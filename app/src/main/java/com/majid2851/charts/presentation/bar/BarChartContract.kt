package com.majid2851.charts.presentation.bar

import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object BarChartContract {

    data class State(
        val chartData: BarChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val selectedBarIndex: Int? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: BarChartData) : Intent()
        data class UpdateChartData(val data: BarChartData) : Intent()
        data class SelectBar(val barIndex: Int) : Intent()
        object ClearSelection : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowToast(val message: String) : Effect()
        data class ShowError(val error: String) : Effect()
    }
}
