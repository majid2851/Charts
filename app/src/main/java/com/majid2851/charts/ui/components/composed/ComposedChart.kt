package com.majid2851.charts.ui.components.composed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.components.line.line_chart.components.Bounds
import com.majid2851.charts.ui.components.line.line_chart.components.drawCartesianGrid
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Dimens

/**
 * ComposedChart - Combines multiple chart types (Line, Bar, Area, Scatter)
 * Matches Recharts ComposedChart functionality
 */
@Composable
fun ComposedChart(
    data: ComposedChartData,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {
        data.title?.let {
            Text(
                text = it,
                fontSize = FontSizes.titleLarge,
                modifier = Modifier.padding(bottom = Dimens.paddingSmall)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ChartCanvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val padding = 50f
                val chartWidth = size.width - padding * 2
                val chartHeight = size.height - padding * 2

                if (data.categories.isEmpty()) return@ChartCanvas

                // Calculate bounds from all data
                val bounds = calculateComposedBounds(data)
                
                // Draw grid
                if (data.config.showGrid) {
                    drawCartesianGrid(
                        bounds = bounds,
                        gridConfig = data.config.cartesianGrid,
                        padding = padding
                    )
                }

                // Draw areas first (background layer)
                data.areaDataSets.forEach { areaSet ->
                    drawComposedArea(
                        areaSet = areaSet,
                        categories = data.categories,
                        bounds = bounds,
                        padding = padding
                    )
                }

                // Draw bars
                data.barDataSets.forEach { barSet ->
                    drawComposedBars(
                        barSet = barSet,
                        categories = data.categories,
                        bounds = bounds,
                        padding = padding
                    )
                }

                // Draw lines
                data.lineDataSets.forEach { lineSet ->
                    drawComposedLine(
                        lineSet = lineSet,
                        categories = data.categories,
                        bounds = bounds,
                        padding = padding
                    )
                }

                // Draw scatter points (top layer)
                data.scatterDataSets.forEach { scatterSet ->
                    drawComposedScatter(
                        scatterSet = scatterSet,
                        categories = data.categories,
                        bounds = bounds,
                        padding = padding
                    )
                }

                // Draw axes
                if (data.config.showAxis) {
                    drawComposedAxes(
                        categories = data.categories,
                        bounds = bounds,
                        padding = padding,
                        xAxisConfig = data.xAxisConfig,
                        yAxisConfig = data.yAxisConfig,
                        textMeasurer = textMeasurer
                    )
                }
            }
        }

        // Legend
        if (data.config.showLegend) {
            Spacer(modifier = Modifier.height(16.dp))
            ComposedChartLegend(data = data)
        }
    }
}

/**
 * Calculate bounds from all data sources
 */
private fun calculateComposedBounds(data: ComposedChartData): Bounds {
    val allValues = mutableListOf<Float>()
    
    data.lineDataSets.forEach { lineSet ->
        lineSet.dataPoints.filterNotNull().forEach { point ->
            allValues.add(point.y)
        }
    }
    
    data.barDataSets.forEach { barSet ->
        barSet.dataPoints.filterNotNull().forEach { point ->
            allValues.add(point.y)
        }
    }
    
    data.areaDataSets.forEach { areaSet ->
        areaSet.dataPoints.filterNotNull().forEach { point ->
            allValues.add(point.y)
        }
    }
    
    data.scatterDataSets.forEach { scatterSet ->
        scatterSet.dataPoints.forEach { point ->
            allValues.add(point.y)
        }
    }
    
    if (allValues.isEmpty()) {
        return Bounds(0f, data.categories.size.toFloat() - 1, 0f, 100f)
    }
    
    val minY = allValues.minOrNull() ?: 0f
    val maxY = allValues.maxOrNull() ?: 100f
    val yPadding = (maxY - minY) * 0.1f
    
    return Bounds(
        minX = 0f,
        maxX = (data.categories.size - 1).toFloat(),
        minY = (minY - yPadding).coerceAtLeast(0f),
        maxY = maxY + yPadding
    )
}

/**
 * Draw area for composed chart
 */
