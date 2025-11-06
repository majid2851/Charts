package com.majid2851.charts.presentation.radar

import com.majid2851.charts.presentation.base.MviViewModel

class RadarChartViewModel : MviViewModel<
        RadarChartContract.State,
        RadarChartContract.Intent,
        RadarChartContract.Effect
        >(RadarChartContract.State()) {

    override fun handleIntent(intent: RadarChartContract.Intent) {
        when (intent) {
            is RadarChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is RadarChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.RadarChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.RadarChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }
}
