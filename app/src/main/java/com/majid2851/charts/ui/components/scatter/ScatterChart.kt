package com.majid2851.charts.ui.components.scatter

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
import androidx.compose.ui.graphics.nativeCanvas
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
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Dimens
import kotlin.math.*

@Composable
fun ScatterChart(
    data: ScatterChartData,
    modifier: Modifier = Modifier,
    onPointSelected: ((setIndex: Int, pointIndex: Int, point: ScatterPoint?) -> Unit)? = null
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
                                val padding = 40f
                                val bounds = calculateScatterBounds(data.scatterSets)
                                
                                val closestPoint = findClosestPoint(
                                    tapOffset = tapOffset,
                                    scatterSets = data.scatterSets,
                                    bounds = bounds,
                                    canvasWidth = size.width.toFloat(),
                                    canvasHeight = size.height.toFloat(),
                                    padding = padding,
                                    zAxisConfig = data.zAxisConfig
                                )
                                
                                closestPoint?.let { (setIdx, pointIdx) ->
                                    selectedPoint = Pair(setIdx, pointIdx)
                                    onPointSelected?.invoke(
                                        setIdx,
                                        pointIdx,
                                        data.scatterSets[setIdx].dataPoints[pointIdx]
                                    )
                                }
                            }
                        }
                    }
            ) {
                val padding = 40f
                val chartWidth = size.width - padding * 2
                val chartHeight = size.height - padding * 2

                if (data.scatterSets.isEmpty() || chartWidth <= 0 || chartHeight <= 0) return@ChartCanvas

                // Calculate bounds
                val bounds = calculateScatterBounds(data.scatterSets)
                
                // Draw grid
                if (data.config.showGrid) {
                    drawCartesianGrid(
                        bounds = bounds,
                        gridConfig = data.config.cartesianGrid,
                        padding = padding
                    )
                }

                // Draw scatter sets
                data.scatterSets.forEachIndexed { setIndex, scatterSet ->
                    drawScatterSet(
                        scatterSet = scatterSet,
                        bounds = bounds,
                        padding = padding,
                        selectedPoint = if (selectedPoint?.first == setIndex) selectedPoint?.second else null,
                        zAxisConfig = data.zAxisConfig
                    )
                }

                // Draw axes
                if (data.config.showAxis) {
                    drawAxes(
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
            ScatterChartLegend(scatterSets = data.scatterSets)
        }
    }
}

/**
 * Calculate bounds for scatter data
 */
private fun calculateScatterBounds(scatterSets: List<ScatterDataSet>): Bounds {
    if (scatterSets.isEmpty()) return Bounds(0f, 10f, 0f, 10f)

    val allPoints = scatterSets.flatMap { it.dataPoints }
    if (allPoints.isEmpty()) return Bounds(0f, 10f, 0f, 10f)

    val minX = allPoints.minOf { it.x }
    val maxX = allPoints.maxOf { it.x }
    val minY = allPoints.minOf { it.y }
    val maxY = allPoints.maxOf { it.y }

    // Add padding
    val xPadding = (maxX - minX) * 0.1f
    val yPadding = (maxY - minY) * 0.1f

    return Bounds(
        minX = (minX - xPadding).coerceAtLeast(0f),
        maxX = maxX + xPadding,
        minY = (minY - yPadding).coerceAtLeast(0f),
        maxY = maxY + yPadding
    )
}

/**
 * Draw a single scatter set
 */
