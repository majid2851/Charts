package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class CandlestickChartData(
    override val title: String? = null,
    override val description: String? = null,
    val candles: List<CandleEntry>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val bullishColor: Color = Color.Green,
    val bearishColor: Color = Color.Red,
    val showVolume: Boolean = false
) : ChartData

data class CandleEntry(
    val timestamp: Long,
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float,
    val volume: Float = 0f,
    val label: String? = null
) {
    val isBullish: Boolean
        get() = close >= open
}
