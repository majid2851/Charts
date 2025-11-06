package com.majid2851.charts.presentation.pie

import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object PieChartContract {

    data class State(
        val chartData: PieChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val selectedSliceIndex: Int? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: PieChartData) : Intent()
        data class UpdateChartData(val data: PieChartData) : Intent()
        data class SelectSlice(val sliceIndex: Int) : Intent()
        object ClearSelection : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowSliceDetails(val sliceIndex: Int, val value: Float) : Effect()
        data class ShowError(val error: String) : Effect()
    }
}
