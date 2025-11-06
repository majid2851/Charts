package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ChartConfig(
    val showGrid: Boolean = true,
    val showAxis: Boolean = true,
    val showLegend: Boolean = true,
    val animationEnabled: Boolean = true,
    val animationDuration: Int = 300,
    val backgroundColor: Color = Color.Transparent,
    val chartPadding: Dp = 16.dp,
    val isInteractive: Boolean = true
)

data class AxisConfig(
    val showLabels: Boolean = true,
    val showGridLines: Boolean = true,
    val labelCount: Int = 5,
    val axisColor: Color = Color.Gray,
    val gridColor: Color = Color.LightGray,
    val labelTextSize: Float = 12f
)

data class LegendConfig(
    val position: LegendPosition = LegendPosition.BOTTOM,
    val orientation: LegendOrientation = LegendOrientation.HORIZONTAL,
    val textColor: Color = Color.Black,
    val textSize: Float = 12f
)

enum class LegendPosition {
    TOP, BOTTOM, LEFT, RIGHT
}

enum class LegendOrientation {
    HORIZONTAL, VERTICAL
}

