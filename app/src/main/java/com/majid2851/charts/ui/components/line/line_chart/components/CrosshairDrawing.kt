package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.domain.model.LineDataSet

/**
 * Draws a vertical line at the selected point
 */
fun DrawScope.drawSelectedPointVerticalLine(
    selectedPoint: SelectedPoint,
    dataPoint: DataPoint,
    bounds: Bounds,
    padding: Float,
    lineColor: Color = Color.Black.copy(alpha = 0.7f),
    strokeWidth: Float = 2f,
    isDashed: Boolean = false
) {
    val x = mapToScreenX(dataPoint.x, bounds.minX, bounds.maxX, size.width, padding)
    
    val pathEffect = if (isDashed) {
        PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f)
    } else null
    
    // Draw vertical line only
    drawLine(
        color = lineColor,
        start = Offset(x, padding),
        end = Offset(x, size.height - padding),
        strokeWidth = strokeWidth,
        pathEffect = pathEffect
    )
}

/**
 * Draws a regular grid independent of data points - evenly spaced lines
 */
fun DrawScope.drawDataPointGridLines(
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    verticalLineColor: Color = Color.Gray.copy(alpha = 0.6f),
    horizontalLineColor: Color = Color.Gray.copy(alpha = 0.6f),
    strokeWidth: Float = 1.5f,
    isDashed: Boolean = true,
    verticalLineCount: Int = 7,    // Number of vertical grid lines
    horizontalLineCount: Int = 5   // Number of horizontal grid lines
) {
    val pathEffect = if (isDashed) {
        PathEffect.dashPathEffect(floatArrayOf(3f, 6f), 0f)  // Dotted pattern: 3px dot, 6px gap
    } else null
    
    val chartWidth = size.width - 2 * padding
    val chartHeight = size.height - 2 * padding
    
    // Draw vertical lines evenly spaced
    for (i in 0 until verticalLineCount) {
        val x = padding + (chartWidth * i / (verticalLineCount - 1).coerceAtLeast(1))
        drawLine(
            color = verticalLineColor,
            start = Offset(x, padding),
            end = Offset(x, size.height - padding),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
    
    // Draw horizontal lines evenly spaced
    for (i in 0 until horizontalLineCount) {
        val y = padding + (chartHeight * i / (horizontalLineCount - 1).coerceAtLeast(1))
        drawLine(
            color = horizontalLineColor,
            start = Offset(padding, y),
            end = Offset(size.width - padding, y),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}

