package com.majid2851.charts.presentation.radar

import com.majid2851.charts.domain.model.RadarChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object RadarChartContract {

    data class State(
        val chartData: RadarChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: RadarChartData) : Intent()
        data class UpdateChartData(val data: RadarChartData) : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowError(val error: String) : Effect()
    }
}
