package com.majid2851.charts.ui.components.area

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
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
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes
import kotlin.math.abs

@Composable
fun AreaChart(
    data: AreaChartData,
    modifier: Modifier = Modifier,
    onPointSelected: ((areaIndex: Int, pointIndex: Int, point: DataPoint?) -> Unit)? = null
) {
    var selectedPoint by remember { mutableStateOf<Pair<Int, Int>?>(null) }
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
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(data.config.isInteractive) {
                        if (data.config.isInteractive) {
                            detectTapGestures { tapOffset ->
                                // Find closest point
                                val padding = 40f
                                val bounds = calculateAreaBounds(data.areas, data.stackingMode)
                                
                                val closestPoint = findClosestPoint(
                                    tapOffset = tapOffset,
                                    areas = data.areas,
                                    bounds = bounds,
                                    canvasWidth = size.width.toFloat(),
                                    canvasHeight = size.height.toFloat(),
                                    padding = padding
                                )
                                
                                closestPoint?.let { (areaIdx, pointIdx) ->
                                    selectedPoint = Pair(areaIdx, pointIdx)
                                    onPointSelected?.invoke(
                                        areaIdx,
                                        pointIdx,
                                        data.areas[areaIdx].dataPoints[pointIdx]
                                    )
                                }
                            }
                        }
                    }
            ) {
                val padding = 40f
                val chartWidth = size.width - padding * 2
                val chartHeight = size.height - padding * 2

                if (data.areas.isEmpty() || chartWidth <= 0 || chartHeight <= 0) return@ChartCanvas

                // Calculate bounds
                val bounds = calculateAreaBounds(data.areas, data.stackingMode)
                
                // Draw grid
                if (data.config.showGrid) {
                    drawCartesianGrid(
                        bounds = bounds,
                        gridConfig = data.config.cartesianGrid,
                        padding = padding
                    )
                }

                // Draw areas based on stacking mode
                when (data.stackingMode) {
                    AreaStackingMode.NONE -> drawRegularAreas(
                        areas = data.areas,
                        bounds = bounds,
                        padding = padding,
                        connectNulls = data.connectNulls,
                        selectedPoint = selectedPoint
                    )
                    AreaStackingMode.STACKED -> drawStackedAreas(
                        areas = data.areas,
                        bounds = bounds,
                        padding = padding,
                        connectNulls = data.connectNulls
                    )
                    AreaStackingMode.PERCENTAGE -> drawPercentageAreas(
                        areas = data.areas,
                        bounds = bounds,
                        padding = padding,
                        connectNulls = data.connectNulls
                    )
                }

                // Draw axes
                if (data.config.showAxis) {
                    drawAxes(
                        bounds = bounds,
                        padding = padding,
                        xAxisConfig = data.xAxisConfig,
                        yAxisConfig = data.yAxisConfig,
                        textMeasurer = textMeasurer,
                        stackingMode = data.stackingMode
                    )
                }
            }
        }

        // Legend
        if (data.config.showLegend) {
            Spacer(modifier = Modifier.height(16.dp))
            AreaChartLegend(areas = data.areas)
        }
    }
}

/**
 * Calculate bounds for area data
 */
private fun calculateAreaBounds(
    areas: List<AreaDataSet>,
    stackingMode: AreaStackingMode
): Bounds {
    if (areas.isEmpty()) return Bounds(0f, 10f, 0f, 100f)

    val allPoints = areas.flatMap { it.dataPoints.filterNotNull() }
    if (allPoints.isEmpty()) return Bounds(0f, 10f, 0f, 100f)

    val minX = allPoints.minOf { it.x }
    val maxX = allPoints.maxOf { it.x }

    val (minY, maxY) = when (stackingMode) {
        AreaStackingMode.NONE -> {
            val min = allPoints.minOf { it.y }
            val max = allPoints.maxOf { it.y }
            Pair(min, max)
        }
        AreaStackingMode.STACKED -> {
            // Calculate stacked values
            val maxStackedValue = calculateMaxStackedValue(areas)
            Pair(0f, maxStackedValue)
        }
        AreaStackingMode.PERCENTAGE -> {
            Pair(0f, 1f) // 0 to 100%
        }
    }

    return Bounds(
        minX = minX,
        maxX = maxX,
        minY = minY.coerceAtMost(0f),
        maxY = maxY.coerceAtLeast(minY + 10f)
    )
}

/**
 * Calculate maximum stacked value
 */
private fun calculateMaxStackedValue(areas: List<AreaDataSet>): Float {
    if (areas.isEmpty()) return 0f
    
    val xIndices = areas.flatMap { it.dataPoints.filterNotNull().map { p -> p.x } }.distinct()
    
    return xIndices.maxOfOrNull { x ->
        areas.sumOf { area ->
            area.dataPoints.filterNotNull().find { it.x == x }?.y?.toDouble() ?: 0.0
        }.toFloat()
    } ?: 0f
}

