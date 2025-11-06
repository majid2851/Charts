package com.majid2851.charts.presentation.scatter

import com.majid2851.charts.presentation.base.MviViewModel

class ScatterChartViewModel : MviViewModel<
        ScatterChartContract.State,
        ScatterChartContract.Intent,
        ScatterChartContract.Effect
        >(ScatterChartContract.State()) {

    override fun handleIntent(intent: ScatterChartContract.Intent) {
        when (intent) {
            is ScatterChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is ScatterChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.ScatterChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.ScatterChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }
}
