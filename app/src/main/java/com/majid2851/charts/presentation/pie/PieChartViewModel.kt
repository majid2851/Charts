package com.majid2851.charts.presentation.pie

import com.majid2851.charts.presentation.base.MviViewModel

class PieChartViewModel : MviViewModel<
        PieChartContract.State,
        PieChartContract.Intent,
        PieChartContract.Effect
        >(PieChartContract.State()) {

    override fun handleIntent(intent: PieChartContract.Intent) {
        when (intent) {
            is PieChartContract.Intent.LoadChartData -> loadChartData(intent.data)
            is PieChartContract.Intent.UpdateChartData -> updateChartData(intent.data)
            is PieChartContract.Intent.SelectSlice -> selectSlice(intent.sliceIndex)
            is PieChartContract.Intent.ClearSelection -> clearSelection()
        }
    }

    private fun loadChartData(data: com.majid2851.charts.domain.model.PieChartData) {
        setState {
            copy(chartData = data, isLoading = false, error = null, isAnimating = true)
        }
    }

    private fun updateChartData(data: com.majid2851.charts.domain.model.PieChartData) {
        setState {
            copy(chartData = data, isAnimating = true)
        }
    }

    private fun selectSlice(sliceIndex: Int) {
        setState {
            copy(selectedSliceIndex = sliceIndex)
        }
        currentState.chartData?.slices?.getOrNull(sliceIndex)?.let { slice ->
            sendEffect(PieChartContract.Effect.ShowSliceDetails(sliceIndex, slice.value))
        }
    }

    private fun clearSelection() {
        setState {
            copy(selectedSliceIndex = null)
        }
    }
}