/**
 * Draw regular (non-stacked) areas
 */
private fun DrawScope.drawRegularAreas(
    areas: List<AreaDataSet>,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean,
    selectedPoint: Pair<Int, Int>?
) {
    areas.forEachIndexed { areaIndex, area ->
        drawSingleArea(
            area = area,
            bounds = bounds,
            padding = padding,
            connectNulls = connectNulls
        )
        
        // Draw line on top of area
        drawAreaLine(
            area = area,
            bounds = bounds,
            padding = padding,
            connectNulls = connectNulls
        )
        
        // Draw points
        area.dataPoints.forEachIndexed { pointIndex, point ->
            point?.let {
                val x = mapXToCanvas(it.x, bounds, padding, size.width)
                val y = mapYToCanvas(it.y, bounds, padding, size.height)
                
                val isSelected = selectedPoint?.first == areaIndex && selectedPoint?.second == pointIndex
                val radius = if (isSelected) 6f else 4f
                
                drawCircle(
                    color = area.lineColor,
                    radius = radius,
                    center = Offset(x, y)
                )
                
                if (isSelected) {
                    drawCircle(
                        color = Color.White,
                        radius = radius - 2f,
                        center = Offset(x, y)
                    )
                    drawCircle(
                        color = area.lineColor,
                        radius = radius - 3f,
                        center = Offset(x, y)
                    )
                }
            }
        }
    }
}

/**
 * Draw a single area fill
 */
private fun DrawScope.drawSingleArea(
    area: AreaDataSet,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean
) {
    val validPoints = if (connectNulls) {
        area.dataPoints.filterNotNull()
    } else {
        area.dataPoints
    }
    
    if (validPoints.isEmpty()) return
    
    val path = Path()
    var isFirstPoint = true
    val baselineY = mapYToCanvas(0f.coerceIn(bounds.minY, bounds.maxY), bounds, padding, size.height)
    
    // Draw top line of area
    validPoints.forEachIndexed { index, point ->
        point?.let {
            val x = mapXToCanvas(it.x, bounds, padding, size.width)
            val y = mapYToCanvas(it.y, bounds, padding, size.height)
            
            if (isFirstPoint) {
                path.moveTo(x, baselineY)
                path.lineTo(x, y)
                isFirstPoint = false
            } else {
                if (area.isCurved && index > 0) {
                    // Smooth curve
                    val prevPoint = validPoints[index - 1]
                    prevPoint?.let { prev ->
                        val prevX = mapXToCanvas(prev.x, bounds, padding, size.width)
                        val prevY = mapYToCanvas(prev.y, bounds, padding, size.height)
                        val controlX = (prevX + x) / 2f
                        path.quadraticBezierTo(prevX, prevY, controlX, (prevY + y) / 2f)
                        path.quadraticBezierTo(controlX, (prevY + y) / 2f, x, y)
                    }
                } else {
                    path.lineTo(x, y)
                }
            }
        }
        
        // Handle null points
        if (point == null && !connectNulls) {
            // Start new path segment
            isFirstPoint = true
        }
    }
    
    // Close path to baseline
    validPoints.lastOrNull()?.let { lastPoint ->
        val lastX = mapXToCanvas(lastPoint.x, bounds, padding, size.width)
        path.lineTo(lastX, baselineY)
        path.close()
    }
    
    // Draw fill (use brush if available, otherwise use color)
    if (area.fillBrush != null) {
        drawPath(
            path = path,
            brush = area.fillBrush,
            style = Fill
        )
    } else {
        val fillColor = area.fillColor.copy(alpha = area.fillOpacity)
        drawPath(
            path = path,
            color = fillColor,
            style = Fill
        )
    }
}

/**
 * Draw area line (border)
 */
