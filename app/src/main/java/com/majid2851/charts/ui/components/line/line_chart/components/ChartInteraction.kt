package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.majid2851.charts.domain.model.LineDataSet
import kotlin.math.sqrt


data class SelectedPoint(
    val lineIndex: Int,
    val pointIndex: Int
)

fun findNearestPoint(
    offset: Offset,
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    canvasSize: Size
): SelectedPoint? {
    val touchRadius = 40f
    var nearestPoint: SelectedPoint? = null
    var minDistance = Float.MAX_VALUE

    lines.forEachIndexed { lineIndex, lineDataSet ->
        lineDataSet.dataPoints.forEachIndexed { pointIndex, point ->
            if (point == null) return@forEachIndexed

            val screenX = mapToScreenX(
                value = point.x,
                minValue = bounds.minX,
                maxValue = bounds.maxX,
                width = canvasSize.width,
                padding = padding
            )
            val screenY = mapToScreenY(
                value = point.y,
                minValue = bounds.minY,
                maxValue = bounds.maxY,
                height = canvasSize.height,
                padding = padding
            )

            val distance = sqrt(
                (offset.x - screenX) * (offset.x - screenX) +
                (offset.y - screenY) * (offset.y - screenY)
            )

            if (distance < touchRadius && distance < minDistance) {
                minDistance = distance
                nearestPoint = SelectedPoint(lineIndex, pointIndex)
            }
        }
    }

    return nearestPoint
}

/**
 * Finds the nearest point based on X-axis position only (ignores Y)
 * This is useful for showing crosshair/tooltip while dragging
 * The user can touch anywhere vertically and it will snap to the nearest X position
 */
fun findNearestPointByX(
    offset: Offset,
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    canvasSize: Size
): SelectedPoint? {
    if (lines.isEmpty()) return null
    
    // Only consider touches within the chart area
    if (offset.x < padding || offset.x > canvasSize.width - padding) {
        return null
    }
    
    var nearestPoint: SelectedPoint? = null
    var minXDistance = Float.MAX_VALUE
    
    // Find the closest point on the X-axis across all lines
    lines.forEachIndexed { lineIndex, lineDataSet ->
        lineDataSet.dataPoints.forEachIndexed { pointIndex, point ->
            if (point == null) return@forEachIndexed
            
            val screenX = mapToScreenX(
                value = point.x,
                minValue = bounds.minX,
                maxValue = bounds.maxX,
                width = canvasSize.width,
                padding = padding
            )
            
            val xDistance = kotlin.math.abs(offset.x - screenX)
            
            if (xDistance < minXDistance) {
                minXDistance = xDistance
                nearestPoint = SelectedPoint(lineIndex, pointIndex)
            }
        }
    }
    
    return nearestPoint
}

/**
 * Finds all points at the nearest X position across all lines
 * Useful for showing multiple values at the same X coordinate
 */
fun findAllPointsAtNearestX(
    offset: Offset,
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    canvasSize: Size
): List<SelectedPoint> {
    if (lines.isEmpty()) return emptyList()
    
    // Only consider touches within the chart area
    if (offset.x < padding || offset.x > canvasSize.width - padding) {
        return emptyList()
    }
    
    // First find the nearest X position
    var nearestXValue: Float? = null
    var minXDistance = Float.MAX_VALUE
    
    lines.forEach { lineDataSet ->
        lineDataSet.dataPoints.forEach { point ->
            if (point == null) return@forEach
            
            val screenX = mapToScreenX(
                value = point.x,
                minValue = bounds.minX,
                maxValue = bounds.maxX,
                width = canvasSize.width,
                padding = padding
            )
            
            val xDistance = kotlin.math.abs(offset.x - screenX)
            
            if (xDistance < minXDistance) {
                minXDistance = xDistance
                nearestXValue = point.x
            }
        }
    }
    
    // Now find all points at that X value
    val result = mutableListOf<SelectedPoint>()
    nearestXValue?.let { xValue ->
        lines.forEachIndexed { lineIndex, lineDataSet ->
            lineDataSet.dataPoints.forEachIndexed { pointIndex, point ->
                if (point?.x == xValue) {
                    result.add(SelectedPoint(lineIndex, pointIndex))
                }
            }
        }
    }
    
    return result
}




