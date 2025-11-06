package com.majid2851.charts.presentation.bar

import com.majid2851.charts.presentation.base.MviViewModel

class BarChartViewModel : MviViewModel<
        BarChartContract.State,
        BarChartContract.Intent,
        BarChartContract.Effect
        >(BarChartContract.State()) {

    override fun handleIntent(intent: BarChartContract.Intent) {
        when (intent) {
            is BarChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is BarChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
            is BarChartContract.Intent.SelectBar -> selectBar(intent.barIndex)
            is BarChartContract.Intent.ClearSelection -> clearSelection()
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.BarChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.BarChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }

    private fun selectBar(barIndex: Int) {
        setState {
            copy(selectedBarIndex = barIndex)
        }
    }

    private fun clearSelection() {
        setState {
            copy(selectedBarIndex = null)
        }
    }
}
