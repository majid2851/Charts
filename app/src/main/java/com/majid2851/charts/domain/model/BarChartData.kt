package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class BarChartData(
    override val title: String? = null,
    override val description: String? = null,
    val bars: List<BarDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val barStyle: BarStyle = BarStyle.VERTICAL,
    val groupingMode: BarGroupingMode = BarGroupingMode.GROUPED
) : ChartData

data class BarDataSet(
    val label: String,
    val entries: List<LabeledEntry>,
    val barColor: Color = Color.Blue,
    val barWidth: Float = 0.8f
)

enum class BarStyle {
    VERTICAL,
    HORIZONTAL
}

enum class BarGroupingMode {
    GROUPED,
    STACKED
}
