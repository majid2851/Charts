package com.majid2851.charts.presentation.area

import com.majid2851.charts.presentation.base.MviViewModel

class AreaChartViewModel : MviViewModel<
        AreaChartContract.State,
        AreaChartContract.Intent,
        AreaChartContract.Effect
        >(AreaChartContract.State()) {

    override fun handleIntent(intent: AreaChartContract.Intent) {
        when (intent) {
            is AreaChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is AreaChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.AreaChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.AreaChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }
}
