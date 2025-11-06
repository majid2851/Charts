package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class PieChartData(
    override val title: String? = null,
    override val description: String? = null,
    val slices: List<PieSlice>,
    val config: ChartConfig = ChartConfig(),
    val pieStyle: PieStyle = PieStyle.PIE,
    val donutRadius: Float = 0.5f,
    val showLabels: Boolean = true,
    val showPercentages: Boolean = true
) : ChartData

data class PieSlice(
    val label: String,
    val value: Float,
    val color: Color,
    val isExploded: Boolean = false,
    val explodeDistance: Float = 0.1f
)

enum class PieStyle {
    PIE,
    DONUT,
    SEMI_CIRCLE
}
