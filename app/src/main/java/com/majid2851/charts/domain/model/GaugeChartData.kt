package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class GaugeChartData(
    override val title: String? = null,
    override val description: String? = null,
    val currentValue: Float,
    val minValue: Float = 0f,
    val maxValue: Float = 100f,
    val config: ChartConfig = ChartConfig(),
    val gaugeStyle: GaugeStyle = GaugeStyle.SEMI_CIRCLE,
    val ranges: List<GaugeRange> = emptyList(),
    val showNeedle: Boolean = true,
    val showValue: Boolean = true
) : ChartData

data class GaugeRange(
    val startValue: Float,
    val endValue: Float,
    val color: Color,
    val label: String? = null
)

enum class GaugeStyle {
    SEMI_CIRCLE,
    FULL_CIRCLE,
    LINEAR
}
