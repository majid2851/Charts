package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.majid2851.charts.domain.model.ReferenceLine

/**
 * Draws reference lines (horizontal or vertical lines at specific values)
 */
fun DrawScope.drawReferenceLines(
    referenceLines: List<ReferenceLine>,
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












