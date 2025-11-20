package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data model for PieChart
 */
data class PieChartData(
    override val title: String? = null,
    override val description: String? = null,
    val slices: List<PieSlice>,
    val config: PieChartConfig = PieChartConfig()
) : ChartData

/**
 * Represents a single slice in the pie chart
 */
data class PieSlice(
    val label: String,
    val value: Float,
    val color: Color,
    val isSelected: Boolean = false
)

/**
 * Configuration for PieChart appearance and behavior
 */
data class PieChartConfig(
    val showLabels: Boolean = true,
    val showLegend: Boolean = true,
    val showPercentage: Boolean = true,
    val animationEnabled: Boolean = true,
    val animationDuration: Int = 800,
    val backgroundColor: Color = Color.Transparent,
    val chartPadding: Dp = 16.dp,
    val isInteractive: Boolean = true,
    val labelTextSize: Float = 14f,
    val labelColor: Color = Color.White,
    
    // Pie specific
    val innerRadius: Float = 0f, // 0 = full pie, > 0 = donut chart
    val outerRadius: Float = 1f, // Relative to available space (0-1)
    val startAngle: Float = 0f, // Starting angle in degrees (0 = right, 90 = top)
    val endAngle: Float = 360f, // Ending angle in degrees
    val paddingAngle: Float = 0f, // Gap between slices in degrees
    val cornerRadius: Float = 0f, // Rounded corners for slices
    
    // Label configuration
    val labelPosition: PieLabelPosition = PieLabelPosition.INSIDE,
    val showLabelLine: Boolean = true,
    val labelLineLength: Float = 20f,
    
    // Interactive
    val activeSliceOffset: Float = 10f, // How much to offset selected slice
    val showActiveShape: Boolean = false, // Show custom active shape
    
    // Center label (for donut charts)
    val centerLabel: String? = null,
    val centerLabelTextSize: Float = 24f,
    val centerLabelColor: Color = Color.Black
)

/**
 * Label position options for pie chart
 */
enum class PieLabelPosition {
    INSIDE,      // Inside the slice
    OUTSIDE,     // Outside the slice with line
    CENTER,      // At the center of the chart
    NONE         // No labels
}

/**
 * Data for two-level (nested) pie chart
 */
data class TwoLevelPieChartData(
    override val title: String? = null,
    override val description: String? = null,
    val innerSlices: List<PieSlice>,
    val outerSlices: List<PieSlice>,
    val innerConfig: PieChartConfig = PieChartConfig(
        innerRadius = 0f,
        outerRadius = 0.5f
    ),
    val outerConfig: PieChartConfig = PieChartConfig(
        innerRadius = 0.6f,
        outerRadius = 0.8f
    )
) : ChartData

/**
 * Configuration for pie chart with needle (gauge-like)
 */
data class PieChartWithNeedleData(
    override val title: String? = null,
    override val description: String? = null,
    val slices: List<PieSlice>,
    val needleValue: Float, // Value where needle points (0-total)
    val needleColor: Color = Color(0xFFD0D000),
    val config: PieChartConfig = PieChartConfig(
        startAngle = 180f,
        endAngle = 0f
    )
) : ChartData
