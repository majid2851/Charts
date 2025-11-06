package com.majid2851.charts.presentation.area

import com.majid2851.charts.domain.model.AreaChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object AreaChartContract {

    data class State(
        val chartData: AreaChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: AreaChartData) : Intent()
        data class UpdateChartData(val data: AreaChartData) : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowError(val error: String) : Effect()
    }
}
