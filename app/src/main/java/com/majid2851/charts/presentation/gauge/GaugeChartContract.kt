package com.majid2851.charts.presentation.gauge

import com.majid2851.charts.domain.model.GaugeChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object GaugeChartContract {

    data class State(
        val chartData: GaugeChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val isAnimating: Boolean = false,
        val currentValue: Float = 0f
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: GaugeChartData) : Intent()
        data class UpdateValue(val value: Float) : Intent()
        data class UpdateChartData(val data: GaugeChartData) : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowError(val error: String) : Effect()
        data class ValueThresholdReached(val threshold: String) : Effect()
    }
}
