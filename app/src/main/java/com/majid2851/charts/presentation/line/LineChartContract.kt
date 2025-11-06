package com.majid2851.charts.presentation.line

import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object LineChartContract {

    data class State(
        val chartData: LineChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val selectedPointIndex: Int? = null,
        val isAnimating: Boolean = false
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: LineChartData) : Intent()
        data class UpdateChartData(val data: LineChartData) : Intent()
        data class SelectDataPoint(val lineIndex: Int, val pointIndex: Int) : Intent()
        object ClearSelection : Intent()
        object RefreshChart : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowToast(val message: String) : Effect()
        data class ShowError(val error: String) : Effect()
        object NavigateBack : Effect()
    }
}