private fun DrawScope.drawScatterSet(
    scatterSet: ScatterDataSet,
    bounds: Bounds,
    padding: Float,
    selectedPoint: Int?,
    zAxisConfig: ZAxisConfig?
) {
    // Draw connecting line if enabled
    if (scatterSet.showLine && scatterSet.dataPoints.size > 1) {
        val path = Path()
        scatterSet.dataPoints.forEachIndexed { index, point ->
            val x = mapXToCanvas(point.x, bounds, padding, size.width)
            val y = mapYToCanvas(point.y, bounds, padding, size.height)
            
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        
        drawPath(
            path = path,
            color = scatterSet.lineColor ?: scatterSet.pointColor,
            style = Stroke(width = scatterSet.lineWidth)
        )
    }

    // Draw points
    scatterSet.dataPoints.forEachIndexed { index, point ->
        val x = mapXToCanvas(point.x, bounds, padding, size.width)
        val y = mapYToCanvas(point.y, bounds, padding, size.height)
        
        // Calculate point size (for bubble charts with Z-axis)
        val pointRadius = if (point.z != null && zAxisConfig != null) {
            mapZToSize(point.z, zAxisConfig.range)
        } else {
            scatterSet.pointSize
        }
        
        // Determine point color
        val pointColor = point.color 
            ?: (if (scatterSet.customColors != null && index < scatterSet.customColors.size) {
                scatterSet.customColors[index]
            } else {
                scatterSet.pointColor
            })
        
        val isSelected = selectedPoint == index
        
        // Draw point shape
        drawScatterPoint(
            center = Offset(x, y),
            radius = if (isSelected) pointRadius * 1.2f else pointRadius,
            color = pointColor,
            shape = scatterSet.pointShape,
            isSelected = isSelected
        )
        
        // Draw labels if enabled
        if (scatterSet.showLabels && point.label != null) {
            drawContext.canvas.nativeCanvas.apply {
                val paint = android.graphics.Paint().apply {
                    this.color = android.graphics.Color.BLACK
                    textSize = 12.sp.toPx()
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                drawText(
                    point.label,
                    x,
                    y - pointRadius - 10f,
                    paint
                )
            }
        }
    }
}

/**
 * Draw a single scatter point with different shapes
 */
private fun DrawScope.drawScatterPoint(
    center: Offset,
    radius: Float,
    color: Color,
    shape: PointShape,
    isSelected: Boolean
) {
    when (shape) {
        PointShape.CIRCLE -> {
            drawCircle(
                color = color,
                radius = radius,
                center = center
            )
            if (isSelected) {
                drawCircle(
                    color = color.copy(alpha = 0.3f),
                    radius = radius * 1.5f,
                    center = center
                )
            }
        }
        PointShape.SQUARE -> {
            val size = radius * 2
            drawRect(
                color = color,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = androidx.compose.ui.geometry.Size(size, size)
            )
        }
        PointShape.TRIANGLE -> {
            val path = Path().apply {
                moveTo(center.x, center.y - radius)
                lineTo(center.x - radius, center.y + radius)
                lineTo(center.x + radius, center.y + radius)
                close()
            }
            drawPath(path, color)
        }
        PointShape.DIAMOND -> {
            val path = Path().apply {
                moveTo(center.x, center.y - radius)
                lineTo(center.x + radius, center.y)
                lineTo(center.x, center.y + radius)
                lineTo(center.x - radius, center.y)
                close()
            }
            drawPath(path, color)
        }
        PointShape.CROSS -> {
            val lineLength = radius
            drawLine(
                color = color,
                start = Offset(center.x - lineLength, center.y),
                end = Offset(center.x + lineLength, center.y),
                strokeWidth = 3f
            )
            drawLine(
                color = color,
                start = Offset(center.x, center.y - lineLength),
                end = Offset(center.x, center.y + lineLength),
                strokeWidth = 3f
            )
        }
        PointShape.STAR -> {
            val path = createStarPath(center, radius)
            drawPath(path, color, style = Fill)
        }
    }
}

/**
 * Create a star shape path
 */
private fun createStarPath(center: Offset, radius: Float): Path {
    val path = Path()
    val numPoints = 5
    val innerRadius = radius * 0.4f
    
    for (i in 0 until numPoints * 2) {
        val angle = (i * PI / numPoints).toFloat() - (PI / 2).toFloat()
        val r = if (i % 2 == 0) radius else innerRadius
        val x = center.x + r * cos(angle)
        val y = center.y + r * sin(angle)
        
        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    return path
}

/**
 * Map Z value to point size
 */
private fun mapZToSize(z: Float, range: Pair<Float, Float>): Float {
    val (minSize, maxSize) = range
    // Normalize z to 0-1 range (assuming z is positive)
    val normalized = (z / 500f).coerceIn(0f, 1f)
    return minSize + (maxSize - minSize) * normalized
}

/**
 * Draw axes with labels
 */
private fun DrawScope.drawAxes(
    bounds: Bounds,
    padding: Float,
    xAxisConfig: AxisConfig,
    yAxisConfig: AxisConfig,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
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
            
            val text = value.toInt().toString()
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

/**
 * Find closest point to tap location
 */
private fun findClosestPoint(
    tapOffset: Offset,
    scatterSets: List<ScatterDataSet>,
    bounds: Bounds,
    canvasWidth: Float,
    canvasHeight: Float,
    padding: Float,
    zAxisConfig: ZAxisConfig?
): Pair<Int, Int>? {
    var closestPoint: Pair<Int, Int>? = null
    var minDistance = Float.MAX_VALUE
    
    scatterSets.forEachIndexed { setIndex, scatterSet ->
        scatterSet.dataPoints.forEachIndexed { pointIndex, point ->
            val x = mapXToCanvas(point.x, bounds, padding, canvasWidth)
            val y = mapYToCanvas(point.y, bounds, padding, canvasHeight)
            
            val distance = sqrt(
                (tapOffset.x - x) * (tapOffset.x - x) +
                (tapOffset.y - y) * (tapOffset.y - y)
            )
            
            val touchRadius = if (point.z != null && zAxisConfig != null) {
                mapZToSize(point.z, zAxisConfig.range)
            } else {
                scatterSet.pointSize
            }
            
            if (distance < minDistance && distance < touchRadius + 20f) {
                minDistance = distance
                closestPoint = Pair(setIndex, pointIndex)
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
 * Legend for scatter chart
 */
@Composable
private fun ScatterChartLegend(scatterSets: List<ScatterDataSet>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        scatterSets.forEach { scatterSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(scatterSet.pointColor)
                )
                Text(
                    text = scatterSet.label,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScatterChartPreview() {
    ScatterChart(
        data = ScatterChartData(
            title = "Simple Scatter Chart",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = listOf(
                        ScatterPoint(100f, 200f, 200f),
                        ScatterPoint(120f, 100f, 260f),
                        ScatterPoint(170f, 300f, 400f),
                        ScatterPoint(140f, 250f, 280f),
                        ScatterPoint(150f, 400f, 500f),
                        ScatterPoint(110f, 280f, 200f)
                    ),
                    pointColor = Color(0xFF8884d8)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
