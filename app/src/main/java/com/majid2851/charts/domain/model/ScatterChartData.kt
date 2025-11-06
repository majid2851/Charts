package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class ScatterChartData(
    override val title: String? = null,
    override val description: String? = null,
    val scatterSets: List<ScatterDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig()
) : ChartData

data class ScatterDataSet(
    val label: String,
    val dataPoints: List<ScatterPoint>,
    val pointColor: Color = Color.Blue,
    val pointShape: PointShape = PointShape.CIRCLE
)

data class ScatterPoint(
    val x: Float,
    val y: Float,
    val label: String? = null,
    val size: Float = 8f
)

enum class PointShape {
    CIRCLE,
    SQUARE,
    TRIANGLE,
    DIAMOND
}
