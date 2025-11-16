package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.domain.model.PointShape

/**
 * Draws points on the line chart with various shapes
 */
fun DrawScope.drawPoints(
    lineDataSet: LineDataSet,
    bounds: Bounds,
    padding: Float,
    lineIndex: Int,
    selectedPoint: SelectedPoint?
) {
    if (!lineDataSet.showPoints) return
    
    val interactionConfig = lineDataSet.interactionConfig
    
    lineDataSet.dataPoints.forEachIndexed { pointIndex, point ->
        if (point == null) return@forEachIndexed
        
        // Highlight all points at the same X position (same pointIndex) across all lines
        val isPointSelected = selectedPoint?.pointIndex == pointIndex
        
        val x = mapToScreenX(point.x, bounds.minX, bounds.maxX, size.width, padding)
        val y = mapToScreenY(point.y, bounds.minY, bounds.maxY, size.height, padding)
        
        val pointRadius = if (isPointSelected && interactionConfig.enableInteraction) {
            interactionConfig.activePointRadius
        } else {
            lineDataSet.pointRadius
        }
        
        val pointColor = if (isPointSelected && interactionConfig.enableInteraction && interactionConfig.activePointColor != null) {
            interactionConfig.activePointColor
        } else {
            lineDataSet.lineColor
        }
        
        when (lineDataSet.customPointShape) {
            PointShape.CIRCLE -> {
                drawCircle(
                    color = pointColor,
                    radius = pointRadius,
                    center = Offset(x, y)
                )
                // Only draw white center if not selected (when selected, point is filled)
                if (!isPointSelected || !interactionConfig.enableInteraction) {
                    drawCircle(
                        color = Color.White,
                        radius = pointRadius / 2,
                        center = Offset(x, y)
                    )
                }
            }
            PointShape.SQUARE -> {
                val size = pointRadius * 2
                drawRect(
                    color = pointColor,
                    topLeft = Offset(x - pointRadius, y - pointRadius),
                    size = androidx.compose.ui.geometry.Size(size, size)
                )
                // Only draw white center if not selected
                if (!isPointSelected || !interactionConfig.enableInteraction) {
                    drawRect(
                        color = Color.White,
                        topLeft = Offset(x - pointRadius / 2, y - pointRadius / 2),
                        size = androidx.compose.ui.geometry.Size(pointRadius, pointRadius)
                    )
                }
            }
            PointShape.TRIANGLE -> {
                val path = Path().apply {
                    moveTo(x, y - pointRadius)
                    lineTo(x + pointRadius, y + pointRadius)
                    lineTo(x - pointRadius, y + pointRadius)
                    close()
                }
                drawPath(path, pointColor)
            }
            PointShape.DIAMOND -> {
                val path = Path().apply {
                    moveTo(x, y - pointRadius)
                    lineTo(x + pointRadius, y)
                    lineTo(x, y + pointRadius)
                    lineTo(x - pointRadius, y)
                    close()
                }
                drawPath(path, pointColor)
                // Only draw white center if not selected
                if (!isPointSelected || !interactionConfig.enableInteraction) {
                    val innerPath = Path().apply {
                        val innerRadius = pointRadius / 2
                        moveTo(x, y - innerRadius)
                        lineTo(x + innerRadius, y)
                        lineTo(x, y + innerRadius)
                        lineTo(x - innerRadius, y)
                        close()
                    }
                    drawPath(innerPath, Color.White)
                }
            }
            PointShape.CROSS -> {
                drawLine(
                    color = pointColor,
                    start = Offset(x - pointRadius, y),
                    end = Offset(x + pointRadius, y),
                    strokeWidth = 2f
                )
                drawLine(
                    color = pointColor,
                    start = Offset(x, y - pointRadius),
                    end = Offset(x, y + pointRadius),
                    strokeWidth = 2f
                )
            }
            PointShape.STAR -> {
                val path = Path()
                val outerRadius = pointRadius
                val innerRadius = pointRadius / 2
                val points = 5
                for (i in 0 until points * 2) {
                    val radius = if (i % 2 == 0) outerRadius else innerRadius
                    val angle = Math.PI * i / points - Math.PI / 2
                    val px = x + (radius * kotlin.math.cos(angle)).toFloat()
                    val py = y + (radius * kotlin.math.sin(angle)).toFloat()
                    if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
                }
                path.close()
                drawPath(path, pointColor)
            }
        }
    }
}

