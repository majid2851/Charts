package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class LineChartData(
    override val title: String? = null,
    override val description: String? = null,
    val lines: List<LineDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig()
) : ChartData

data class LineDataSet(
    val label: String,
    val dataPoints: List<DataPoint>,
    val lineColor: Color = Color.Blue,
    val lineWidth: Float = 2f,
    val showPoints: Boolean = true,
    val pointRadius: Float = 4f,
    val isCurved: Boolean = false,
    val fillArea: Boolean = false,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f)
)
