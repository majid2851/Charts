package com.majid2851.charts.presentation.gauge

import com.majid2851.charts.presentation.base.MviViewModel

class GaugeChartViewModel : MviViewModel<
        GaugeChartContract.State,
        GaugeChartContract.Intent,
        GaugeChartContract.Effect
        >(GaugeChartContract.State()) {

    override fun handleIntent(intent: GaugeChartContract.Intent) {
        when (intent) {
            is GaugeChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is GaugeChartContract.Intent.UpdateValue -> updateValue(intent.value)
            is GaugeChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.GaugeChartData) {
        setState {
            copy(
                chartData = data,
                currentValue = data.currentValue,
                isLoading = false,
                error = null,
                isAnimating = true
            )
        }
    }

    private fun updateValue(value: Float) {
        setState {
            copy(currentValue = value, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.GaugeChartData) {
        setState {
            copy(chartData = data, currentValue = data.currentValue, isAnimating = true)
        }
    }
}
