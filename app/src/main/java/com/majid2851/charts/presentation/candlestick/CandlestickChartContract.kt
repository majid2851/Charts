package com.majid2851.charts.presentation.candlestick

import com.majid2851.charts.domain.model.CandlestickChartData
import com.majid2851.charts.presentation.base.ViewEffect
import com.majid2851.charts.presentation.base.ViewIntent
import com.majid2851.charts.presentation.base.ViewState

object CandlestickChartContract {

    data class State(
        val chartData: CandlestickChartData? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val selectedCandleIndex: Int? = null,
        val isAnimating: Boolean = false,
        val zoomLevel: Float = 1f
    ) : ViewState

    sealed class Intent : ViewIntent {
        data class LoadChartData(val data: CandlestickChartData) : Intent()
        data class UpdateChartData(val data: CandlestickChartData) : Intent()
        data class SelectCandle(val index: Int) : Intent()
        data class ZoomChart(val zoomLevel: Float) : Intent()
        object ClearSelection : Intent()
    }

    sealed class Effect : ViewEffect {
        data class ShowCandleDetails(val index: Int) : Effect()
        data class ShowError(val error: String) : Effect()
    }
}
