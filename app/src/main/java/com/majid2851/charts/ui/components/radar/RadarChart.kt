package com.majid2851.charts.ui.components.radar

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
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Dimens
import kotlin.math.*

@Composable
fun RadarChart(
    data: RadarChartData,
    modifier: Modifier = Modifier,
    onPointSelected: ((dataSetIndex: Int, pointIndex: Int, value: Float) -> Unit)? = null
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
                                val center = Offset(size.width / 2f, size.height / 2f)
                                val radius = minOf(size.width, size.height) / 2 * data.outerRadius
                                
                                val closestPoint = findClosestRadarPoint(
                                    tapOffset = tapOffset,
                                    center = center,
                                    radius = radius,
                                    dataSets = data.dataSets,
                                    labels = data.labels,
                                    domain = data.domain
                                )
                                
                                closestPoint?.let { (setIdx, pointIdx) ->
                                    selectedPoint = Pair(setIdx, pointIdx)
                                    onPointSelected?.invoke(
                                        setIdx,
                                        pointIdx,
                                        data.dataSets[setIdx].values[pointIdx]
                                    )
                                }
                            }
                        }
                    }
            ) {
                if (data.labels.isEmpty() || data.dataSets.isEmpty()) return@ChartCanvas

                val center = Offset(size.width / 2, size.height / 2)
                val radius = minOf(size.width, size.height) / 2 * data.outerRadius
                val angleCount = data.labels.size
                
                // Calculate domain
                val (minValue, maxValue) = data.domain ?: run {
                    val allValues = data.dataSets.flatMap { it.values }
                    val min = allValues.minOrNull() ?: 0f
                    val max = allValues.maxOrNull() ?: 100f
                    Pair(min.coerceAtMost(0f), max)
                }

                // Draw polar grid
                if (data.polarGridConfig.show) {
                    drawPolarGrid(
                        center = center,
                        radius = radius,
                        angleCount = angleCount,
                        config = data.polarGridConfig,
                        radiusConfig = data.polarRadiusAxisConfig
                    )
                }

                // Draw polar radius axis
                if (data.polarRadiusAxisConfig.show) {
                    drawPolarRadiusAxis(
                        center = center,
                        radius = radius,
                        config = data.polarRadiusAxisConfig,
                        textMeasurer = textMeasurer,
                        minValue = minValue,
                        maxValue = maxValue
                    )
                }

                // Draw radar data sets
                data.dataSets.forEachIndexed { setIndex, dataSet ->
                    drawRadarDataSet(
                        center = center,
                        radius = radius,
                        dataSet = dataSet,
                        angleCount = angleCount,
                        minValue = minValue,
                        maxValue = maxValue,
                        selectedPoint = if (selectedPoint?.first == setIndex) selectedPoint?.second else null
                    )
                }

                // Draw polar angle axis (labels)
                if (data.polarAngleAxisConfig.show) {
                    drawPolarAngleAxis(
                        center = center,
                        radius = radius,
                        labels = data.labels,
                        config = data.polarAngleAxisConfig,
                        textMeasurer = textMeasurer
                    )
                }
            }
        }

        // Legend
        if (data.config.showLegend) {
            Spacer(modifier = Modifier.height(16.dp))
            RadarChartLegend(dataSets = data.dataSets)
        }
    }
}

/**
 * Draw polar grid (web)
 */
private fun DrawScope.drawPolarGrid(
    center: Offset,
    radius: Float,
    angleCount: Int,
    config: PolarGridConfig,
    radiusConfig: PolarRadiusAxisConfig
) {
    val tickCount = radiusConfig.tickCount
    
    // Draw concentric circles/polygons
    for (i in 1..tickCount) {
        val r = radius * i / tickCount
        
        when (config.gridType) {
            PolarGridType.CIRCLE -> {
                // Draw circle
                drawCircle(
                    color = config.strokeColor,
                    radius = r,
                    center = center,
                    style = Stroke(width = config.strokeWidth)
                )
            }
            PolarGridType.POLYGON -> {
                // Draw polygon
                val path = Path()
                for (j in 0..angleCount) {
                    val angle = (j * 2 * PI / angleCount - PI / 2).toFloat()
                    val x = center.x + r * cos(angle)
                    val y = center.y + r * sin(angle)
                    
                    if (j == 0) {
                        path.moveTo(x, y)
                    } else {
                        path.lineTo(x, y)
                    }
                }
                path.close()
                
                drawPath(
                    path = path,
                    color = config.strokeColor,
                    style = Stroke(width = config.strokeWidth)
                )
            }
        }
    }
    
    // Draw radial lines from center
    for (i in 0 until angleCount) {
        val angle = (i * 2 * PI / angleCount - PI / 2).toFloat()
        val endX = center.x + radius * cos(angle)
        val endY = center.y + radius * sin(angle)
        
        drawLine(
            color = config.strokeColor,
            start = center,
            end = Offset(endX, endY),
            strokeWidth = config.strokeWidth
        )
    }
}

/**
 * Draw polar radius axis
 */
private fun DrawScope.drawPolarRadiusAxis(
    center: Offset,
    radius: Float,
    config: PolarRadiusAxisConfig,
    textMeasurer: androidx.compose.ui.text.TextMeasurer,
    minValue: Float,
    maxValue: Float
) {
    if (!config.showLabels) return
    
    val angle = Math.toRadians(config.angle.toDouble()).toFloat()
    val tickCount = config.tickCount
    
    for (i in 0..tickCount) {
        val r = radius * i / tickCount
        val value = minValue + (maxValue - minValue) * i / tickCount
        
        val x = center.x + r * cos(angle)
        val y = center.y + r * sin(angle)
        
        val text = value.toInt().toString()
        val textResult = textMeasurer.measure(
            text = text,
            style = TextStyle(
                fontSize = config.labelSize.sp,
                color = config.labelColor
            )
        )
        
        drawText(
            textLayoutResult = textResult,
            topLeft = Offset(
                x - textResult.size.width / 2,
                y - textResult.size.height / 2
            )
        )
    }
}