private fun DrawScope.drawComposedArea(
    areaSet: AreaDataSet,
    categories: List<String>,
    bounds: Bounds,
    padding: Float
) {
    val validPoints = areaSet.dataPoints.filterNotNull()
    if (validPoints.isEmpty()) return
    
    val path = Path()
    val baselineY = mapYToCanvas(0f.coerceIn(bounds.minY, bounds.maxY), bounds, padding, size.height)
    
    // Draw area path
    validPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(index.toFloat(), bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        if (index == 0) {
            path.moveTo(x, baselineY)
            path.lineTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    
    // Close path to baseline
    validPoints.lastOrNull()?.let {
        val lastX = mapXToCanvas((validPoints.size - 1).toFloat(), bounds, padding, size.width)
        path.lineTo(lastX, baselineY)
        path.close()
    }
    
    // Draw fill
    drawPath(
        path = path,
        color = areaSet.fillColor.copy(alpha = areaSet.fillOpacity),
        style = Fill
    )
    
    // Draw stroke
    val strokePath = Path()
    validPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(index.toFloat(), bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        if (index == 0) {
            strokePath.moveTo(x, y)
        } else {
            strokePath.lineTo(x, y)
        }
    }
    
    drawPath(
        path = strokePath,
        color = areaSet.lineColor,
        style = Stroke(width = areaSet.lineWidth)
    )
}

/**
 * Draw bars for composed chart
 */
private fun DrawScope.drawComposedBars(
    barSet: ComposedBarDataSet,
    categories: List<String>,
    bounds: Bounds,
    padding: Float
) {
    val validPoints = barSet.dataPoints.filterNotNull()
    val barWidth = barSet.barSize
    val chartWidth = size.width - padding * 2
    val categoryWidth = chartWidth / categories.size
    
    validPoints.forEachIndexed { index, point ->
        val x = padding + (index + 0.5f) * categoryWidth - barWidth / 2
        val baseY = mapYToCanvas(0f.coerceIn(bounds.minY, bounds.maxY), bounds, padding, size.height)
        val topY = mapYToCanvas(point.y, bounds, padding, size.height)
        val barHeight = baseY - topY
        
        drawRect(
            color = barSet.color,
            topLeft = Offset(x, topY),
            size = Size(barWidth, barHeight)
        )
    }
}

/**
 * Draw line for composed chart
 */
private fun DrawScope.drawComposedLine(
    lineSet: LineDataSet,
    categories: List<String>,
    bounds: Bounds,
    padding: Float
) {
    val validPoints = lineSet.dataPoints.filterNotNull()
    if (validPoints.size < 2) return
    
    val path = Path()
    
    validPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(index.toFloat(), bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        if (index == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    
    drawPath(
        path = path,
        color = lineSet.lineColor,
        style = Stroke(width = lineSet.lineWidth)
    )
    
    // Draw points
    validPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(index.toFloat(), bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        drawCircle(
            color = lineSet.lineColor,
            radius = 4f,
            center = Offset(x, y)
        )
    }
}

/**
 * Draw scatter points for composed chart
 */
private fun DrawScope.drawComposedScatter(
    scatterSet: ScatterDataSet,
    categories: List<String>,
    bounds: Bounds,
    padding: Float
) {
    scatterSet.dataPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(index.toFloat(), bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        when (scatterSet.pointShape) {
            PointShape.CIRCLE -> {
                drawCircle(
                    color = scatterSet.pointColor,
                    radius = scatterSet.pointSize,
                    center = Offset(x, y)
                )
            }
            PointShape.SQUARE -> {
                val size = scatterSet.pointSize * 2
                drawRect(
                    color = scatterSet.pointColor,
                    topLeft = Offset(x - scatterSet.pointSize, y - scatterSet.pointSize),
                    size = Size(size, size)
                )
            }
            else -> {
                drawCircle(
                    color = scatterSet.pointColor,
                    radius = scatterSet.pointSize,
                    center = Offset(x, y)
                )
            }
        }
    }
}

/**
 * Draw axes for composed chart
 */
private fun DrawScope.drawComposedAxes(
    categories: List<String>,
    bounds: Bounds,
    padding: Float,
    xAxisConfig: AxisConfig,
    yAxisConfig: AxisConfig,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val chartWidth = size.width - padding * 2
    val chartHeight = size.height - padding * 2
    
    // X-axis (categories)
    if (xAxisConfig.showLabels) {
        categories.forEachIndexed { index, category ->
            val x = padding + (index + 0.5f) * (chartWidth / categories.size)
            val text = category
            val textResult = textMeasurer.measure(
                text = text,
                style = TextStyle(fontSize = xAxisConfig.labelTextSize.sp, color = xAxisConfig.axisColor)
            )
            
            drawText(
                textLayoutResult = textResult,
                topLeft = Offset(
                    x - textResult.size.width / 2,
                    size.height - padding + 10f
                )
            )
        }
    }
    
    // Y-axis
    if (yAxisConfig.showLabels) {
        val yLabelCount = yAxisConfig.labelCount
        for (i in 0..yLabelCount) {
            val fraction = i.toFloat() / yLabelCount
            val y = padding + chartHeight - fraction * chartHeight
            val value = bounds.minY + (bounds.maxY - bounds.minY) * fraction
            
            val text = value.toInt().toString()
            val textResult = textMeasurer.measure(
                text = text,
                style = TextStyle(fontSize = yAxisConfig.labelTextSize.sp, color = yAxisConfig.axisColor)
            )
            
            drawText(
                textLayoutResult = textResult,
                topLeft = Offset(
                    5f,
                    y - textResult.size.height / 2
                )
            )
        }
    }
}

// Mapping functions
private fun mapXToCanvas(x: Float, bounds: Bounds, padding: Float, canvasWidth: Float): Float {
    val chartWidth = canvasWidth - padding * 2
    val categoryWidth = chartWidth / (bounds.maxX - bounds.minX + 1)
    return padding + (x - bounds.minX + 0.5f) * categoryWidth
}

private fun mapYToCanvas(y: Float, bounds: Bounds, padding: Float, canvasHeight: Float): Float {
    val chartHeight = canvasHeight - padding * 2
    return padding + chartHeight - ((y - bounds.minY) / (bounds.maxY - bounds.minY)) * chartHeight
}

/**
 * Legend for composed chart
 */
@Composable
private fun ComposedChartLegend(data: ComposedChartData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        // Line legends
        data.lineDataSets.forEach { lineSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(lineSet.lineColor)
                )
                Text(text = lineSet.label, fontSize = 14.sp)
            }
        }
        
        // Bar legends
        data.barDataSets.forEach { barSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(barSet.color)
                )
                Text(text = barSet.label, fontSize = 14.sp)
            }
        }
        
        // Area legends
        data.areaDataSets.forEach { areaSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(areaSet.fillColor)
                )
                Text(text = areaSet.label, fontSize = 14.sp)
            }
        }
        
        // Scatter legends
        data.scatterDataSets.forEach { scatterSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(scatterSet.pointColor)
                )
                Text(text = scatterSet.label, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposedChartPreview() {
    val categories = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F")
    
    ComposedChart(
        data = ComposedChartData(
            title = "Line Bar Area Composed Chart",
            categories = categories,
            areaDataSets = listOf(
                AreaDataSet(
                    label = "amt",
                    dataPoints = listOf(
                        DataPoint(0f, 1400f),
                        DataPoint(1f, 1506f),
                        DataPoint(2f, 989f),
                        DataPoint(3f, 1228f),
                        DataPoint(4f, 1100f),
                        DataPoint(5f, 1700f)
                    ),
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8)
                )
            ),
            barDataSets = listOf(
                ComposedBarDataSet(
                    dataKey = "pv",
                    label = "pv",
                    dataPoints = listOf(
                        DataPoint(0f, 800f),
                        DataPoint(1f, 967f),
                        DataPoint(2f, 1098f),
                        DataPoint(3f, 1200f),
                        DataPoint(4f, 1108f),
                        DataPoint(5f, 680f)
                    ),
                    color = Color(0xFF413ea0),
                    barSize = 20f
                )
            ),
            lineDataSets = listOf(
                LineDataSet(
                    label = "uv",
                    dataPoints = listOf(
                        DataPoint(0f, 590f),
                        DataPoint(1f, 868f),
                        DataPoint(2f, 1397f),
                        DataPoint(3f, 1480f),
                        DataPoint(4f, 1520f),
                        DataPoint(5f, 1400f)
                    ),
                    lineColor = Color(0xFFff7300)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

