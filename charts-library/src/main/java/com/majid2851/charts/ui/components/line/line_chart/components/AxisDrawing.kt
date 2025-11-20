package com.majid2851.charts.ui.components.line.line_chart.components

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import com.majid2851.charts.domain.model.DataPoint

fun DrawScope.drawAxes(
    bounds: Bounds,
    axisColor: Color,
    padding: Float
) {
    // Y-axis (vertical line on the left) - extends through entire chart height
    // including negative values region
    drawLine(
        color = axisColor,
        start = Offset(padding, padding),
        end = Offset(padding, size.height - padding),
        strokeWidth = 2f
    )
    
    // X-axis - draw at y=0 if data includes negative values, otherwise at bottom
    val xAxisY = if (bounds.minY < 0 && bounds.maxY > 0) {
        // Data crosses zero, draw X-axis at y=0
        mapToScreenY(0f, bounds.minY, bounds.maxY, size.height, padding)
    } else if (bounds.maxY <= 0) {
        // All negative data, draw at top (which represents the maximum negative value)
        padding
    } else {
        // All positive data, draw at bottom
        size.height - padding
    }
    
    drawLine(
        color = axisColor,
        start = Offset(padding, xAxisY),
        end = Offset(size.width - padding, xAxisY),
        strokeWidth = 2f
    )
}

/**
 * Draws Y-axis labels
 */
fun DrawScope.drawYAxisLabels(
    bounds: Bounds,
    labelCount: Int,
    labelColor: Color,
    labelTextSize: Float,
    padding: Float
) {
    val paint = Paint().apply {
        color = labelColor.toArgb()
        textSize = labelTextSize
        textAlign = Paint.Align.RIGHT
    }
    
    drawIntoCanvas { canvas ->
        for (i in 0 until labelCount) {
            val value = bounds.minY + (bounds.maxY - bounds.minY) *
                    i / (labelCount - 1).coerceAtLeast(1)
            val y = size.height - padding - (size.height - 2 * padding) *
                    i / (labelCount - 1).coerceAtLeast(1)
            
            val label = when {
                value >= 1000 -> String.format("%.1fk", value / 1000)
                value <= -1000 -> String.format("%.1fk", value / 1000)
                kotlin.math.abs(value) < 0.1 -> "0" // Handle near-zero values
                else -> String.format("%.0f", value)
            }
            
            canvas.nativeCanvas.drawText(
                label,
                padding - 10f,
                y + labelTextSize / 3,
                paint
            )
        }
    }
}

fun DrawScope.drawXAxisLabels(
    dataPoints: List<DataPoint?>,
    bounds: Bounds,
    labelColor: Color,
    labelTextSize: Float,
    padding: Float
) {
    val paint = Paint().apply {
        color = labelColor.toArgb()
        textSize = labelTextSize
        textAlign = Paint.Align.CENTER
    }
    
    // Determine X-axis position for label placement
    val xAxisY = if (bounds.minY < 0 && bounds.maxY > 0) {
        // Data crosses zero, X-axis is at y=0
        mapToScreenY(0f, bounds.minY, bounds.maxY, size.height, padding)
    } else {
        // All positive or all negative, use bottom
        size.height - padding
    }
    
    drawIntoCanvas { canvas ->
        dataPoints.forEachIndexed { index, point ->
            point?.let {
                if (it.label != null) {
                    val x = mapToScreenX(it.x, bounds.minX, bounds.maxX, size.width, padding)
                    // Position labels below the X-axis line
                    val y = xAxisY + labelTextSize + 10f
                    
                    canvas.nativeCanvas.drawText(
                        it.label,
                        x,
                        y,
                        paint
                    )
                }
            }
        }
    }
}








