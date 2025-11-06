package com.majid2851.charts.presentation.line

import com.majid2851.charts.presentation.base.MviViewModel

class LineChartViewModel : MviViewModel<
        LineChartContract.State,
        LineChartContract.Intent,
        LineChartContract.Effect
        >(LineChartContract.State()) {

    override fun handleIntent(intent: LineChartContract.Intent) {
        when (intent) {
            is LineChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is LineChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
            is LineChartContract.Intent.SelectDataPoint -> selectDataPoint(intent.lineIndex, intent.pointIndex)
            is LineChartContract.Intent.ClearSelection -> clearSelection()
            is LineChartContract.Intent.RefreshChart -> refreshChart()
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.LineChartData) {
        setState {
            copy(
                chartData = data,
                isLoading = false,
                error = null,
                isAnimating = true
            )
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.LineChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }

    private fun selectDataPoint(lineIndex: Int, pointIndex: Int) {
        setState {
            copy(selectedPointIndex = pointIndex)
        }
    }

    private fun clearSelection() {
        setState {
            copy(selectedPointIndex = null)
        }
    }

    private fun refreshChart() {
        setState {
            copy(isAnimating = true)
        }
    }
}

