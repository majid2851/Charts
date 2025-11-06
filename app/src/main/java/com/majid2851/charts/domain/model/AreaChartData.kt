package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class AreaChartData(
    override val title: String? = null,
    override val description: String? = null,
    val areas: List<AreaDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val stackingMode: AreaStackingMode = AreaStackingMode.NONE
) : ChartData

data class AreaDataSet(
    val label: String,
    val dataPoints: List<DataPoint>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val lineWidth: Float = 2f,
    val isCurved: Boolean = true
)

enum class AreaStackingMode {
    NONE,
    STACKED,
    PERCENTAGE
}
