package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import com.majid2851.charts.domain.model.LineDataSet

/**
 * Represents the bounds of the chart data
 */
data class Bounds(
    val minX: Float,
    val maxX: Float,
    val minY: Float,
    val maxY: Float
) {
    /**
     * Applies zoom and pan transformations to the bounds
     */
    fun applyZoomAndPan(
        zoomState: ZoomState,
        canvasWidth: Float,
        canvasHeight: Float,
        padding: Float
    ): Bounds {
        val zoom = zoomState.zoom
        val pan = zoomState.panOffset
        
        // Calculate the visible range based on zoom
        val xRange = (maxX - minX) / zoom
        val yRange = (maxY - minY) / zoom
        
        // Calculate pan offset in data coordinates
        val chartWidth = canvasWidth - 2 * padding
        val chartHeight = canvasHeight - 2 * padding
        
        val xPanOffset = -pan.x / chartWidth * (maxX - minX) / zoom
        val yPanOffset = pan.y / chartHeight * (maxY - minY) / zoom
        
        // Calculate center point
        val centerX = (minX + maxX) / 2
        val centerY = (minY + maxY) / 2
        
        // Calculate new bounds centered around the pan offset
        val newMinX = centerX - xRange / 2 + xPanOffset
        val newMaxX = centerX + xRange / 2 + xPanOffset
        val newMinY = centerY - yRange / 2 + yPanOffset
        val newMaxY = centerY + yRange / 2 + yPanOffset
        
        return Bounds(
            minX = newMinX,
            maxX = newMaxX,
            minY = newMinY,
            maxY = newMaxY
        )
    }
    
    /**
     * Returns the width of the bounds
     */
    val width: Float
        get() = maxX - minX
    
    /**
     * Returns the height of the bounds
     */
    val height: Float
        get() = maxY - minY
}

/**
 * Calculates the bounds (min/max X and Y values) from the line data sets
 */
fun calculateBounds(lines: List<LineDataSet>): Bounds {
    if (lines.isEmpty()) {
        return Bounds(0f, 1f, 0f, 1f)
    }
    
    val allPoints = lines.flatMap { it.dataPoints }.filterNotNull()
    
    if (allPoints.isEmpty()) {
        return Bounds(0f, 1f, 0f, 1f)
    }
    
    val minX = allPoints.minOfOrNull { it.x } ?: 0f
    val maxX = allPoints.maxOfOrNull { it.x } ?: 1f
    val minY = allPoints.minOfOrNull { it.y } ?: 0f
    val maxY = allPoints.maxOfOrNull { it.y } ?: 1f
    
    // Add some padding to Y axis for better visualization
    val yPadding = (maxY - minY) * 0.1f
    
    return Bounds(
        minX = minX,
        maxX = maxX,
        minY = (minY - yPadding).coerceAtLeast(0f),
        maxY = maxY + yPadding
    )
}