private fun DrawScope.drawAreaLine(
    area: AreaDataSet,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean
) {
    val validPoints = if (connectNulls) {
        area.dataPoints.filterNotNull()
    } else {
        area.dataPoints
    }
    
    if (validPoints.isEmpty()) return
    
    val path = Path()
    var isFirstPoint = true
    
    validPoints.forEachIndexed { index, point ->
        point?.let {
            val x = mapXToCanvas(it.x, bounds, padding, size.width)
            val y = mapYToCanvas(it.y, bounds, padding, size.height)
            
            if (isFirstPoint) {
                path.moveTo(x, y)
                isFirstPoint = false
            } else {
                if (area.isCurved && index > 0) {
                    val prevPoint = validPoints[index - 1]
                    prevPoint?.let { prev ->
                        val prevX = mapXToCanvas(prev.x, bounds, padding, size.width)
                        val prevY = mapYToCanvas(prev.y, bounds, padding, size.height)
                        val controlX = (prevX + x) / 2f
                        path.quadraticBezierTo(prevX, prevY, controlX, (prevY + y) / 2f)
                        path.quadraticBezierTo(controlX, (prevY + y) / 2f, x, y)
                    }
                } else {
                    path.lineTo(x, y)
                }
            }
        }
        
        // Handle null points
        if (point == null && !connectNulls) {
            isFirstPoint = true
        }
    }
    
    drawPath(
        path = path,
        color = area.lineColor,
        style = Stroke(width = area.lineWidth)
    )
}

/**
 * Draw stacked areas
 */
private fun DrawScope.drawStackedAreas(
    areas: List<AreaDataSet>,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean
) {
    if (areas.isEmpty()) return
    
    // Calculate cumulative values
    val xValues = areas.flatMap { it.dataPoints.filterNotNull().map { p -> p.x } }.distinct().sorted()
    val cumulativeData = mutableMapOf<Float, Float>()
    
    areas.forEach { area ->
        val path = Path()
        var isFirstPoint = true
        val baselineValues = mutableListOf<Pair<Float, Float>>()
        
        xValues.forEach { x ->
            val point = area.dataPoints.filterNotNull().find { it.x == x }
            val yValue = point?.y ?: 0f
            val baseline = cumulativeData.getOrDefault(x, 0f)
            val topValue = baseline + yValue
            
            val canvasX = mapXToCanvas(x, bounds, padding, size.width)
            val canvasYTop = mapYToCanvas(topValue, bounds, padding, size.height)
            val canvasYBase = mapYToCanvas(baseline, bounds, padding, size.height)
            
            if (isFirstPoint) {
                path.moveTo(canvasX, canvasYBase)
                path.lineTo(canvasX, canvasYTop)
                isFirstPoint = false
            } else {
                path.lineTo(canvasX, canvasYTop)
            }
            
            baselineValues.add(Pair(canvasX, canvasYBase))
            cumulativeData[x] = topValue
        }
        
        // Close path along baseline
        baselineValues.reversed().forEach { (x, y) ->
            path.lineTo(x, y)
        }
        path.close()
        
        // Draw filled area
        if (area.fillBrush != null) {
            drawPath(path, brush = area.fillBrush, style = Fill)
        } else {
            val fillColor = area.fillColor.copy(alpha = area.fillOpacity)
            drawPath(path, color = fillColor, style = Fill)
        }
        
        // Draw top line
        val linePath = Path()
        var lineFirst = true
        xValues.forEach { x ->
            val baseline = cumulativeData.getOrDefault(x, 0f)
            val canvasX = mapXToCanvas(x, bounds, padding, size.width)
            val canvasY = mapYToCanvas(baseline, bounds, padding, size.height)
            
            if (lineFirst) {
                linePath.moveTo(canvasX, canvasY)
                lineFirst = false
            } else {
                linePath.lineTo(canvasX, canvasY)
            }
        }
        drawPath(linePath, color = area.lineColor, style = Stroke(width = area.lineWidth))
    }
}

/**
 * Draw percentage (normalized) areas
 */
private fun DrawScope.drawPercentageAreas(
    areas: List<AreaDataSet>,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean
) {
    if (areas.isEmpty()) return
    
    val xValues = areas.flatMap { it.dataPoints.filterNotNull().map { p -> p.x } }.distinct().sorted()
    val cumulativePercentage = mutableMapOf<Float, Float>()
    
    areas.forEach { area ->
        val path = Path()
        var isFirstPoint = true
        val baselineValues = mutableListOf<Pair<Float, Float>>()
        
        xValues.forEach { x ->
            val point = area.dataPoints.filterNotNull().find { it.x == x }
            val yValue = point?.y ?: 0f
            
            // Calculate total at this x
            val total = areas.sumOf { a ->
                a.dataPoints.filterNotNull().find { it.x == x }?.y?.toDouble() ?: 0.0
            }.toFloat()
            
            val percentage = if (total > 0) yValue / total else 0f
            val baseline = cumulativePercentage.getOrDefault(x, 0f)
            val topValue = baseline + percentage
            
            val canvasX = mapXToCanvas(x, bounds, padding, size.width)
            val canvasYTop = mapYToCanvas(topValue, bounds, padding, size.height)
            val canvasYBase = mapYToCanvas(baseline, bounds, padding, size.height)
            
            if (isFirstPoint) {
                path.moveTo(canvasX, canvasYBase)
                path.lineTo(canvasX, canvasYTop)
                isFirstPoint = false
            } else {
                path.lineTo(canvasX, canvasYTop)
            }
            
            baselineValues.add(Pair(canvasX, canvasYBase))
            cumulativePercentage[x] = topValue
        }
        
        // Close path
        baselineValues.reversed().forEach { (x, y) ->
            path.lineTo(x, y)
        }
        path.close()
        
        if (area.fillBrush != null) {
            drawPath(path, brush = area.fillBrush, style = Fill)
        } else {
            val fillColor = area.fillColor.copy(alpha = area.fillOpacity)
            drawPath(path, color = fillColor, style = Fill)
        }
        
        // Draw line
        val linePath = Path()
        var lineFirst = true
        xValues.forEach { x ->
            val baseline = cumulativePercentage.getOrDefault(x, 0f)
            val canvasX = mapXToCanvas(x, bounds, padding, size.width)
            val canvasY = mapYToCanvas(baseline, bounds, padding, size.height)
            
            if (lineFirst) {
                linePath.moveTo(canvasX, canvasY)
                lineFirst = false
            } else {
                linePath.lineTo(canvasX, canvasY)
            }
        }
        drawPath(linePath, color = area.lineColor, style = Stroke(width = area.lineWidth))
    }
}

