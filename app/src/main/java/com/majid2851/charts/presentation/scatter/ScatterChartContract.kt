package com.majid2851.charts.presentation.scatter

import com.majid2851.charts.domain.model.ScatterChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object ScatterChartContract {

    data class State(
        val chartData: ScatterChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: ScatterChartData) : Intent()
        data class UpdateChartData(val data: ScatterChartData) : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowError(val error: String) : Effect()
    }
}
