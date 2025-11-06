package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class RadarChartData(
    override val title: String? = null,
    override val description: String? = null,
    val dataSets: List<RadarDataSet>,
    val config: ChartConfig = ChartConfig(),
    val labels: List<String>,
    val maxValue: Float = 100f,
    val webLineColor: Color = Color.Gray,
    val showWeb: Boolean = true
) : ChartData

data class RadarDataSet(
    val label: String,
    val values: List<Float>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val lineWidth: Float = 2f
)