/**
 * Draw a single radar data set
 */
private fun DrawScope.drawRadarDataSet(
    center: Offset,
    radius: Float,
    dataSet: RadarDataSet,
    angleCount: Int,
    minValue: Float,
    maxValue: Float,
    selectedPoint: Int?
) {
    if (dataSet.values.size != angleCount) return
    
    val path = Path()
    val points = mutableListOf<Offset>()
    
    // Calculate points
    for (i in 0 until angleCount) {
        val angle = (i * 2 * PI / angleCount - PI / 2).toFloat()
        val value = dataSet.values[i]
        val normalizedValue = ((value - minValue) / (maxValue - minValue)).coerceIn(0f, 1f)
        val r = radius * normalizedValue
        
        val x = center.x + r * cos(angle)
        val y = center.y + r * sin(angle)
        val point = Offset(x, y)
        points.add(point)
        
        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    path.close()
    
    // Draw filled area
    drawPath(
        path = path,
        color = dataSet.fillColor.copy(alpha = dataSet.fillOpacity),
        style = Fill
    )
    
    // Draw stroke
    drawPath(
        path = path,
        color = dataSet.lineColor,
        style = Stroke(width = dataSet.lineWidth)
    )
    
    // Draw points
    if (dataSet.showPoints) {
        points.forEachIndexed { index, point ->
            val isSelected = selectedPoint == index
            val pointRadius = if (isSelected) dataSet.pointSize * 1.5f else dataSet.pointSize
            
            drawCircle(
                color = dataSet.lineColor,
                radius = pointRadius,
                center = point
            )
            
            if (isSelected) {
                drawCircle(
                    color = Color.White,
                    radius = pointRadius - 2f,
                    center = point
                )
                drawCircle(
                    color = dataSet.lineColor,
                    radius = pointRadius - 3f,
                    center = point
                )
            }
        }
    }
}

/**
 * Draw polar angle axis (labels around perimeter)
 */
private fun DrawScope.drawPolarAngleAxis(
    center: Offset,
    radius: Float,
    labels: List<String>,
    config: PolarAngleAxisConfig,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val angleCount = labels.size
    val labelRadius = radius + 30f // Offset from radar edge
    
    labels.forEachIndexed { index, label ->
        val angle = (index * 2 * PI / angleCount - PI / 2).toFloat()
        val x = center.x + labelRadius * cos(angle)
        val y = center.y + labelRadius * sin(angle)
        
        val textResult = textMeasurer.measure(
            text = label,
            style = TextStyle(
                fontSize = config.labelSize.sp,
                color = config.labelColor
            )
        )
        
        // Adjust position based on angle for better readability
        val textX = when {
            abs(cos(angle)) < 0.1f -> x - textResult.size.width / 2
            cos(angle) > 0 -> x
            else -> x - textResult.size.width
        }
        
        val textY = when {
            abs(sin(angle)) < 0.1f -> y - textResult.size.height / 2
            sin(angle) > 0 -> y
            else -> y - textResult.size.height
        }
        
        drawText(
            textLayoutResult = textResult,
            topLeft = Offset(textX, textY)
        )
    }
}

/**
 * Find closest radar point to tap location
 */
private fun findClosestRadarPoint(
    tapOffset: Offset,
    center: Offset,
    radius: Float,
    dataSets: List<RadarDataSet>,
    labels: List<String>,
    domain: Pair<Float, Float>?
): Pair<Int, Int>? {
    var closestPoint: Pair<Int, Int>? = null
    var minDistance = Float.MAX_VALUE
    
    val angleCount = labels.size
    val (minValue, maxValue) = domain ?: run {
        val allValues = dataSets.flatMap { it.values }
        val min = allValues.minOrNull() ?: 0f
        val max = allValues.maxOrNull() ?: 100f
        Pair(min.coerceAtMost(0f), max)
    }
    
    dataSets.forEachIndexed { setIndex, dataSet ->
        dataSet.values.forEachIndexed { pointIndex, value ->
            val angle = (pointIndex * 2 * PI / angleCount - PI / 2).toFloat()
            val normalizedValue = ((value - minValue) / (maxValue - minValue)).coerceIn(0f, 1f)
            val r = radius * normalizedValue
            
            val x = center.x + r * cos(angle)
            val y = center.y + r * sin(angle)
            
            val distance = sqrt(
                (tapOffset.x - x) * (tapOffset.x - x) +
                (tapOffset.y - y) * (tapOffset.y - y)
            )
            
            if (distance < minDistance && distance < 30f) {
                minDistance = distance
                closestPoint = Pair(setIndex, pointIndex)
            }
        }
    }
    
    return closestPoint
}

/**
 * Legend for radar chart
 */
@Composable
private fun RadarChartLegend(dataSets: List<RadarDataSet>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        dataSets.forEach { dataSet ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(dataSet.fillColor)
                )
                Text(
                    text = dataSet.label,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RadarChartPreview() {
    RadarChart(
        data = RadarChartData(
            title = "Simple Radar Chart",
            labels = listOf("Math", "Chinese", "English", "Geography", "Physics", "History"),
            dataSets = listOf(
                RadarDataSet(
                    label = "Mike",
                    values = listOf(120f, 98f, 86f, 99f, 85f, 65f),
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.6f)
                )
            ),
            domain = Pair(0f, 150f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
