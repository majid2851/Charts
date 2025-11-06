package com.majid2851.charts.presentation.candlestick

import com.majid2851.charts.presentation.base.MviViewModel

class CandlestickChartViewModel : MviViewModel<
        CandlestickChartContract.State,
        CandlestickChartContract.Intent,
        CandlestickChartContract.Effect
        >(CandlestickChartContract.State()) {

    override fun handleIntent(intent: CandlestickChartContract.Intent) {
        when (intent) {
            is CandlestickChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is CandlestickChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
            is CandlestickChartContract.Intent.SelectCandle -> selectCandle(intent.index)
            is CandlestickChartContract.Intent.ZoomChart -> zoomChart(intent.zoomLevel)
            is CandlestickChartContract.Intent.ClearSelection -> clearSelection()
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.CandlestickChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.CandlestickChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }

    private fun selectCandle(index: Int) {
        setState {
            copy(selectedCandleIndex = index)
        }
        sendEffect(CandlestickChartContract.Effect.ShowCandleDetails(index))
    }

    private fun zoomChart(zoomLevel: Float) {
        setState {
            copy(zoomLevel = zoomLevel)
        }
    }

    private fun clearSelection() {
        setState {
            copy(selectedCandleIndex = null)
        }
    }
}
