package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.majid2851.charts.domain.model.LineDataSet

/**
 * Draws a line connecting data points with optional curves
 * Fixed curve implementation using cubic Bezier curves for smooth lines
 */
fun DrawScope.drawLine(
    lineDataSet: LineDataSet,
    bounds: Bounds,
    padding: Float,
    connectNulls: Boolean = false,
    isSelected: Boolean = false,
    selectedPointIndex: Int? = null
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
                
                // Use cubic Bezier curve with horizontal control points for smooth curves
                // This creates natural, flowing curves similar to Recharts
                val controlX1 = prevX + (currentX - prevX) / 2
                val controlY1 = prevY
                
                val controlX2 = prevX + (currentX - prevX) / 2
                val controlY2 = currentY
                
                path.cubicTo(controlX1, controlY1, controlX2, controlY2, currentX, currentY)
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
    
    val interactionConfig = lineDataSet.interactionConfig
    val lineColor = if (isSelected && interactionConfig.activeLineColor != null) {
        interactionConfig.activeLineColor
    } else {
        lineDataSet.lineColor
    }
    val lineWidth = if (isSelected && interactionConfig.activeLineWidth != null) {
        interactionConfig.activeLineWidth
    } else {
        lineDataSet.lineWidth
    }
    
    drawPath(
        path = path,
        color = lineColor,
        style = Stroke(
            width = lineWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
            pathEffect = pathEffect
        )
    )
}





