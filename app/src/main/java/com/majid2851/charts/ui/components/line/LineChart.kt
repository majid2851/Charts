package com.majid2851.charts.ui.components.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.CartesianGridConfig
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.domain.model.GridLineStyle
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun LineChart(
    data: LineChartData,
    modifier: Modifier = Modifier
) {
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
            val bounds = remember(data.lines) { calculateBounds(data.lines) }
            
            ChartCanvas(modifier = Modifier.fillMaxSize()) {
                val padding = 40f

                if (data.config.showGrid) {
                    drawCartesianGrid(
                        bounds = bounds,
                        gridConfig = data.config.cartesianGrid,
                        padding = padding
                    )
                }

                if (data.config.showAxis) {
                    drawAxes(bounds, data.xAxisConfig.axisColor, padding)
                }

                if (data.referenceLines.isNotEmpty()) {
                    drawReferenceLines(data.referenceLines, bounds, padding)
                }

                data.lines.forEach { lineDataSet ->
                    drawLine(lineDataSet, bounds, padding, data.connectNulls)
                    drawPoints(lineDataSet, bounds, padding)
                }
            }
        }

        if (data.config.showLegend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center
            ) {
                data.lines.forEach { line ->
                    Text(
                        text = line.label,
                        modifier=Modifier
                            .padding(
                                end=Dimens.paddingSmall,
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

private data class Bounds(
    val minX: Float,
    val maxX: Float,
    val minY: Float,
    val maxY: Float
)

private fun calculateBounds(lines: List<LineDataSet>): Bounds {
    val allPoints = lines.flatMap { it.dataPoints }.filterNotNull()
    
    if (allPoints.isEmpty()) {
        return Bounds(0f, 1f, 0f, 1f)
    }
    
    val minX = allPoints.minOfOrNull { it.x } ?: 0f
    val maxX = allPoints.maxOfOrNull { it.x } ?: 0f
    val minY = allPoints.minOfOrNull { it.y } ?: 0f
    val maxY = allPoints.maxOfOrNull { it.y } ?: 0f
    
    // Add padding (10% on each side for Y axis)
    val yPadding = if (maxY - minY > 0) (maxY - minY) * 0.1f else 1f
    
    return Bounds(
        minX = minX,
        maxX = maxX,
        minY = minY - yPadding,
        maxY = maxY + yPadding
    )
}

private fun mapToScreenX(
    value: Float,
    minValue: Float,
    maxValue: Float,
    width: Float,
    padding: Float
): Float {
    if (maxValue == minValue) return padding + width / 2
    return padding + (value - minValue) / (maxValue - minValue) * (width - 2 * padding)
}

// Map data Y value to screen Y coordinate (flipped because canvas Y goes down)
private fun mapToScreenY(
    value: Float,
    minValue: Float,
    maxValue: Float,
    height: Float,
    padding: Float
): Float {
    if (maxValue == minValue) return padding + height / 2
    return height - padding - (value - minValue) / (maxValue - minValue) * (height - 2 * padding)
}

private fun DrawScope.drawCartesianGrid(
    bounds: Bounds,
    gridConfig: CartesianGridConfig,
    padding: Float
) {
    if (gridConfig.showHorizontalLines) {
        val horizontalPathEffect = when (gridConfig.horizontalLineStyle) {
            GridLineStyle.SOLID -> null
            GridLineStyle.DASHED -> PathEffect.dashPathEffect(gridConfig.horizontalDashPattern, 0f)
            GridLineStyle.DOTTED -> PathEffect.dashPathEffect(floatArrayOf(2f, 4f), 0f)
        }
        
        for (i in 0 until gridConfig.horizontalLineCount) {
            val y = padding + (size.height - 2 * padding) * i / (gridConfig.horizontalLineCount - 1).coerceAtLeast(1)
            drawLine(
                color = gridConfig.horizontalLineColor.copy(alpha = gridConfig.horizontalLineAlpha),
                start = Offset(padding, y),
                end = Offset(size.width - padding, y),
                pathEffect = horizontalPathEffect,
                strokeWidth = gridConfig.horizontalLineWidth
            )
        }
    }
    
    if (gridConfig.showVerticalLines) {
        val verticalPathEffect = when (gridConfig.verticalLineStyle) {
            GridLineStyle.SOLID -> null
            GridLineStyle.DASHED -> PathEffect.dashPathEffect(gridConfig.verticalDashPattern, 0f)
            GridLineStyle.DOTTED -> PathEffect.dashPathEffect(floatArrayOf(2f, 4f), 0f)
        }
        
        for (i in 0 until gridConfig.verticalLineCount) {
            val x = padding + (size.width - 2 * padding) * i / (gridConfig.verticalLineCount - 1).coerceAtLeast(1)
            drawLine(
                color = gridConfig.verticalLineColor.copy(alpha = gridConfig.verticalLineAlpha),
                start = Offset(x, padding),
                end = Offset(x, size.height - padding),
                pathEffect = verticalPathEffect,
                strokeWidth = gridConfig.verticalLineWidth
            )
        }
    }
}

// Draw X and Y axes
private fun DrawScope.drawAxes(
    bounds: Bounds,
    axisColor: Color,
    padding: Float
) {
    // Y-axis
    drawLine(
        color = axisColor,
        start = Offset(padding, padding),
        end = Offset(padding, size.height - padding),
        strokeWidth = 2f
    )
    
    // X-axis
    drawLine(
        color = axisColor,
        start = Offset(padding, size.height - padding),
        end = Offset(size.width - padding, size.height - padding),
        strokeWidth = 2f
    )
}

// Draw a single line dataset
private fun DrawScope.drawLine(
    lineDataSet: LineDataSet,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean = false
) {
    val points = lineDataSet.dataPoints
    
    if (points.isEmpty()) return
    
    val path = Path()
    val firstNonNull = points.firstOrNull { it != null } ?: return
    
    // Move to first non-null point
    var firstX = mapToScreenX(firstNonNull.x, bounds.minX, bounds.maxX, size.width, padding)
    var firstY = mapToScreenY(firstNonNull.y, bounds.minY, bounds.maxY, size.height, padding)
    path.moveTo(firstX, firstY)
    
    var lastNonNullIndex = points.indexOfFirst { it != null }
    
    // Draw lines to subsequent points
    for (i in (lastNonNullIndex + 1) until points.size) {
        val point = points[i]
        
        if (point == null) {
            if (!connectNulls) {
                // Start a new path segment after null
                val nextNonNull = points.drop(i + 1).firstOrNull { it != null }
                if (nextNonNull != null) {
                    val nextX = mapToScreenX(nextNonNull.x, bounds.minX, bounds.maxX, size.width, padding)
                    val nextY = mapToScreenY(nextNonNull.y, bounds.minY, bounds.maxY, size.height, padding)
                    
                    // Draw current segment
                    val pathEffect = if (lineDataSet.isDashed) {
                        PathEffect.dashPathEffect(lineDataSet.dashPattern, 0f)
                    } else null
                    
                    drawPath(
                        path = path,
                        color = lineDataSet.lineColor,
                        style = Stroke(
                            width = lineDataSet.lineWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                            pathEffect = pathEffect
                        )
                    )
                    
                    // Start new segment
                    path.reset()
                    path.moveTo(nextX, nextY)
                    lastNonNullIndex = i + 1 + points.drop(i + 1).indexOfFirst { it != null }
                }
            }
            continue
        }
        
        val currentX = mapToScreenX(point.x, bounds.minX, bounds.maxX, size.width, padding)
        val currentY = mapToScreenY(point.y, bounds.minY, bounds.maxY, size.height, padding)
        
        if (lineDataSet.isCurved && lastNonNullIndex >= 0) {
            val prevPoint = points[lastNonNullIndex]
            if (prevPoint != null) {
                val prevX = mapToScreenX(prevPoint.x, bounds.minX, bounds.maxX, size.width, padding)
                val prevY = mapToScreenY(prevPoint.y, bounds.minY, bounds.maxY, size.height, padding)
                
                val controlX = (prevX + currentX) / 2
                val controlY = (prevY + currentY) / 2
                
                path.quadraticBezierTo(controlX, controlY, currentX, currentY)
            }
        } else {
            path.lineTo(currentX, currentY)
        }
        
        lastNonNullIndex = i
    }
    
    // Draw filled area if enabled
    if (lineDataSet.fillArea && points.isNotEmpty()) {
        val lastNonNull = points.lastOrNull { it != null }
        if (lastNonNull != null && firstNonNull != null) {
            val fillPath = Path().apply {
                addPath(path)
                val lastX = mapToScreenX(lastNonNull.x, bounds.minX, bounds.maxX, size.width, padding)
                lineTo(lastX, size.height - padding)
                lineTo(firstX, size.height - padding)
                close()
            }
            drawPath(
                path = fillPath,
                color = lineDataSet.fillColor
            )
        }
    }
    
    // Draw the line with optional dash pattern
    val pathEffect = if (lineDataSet.isDashed) {
        PathEffect.dashPathEffect(lineDataSet.dashPattern, 0f)
    } else null
    
    drawPath(
        path = path,
        color = lineDataSet.lineColor,
        style = Stroke(
            width = lineDataSet.lineWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
            pathEffect = pathEffect
        )
    )
}

// Draw points on the line
private fun DrawScope.drawPoints(
    lineDataSet: LineDataSet,
    bounds: Bounds,
    padding: Float
) {
    if (!lineDataSet.showPoints) return
    
    lineDataSet.dataPoints.forEach { point ->
        if (point == null) return@forEach
        
        val x = mapToScreenX(point.x, bounds.minX, bounds.maxX, size.width, padding)
        val y = mapToScreenY(point.y, bounds.minY, bounds.maxY, size.height, padding)
        
        when (lineDataSet.customPointShape) {
            com.majid2851.charts.domain.model.PointShape.CIRCLE -> {
                drawCircle(
                    color = lineDataSet.lineColor,
                    radius = lineDataSet.pointRadius,
                    center = Offset(x, y)
                )
                drawCircle(
                    color = Color.White,
                    radius = lineDataSet.pointRadius / 2,
                    center = Offset(x, y)
                )
            }
            com.majid2851.charts.domain.model.PointShape.SQUARE -> {
                val size = lineDataSet.pointRadius * 2
                drawRect(
                    color = lineDataSet.lineColor,
                    topLeft = Offset(x - lineDataSet.pointRadius, y - lineDataSet.pointRadius),
                    size = androidx.compose.ui.geometry.Size(size, size)
                )
                drawRect(
                    color = Color.White,
                    topLeft = Offset(x - lineDataSet.pointRadius / 2, y - lineDataSet.pointRadius / 2),
                    size = androidx.compose.ui.geometry.Size(lineDataSet.pointRadius, lineDataSet.pointRadius)
                )
            }
            com.majid2851.charts.domain.model.PointShape.TRIANGLE -> {
                val path = Path().apply {
                    moveTo(x, y - lineDataSet.pointRadius)
                    lineTo(x + lineDataSet.pointRadius, y + lineDataSet.pointRadius)
                    lineTo(x - lineDataSet.pointRadius, y + lineDataSet.pointRadius)
                    close()
                }
                drawPath(path, lineDataSet.lineColor)
            }
            com.majid2851.charts.domain.model.PointShape.DIAMOND -> {
                val path = Path().apply {
                    moveTo(x, y - lineDataSet.pointRadius)
                    lineTo(x + lineDataSet.pointRadius, y)
                    lineTo(x, y + lineDataSet.pointRadius)
                    lineTo(x - lineDataSet.pointRadius, y)
                    close()
                }
                drawPath(path, lineDataSet.lineColor)
                val innerPath = Path().apply {
                    val innerRadius = lineDataSet.pointRadius / 2
                    moveTo(x, y - innerRadius)
                    lineTo(x + innerRadius, y)
                    lineTo(x, y + innerRadius)
                    lineTo(x - innerRadius, y)
                    close()
                }
                drawPath(innerPath, Color.White)
            }
            com.majid2851.charts.domain.model.PointShape.CROSS -> {
                val r = lineDataSet.pointRadius
                drawLine(
                    color = lineDataSet.lineColor,
                    start = Offset(x - r, y),
                    end = Offset(x + r, y),
                    strokeWidth = 2f
                )
                drawLine(
                    color = lineDataSet.lineColor,
                    start = Offset(x, y - r),
                    end = Offset(x, y + r),
                    strokeWidth = 2f
                )
            }
            com.majid2851.charts.domain.model.PointShape.STAR -> {
                val path = Path()
                val outerRadius = lineDataSet.pointRadius
                val innerRadius = lineDataSet.pointRadius / 2
                val points = 5
                for (i in 0 until points * 2) {
                    val radius = if (i % 2 == 0) outerRadius else innerRadius
                    val angle = Math.PI * i / points - Math.PI / 2
                    val px = x + (radius * kotlin.math.cos(angle)).toFloat()
                    val py = y + (radius * kotlin.math.sin(angle)).toFloat()
                    if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
                }
                path.close()
                drawPath(path, lineDataSet.lineColor)
            }
        }
    }
}

// Draw reference lines
private fun DrawScope.drawReferenceLines(
    referenceLines: List<com.majid2851.charts.domain.model.ReferenceLine>,
    bounds: Bounds,
    padding: Float
) {
    referenceLines.forEach { refLine ->
        val pathEffect = if (refLine.isDashed) {
            PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f)
        } else null
        
        if (refLine.isVertical) {
            // Vertical reference line
            val x = mapToScreenX(refLine.value, bounds.minX, bounds.maxX, size.width, padding)
            drawLine(
                color = refLine.color,
                start = Offset(x, padding),
                end = Offset(x, size.height - padding),
                strokeWidth = refLine.strokeWidth,
                pathEffect = pathEffect
            )
        } else {
            // Horizontal reference line
            val y = mapToScreenY(refLine.value, bounds.minY, bounds.maxY, size.height, padding)
            drawLine(
                color = refLine.color,
                start = Offset(padding, y),
                end = Offset(size.width - padding, y),
                strokeWidth = refLine.strokeWidth,
                pathEffect = pathEffect
            )
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

