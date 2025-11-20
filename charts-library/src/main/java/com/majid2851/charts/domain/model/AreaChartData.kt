package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class AreaChartData(
    override val title: String? = null,
    override val description: String? = null,
    val areas: List<AreaDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val stackingMode: AreaStackingMode = AreaStackingMode.NONE,
    val connectNulls: Boolean = false
) : ChartData

data class AreaDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val fillBrush: Brush? = null, // For gradient fills
    val lineWidth: Float = 2f,
    val isCurved: Boolean = true,
    val fillOpacity: Float = 0.6f,
    val stackId: String? = null // For stacking areas
)

enum class AreaStackingMode {
    NONE,      // Regular area chart
    STACKED,   // Stacked values
    PERCENTAGE // Normalized to 100%
}

/**
 * Enum for area curve types (matching Recharts)
 */
enum class AreaCurveType {
    LINEAR,      // Straight lines between points
    MONOTONE,    // Smooth curve preserving monotonicity
    NATURAL,     // Natural cubic spline
    STEP,        // Step function
    CARDINAL     // Cardinal spline (customizable tension)
}
