package com.majid2851.charts.ui.components.line.line_chart

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.components.line.line_chart.components.*
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Strings

@Composable
fun LineChart(
    data: LineChartData,
    modifier: Modifier = Modifier,
    onPointSelected: ((lineIndex: Int, pointIndex: Int, point: DataPoint?) -> Unit)? = null,
    zoomState: ZoomState? = null
) {
    var selectedPoint by remember { mutableStateOf<SelectedPoint?>(null) }

    val chartZoomState = zoomState ?: rememberZoomState(
        minZoom = data.config.minZoom,
        maxZoom = data.config.maxZoom
    )
    
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
            val baseBounds = remember(data.lines) { calculateBounds(data.lines) }
            
            ChartCanvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(data.config.enableZoom, data.config.enablePan) {
                        if (data.config.enableZoom || data.config.enablePan) {
                            detectTransformGestures { centroid, pan, zoom, _ ->
                                if (data.config.enableZoom && zoom != 1f) {
                                    chartZoomState.updateZoom(chartZoomState.zoom * zoom, centroid)
                                }
                                if (data.config.enablePan) {
                                    chartZoomState.updatePan(pan)
                                }
                            }
                        }
                    }
                    .pointerInput(data.config.isInteractive) {
                        if (data.config.isInteractive) {
                            // Handle drag gestures for continuous vertical line tracking
                            detectDragGestures(
                                onDragStart = { offset ->
                                    val padding = 40f
                                    val canvasSize = size.toSize()
                                    
                                    val bounds = if (data.config.enableZoom || data.config.enablePan) {
                                        baseBounds.applyZoomAndPan(
                                            chartZoomState,
                                            canvasSize.width,
                                            canvasSize.height,
                                            padding
                                        )
                                    } else {
                                        baseBounds
                                    }
                                    
                                    // Find nearest point by X-axis only
                                    val nearestPoint = findNearestPointByX(
                                        offset = offset,
                                        lines = data.lines,
                                        bounds = bounds,
                                        padding = padding,
                                        canvasSize = canvasSize
                                    )
                                    
                                    selectedPoint = nearestPoint
                                    nearestPoint?.let {
                                        onPointSelected?.invoke(
                                            it.lineIndex,
                                            it.pointIndex,
                                            data.lines[it.lineIndex].dataPoints[it.pointIndex]
                                        )
                                    }
                                },
                                onDrag = { change, _ ->
                                    change.consume()
                                    val padding = 40f
                                    val canvasSize = size.toSize()
                                    
                                    val bounds = if (data.config.enableZoom || data.config.enablePan) {
                                        baseBounds.applyZoomAndPan(
                                            chartZoomState,
                                            canvasSize.width,
                                            canvasSize.height,
                                            padding
                                        )
                                    } else {
                                        baseBounds
                                    }
                                    
                                    // Update selected point as user drags
                                    val nearestPoint = findNearestPointByX(
                                        offset = change.position,
                                        lines = data.lines,
                                        bounds = bounds,
                                        padding = padding,
                                        canvasSize = canvasSize
                                    )
                                    
                                    selectedPoint = nearestPoint
                                    nearestPoint?.let {
                                        onPointSelected?.invoke(
                                            it.lineIndex,
                                            it.pointIndex,
                                            data.lines[it.lineIndex].dataPoints[it.pointIndex]
                                        )
                                    }
                                },
                                onDragEnd = {
                                    // Keep the selection visible after drag ends
                                    // Optionally clear it: selectedPoint = null
                                }
                            )
                        }
                    }
                    .pointerInput(data.config.isInteractive) {
                        if (data.config.isInteractive) {
                            // Handle tap gestures for quick selection
                            detectTapGestures(
                                onTap = { offset ->
                                    val padding = 40f
                                    val canvasSize = size.toSize()
                                     
                                    val bounds = if (data.config.enableZoom || data.config.enablePan) {
                                        baseBounds.applyZoomAndPan(
                                            chartZoomState,
                                            canvasSize.width,
                                            canvasSize.height,
                                            padding
                                        )
                                    } else {
                                        baseBounds
                                    }

                                    // Use X-axis based detection for tap too
                                    val clickedPoint = findNearestPointByX(
                                        offset = offset,
                                        lines = data.lines,
                                        bounds = bounds,
                                        padding = padding,
                                        canvasSize = canvasSize
                                    )

                                    selectedPoint = clickedPoint
                                    clickedPoint?.let {
                                        onPointSelected?.invoke(
                                            it.lineIndex,
                                            it.pointIndex,
                                            data.lines[it.lineIndex].dataPoints[it.pointIndex]
                                        )
                                    }
                                },
                                onDoubleTap = { offset ->
                                    if (data.config.enableZoom) {
                                        if (chartZoomState.zoom > 1f) {
                                            chartZoomState.reset()
                                        } else {
                                            chartZoomState.zoomIn(offset)
                                        }
                                    }
                                }
                            )
                        }
                    }
            ) {
                val padding = 40f
                
                // Apply zoom and pan transformations to bounds
                val bounds = if (data.config.enableZoom || data.config.enablePan) {
                    baseBounds.applyZoomAndPan(
                        chartZoomState,
                        size.width,
                        size.height,
                        padding
                    )
                } else {
                    baseBounds
                }

                if (data.config.showGrid) {
                    drawCartesianGrid(
                        bounds = bounds,
                        gridConfig = data.config.cartesianGrid,
                        padding = padding
                    )
                }

                // Draw regular grid lines (independent of data points)
                drawDataPointGridLines(
                    lines = data.lines,
                    bounds = bounds,
                    padding = padding,
                    verticalLineColor = Color.Gray.copy(alpha = 0.6f),
                    horizontalLineColor = Color.Gray.copy(alpha = 0.6f),
                    strokeWidth = 1.5f,
                    isDashed = true,
                    verticalLineCount = 7,
                    horizontalLineCount = 5
                )

                if (data.config.showAxis) {
                    drawAxes(bounds, data.xAxisConfig.axisColor, padding)
                    
                    if (data.yAxisConfig.showLabels) {
                        drawYAxisLabels(
                            bounds = bounds,
                            labelCount = data.yAxisConfig.labelCount,
                            labelColor = data.yAxisConfig.axisColor,
                            labelTextSize = data.yAxisConfig.labelTextSize,
                            padding = padding
                        )
                    }
                    
                    if (data.xAxisConfig.showLabels && data.lines.isNotEmpty()) {
                        drawXAxisLabels(
                            dataPoints = data.lines.first().dataPoints,
                            bounds = bounds,
                            labelColor = data.xAxisConfig.axisColor,
                            labelTextSize = data.xAxisConfig.labelTextSize,
                            padding = padding
                        )
                    }
                }

                if (data.referenceLines.isNotEmpty()) {
                    drawReferenceLines(data.referenceLines, bounds, padding)
                }

                data.lines.forEachIndexed { lineIndex, lineDataSet ->
                    val isLineSelected = selectedPoint?.lineIndex == lineIndex
                    drawLine(
                        lineDataSet = lineDataSet,
                        bounds = bounds,
                        padding = padding,
                        connectNulls = data.connectNulls,
                        isSelected = isLineSelected,
                        selectedPointIndex = if (isLineSelected) selectedPoint?.pointIndex 
                        else null
                    )
                    drawPoints(
                        lineDataSet = lineDataSet,
                        bounds = bounds,
                        padding = padding,
                        lineIndex = lineIndex,
                        selectedPoint = selectedPoint
                    )
                }

                // Draw vertical line for selected point (drawn last to be on top)
                selectedPoint?.let { selected ->
                    val dataPoint = data.lines[selected.lineIndex].dataPoints[selected.pointIndex]
                    dataPoint?.let { point ->
                        drawSelectedPointVerticalLine(
                            selectedPoint = selected,
                            dataPoint = point,
                            bounds = bounds,
                            padding = padding,
                            lineColor = Color.Black.copy(alpha = 0.8f),
                            strokeWidth = 2f
                        )
                    }
                }
            }
            
            // Show zoom controls if enabled
            if (data.config.showZoomControls) {
                ZoomControls(
                    zoomState = chartZoomState,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
        }

        if (data.config.showLegend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                data.lines.forEach { line ->
                    Text(
                        text = line.label,
                        modifier = Modifier
                            .padding(
                                end = Dimens.paddingSmall,
                                start = Dimens.paddingSmall,
                            ),
                        fontSize = FontSizes.bodySmall,
                        fontFamily = FontFamily.SansSerif,
                        color = line.lineColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LineChartPreview() {
    LineChart(
        data = LineChartData(
            title = Strings.LINE_CHART_TITLE,
            lines = listOf(
                LineDataSet(
                    label = "pv",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),  // Page A
                        DataPoint(1f, 1398f),  // Page B
                        DataPoint(2f, 9800f),  // Page C
                        DataPoint(3f, 3908f),  // Page D
                        DataPoint(4f, 4800f),  // Page E
                        DataPoint(5f, 3800f),  // Page F
                        DataPoint(6f, 4300f)   // Page G
                    ),
                    lineColor = Color(0xFF8884d8)  // Blue like Recharts
                ),
                LineDataSet(
                    label = "uv",
                    dataPoints = listOf(
                        DataPoint(0f, 4000f),  // Page A
                        DataPoint(1f, 3000f),  // Page B
                        DataPoint(2f, 2000f),  // Page C
                        DataPoint(3f, 2780f),  // Page D
                        DataPoint(4f, 1890f),  // Page E
                        DataPoint(5f, 2390f),  // Page F
                        DataPoint(6f, 3490f)   // Page G
                    ),
                    lineColor = Color(0xFF82ca9d)  // Green like Recharts
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}
