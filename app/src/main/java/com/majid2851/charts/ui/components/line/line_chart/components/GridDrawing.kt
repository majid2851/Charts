package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.majid2851.charts.domain.model.CartesianGridConfig
import com.majid2851.charts.domain.model.GridLineStyle

fun DrawScope.drawCartesianGrid(
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