/**
 * Draw axes with labels
 */
private fun DrawScope.drawAxes(
    bounds: Bounds,
    padding: Float,
    xAxisConfig: AxisConfig,
    yAxisConfig: AxisConfig,
    textMeasurer: androidx.compose.ui.text.TextMeasurer,
    stackingMode: AreaStackingMode
) {
    val chartWidth = size.width - padding * 2
    val chartHeight = size.height - padding * 2
    
    // X-axis
    if (xAxisConfig.showLabels) {
        val xLabelCount = xAxisConfig.labelCount
        for (i in 0..xLabelCount) {
            val fraction = i.toFloat() / xLabelCount
            val x = padding + fraction * chartWidth
            val value = bounds.minX + (bounds.maxX - bounds.minX) * fraction
            
            val text = String.format("%.1f", value)
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
            
            val text = when (stackingMode) {
                AreaStackingMode.PERCENTAGE -> "${(value * 100).toInt()}%"
                else -> String.format("%.0f", value)
            }
            
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

/**
 * Find closest point to tap location
 */
private fun findClosestPoint(
    tapOffset: Offset,
    areas: List<AreaDataSet>,
    bounds: Bounds,
    canvasWidth: Float,
    canvasHeight: Float,
    padding: Float
): Pair<Int, Int>? {
    var closestPoint: Pair<Int, Int>? = null
    var minDistance = Float.MAX_VALUE
    
    areas.forEachIndexed { areaIndex, area ->
        area.dataPoints.forEachIndexed { pointIndex, point ->
            point?.let {
                val x = mapXToCanvas(it.x, bounds, padding, canvasWidth)
                val y = mapYToCanvas(it.y, bounds, padding, canvasHeight)
                
                val distance = kotlin.math.sqrt(
                    (tapOffset.x - x) * (tapOffset.x - x) +
                    (tapOffset.y - y) * (tapOffset.y - y)
                )
                
                if (distance < minDistance && distance < 50f) {
                    minDistance = distance
                    closestPoint = Pair(areaIndex, pointIndex)
                }
            }
        }
    }
    
    return closestPoint
}

// Mapping functions
private fun mapXToCanvas(x: Float, bounds: Bounds, padding: Float, canvasWidth: Float): Float {
    val chartWidth = canvasWidth - padding * 2
    return padding + ((x - bounds.minX) / (bounds.maxX - bounds.minX)) * chartWidth
}

private fun mapYToCanvas(y: Float, bounds: Bounds, padding: Float, canvasHeight: Float): Float {
    val chartHeight = canvasHeight - padding * 2
    return padding + chartHeight - ((y - bounds.minY) / (bounds.maxY - bounds.minY)) * chartHeight
}

/**
 * Legend for area chart
 */
@Composable
private fun AreaChartLegend(areas: List<AreaDataSet>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        areas.forEach { area ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(area.fillColor)
                )
                Text(
                    text = area.label,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AreaChartPreview() {
    AreaChart(
        data = AreaChartData(
            title = "Simple Area Chart",
            areas = listOf(
                AreaDataSet(
                    label = "UV",
                    dataPoints = listOf(
                        DataPoint(0f, 4000f),
                        DataPoint(1f, 3000f),
                        DataPoint(2f, 2000f),
                        DataPoint(3f, 2780f),
                        DataPoint(4f, 1890f),
                        DataPoint(5f, 2390f),
                        DataPoint(6f, 3490f)
                    ),
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.6f)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

