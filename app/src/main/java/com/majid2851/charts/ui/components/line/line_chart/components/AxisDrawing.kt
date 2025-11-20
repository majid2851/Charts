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
    drawLine(
        color = axisColor,
        start = Offset(padding, padding),
        end = Offset(padding, size.height - padding),
        strokeWidth = 2f
    )
    drawLine(
        color = axisColor,
        start = Offset(padding, size.height - padding),
        end = Offset(size.width - padding, size.height - padding),
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
            
            val label = if (value >= 1000) {
                String.format("%.1fk", value / 1000)
            } else {
                String.format("%.0f", value)
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
    
    drawIntoCanvas { canvas ->
        dataPoints.forEachIndexed { index, point ->
            point?.let {
                if (it.label != null) {
                    val x = mapToScreenX(it.x, bounds.minX, bounds.maxX, size.width, padding)
                    val y = size.height - padding + labelTextSize + 10f
                    
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









