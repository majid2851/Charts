package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

/**
 * Data model for RadialBarChart - concentric circular bars
 */
data class RadialBarChartData(
    override val title: String? = null,
    override val description: String? = null,
    val bars: List<RadialBarEntry>,
    val config: ChartConfig = ChartConfig(),
    val centerX: Float = 0.3f, // Relative position (0-1)
    val centerY: Float = 0.5f, // Relative position (0-1)
    val barSize: Float = 14f,
    val startAngle: Float = 0f,
    val endAngle: Float = 360f,
    val showBackground: Boolean = true,
    val backgroundOpacity: Float = 0.3f
) : ChartData

/**
 * Entry for radial bar chart
 */
data class RadialBarEntry(
    val name: String,
    val value: Float,
    val maxValue: Float = 100f, // For percentage calculation
    val fill: Color,
    val label: String? = null,
    val showLabel: Boolean = true,
    val labelPosition: RadialLabelPosition = RadialLabelPosition.INSIDE_START
)

/**
 * Label position for radial bars
 */
enum class RadialLabelPosition {
    INSIDE_START,
    INSIDE_END,
    OUTSIDE
}

