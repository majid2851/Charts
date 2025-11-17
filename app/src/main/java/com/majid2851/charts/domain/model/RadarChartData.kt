package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class RadarChartData(
    override val title: String? = null,
    override val description: String? = null,
    val dataSets: List<RadarDataSet>,
    val config: ChartConfig = ChartConfig(),
    val labels: List<String>,
    val domain: Pair<Float, Float>? = null, // Custom domain (min, max)
    val outerRadius: Float = 0.8f, // Relative to available space (0-1)
    val polarGridConfig: PolarGridConfig = PolarGridConfig(),
    val polarAngleAxisConfig: PolarAngleAxisConfig = PolarAngleAxisConfig(),
    val polarRadiusAxisConfig: PolarRadiusAxisConfig = PolarRadiusAxisConfig()
) : ChartData

data class RadarDataSet(
    val label: String,
    val values: List<Float>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val fillOpacity: Float = 0.6f,
    val lineWidth: Float = 2f,
    val showPoints: Boolean = true,
    val pointSize: Float = 6f
)

/**
 * Configuration for Polar Grid (web lines)
 */
data class PolarGridConfig(
    val show: Boolean = true,
    val strokeColor: Color = Color.Gray.copy(alpha = 0.3f),
    val strokeWidth: Float = 1f,
    val gridType: PolarGridType = PolarGridType.POLYGON
)

enum class PolarGridType {
    POLYGON,  // Straight lines forming polygon
    CIRCLE    // Circular grid
}

/**
 * Configuration for Polar Angle Axis (labels around the perimeter)
 */
data class PolarAngleAxisConfig(
    val show: Boolean = true,
    val dataKey: String = "subject",
    val labelColor: Color = Color.Black,
    val labelSize: Float = 14f
)

/**
 * Configuration for Polar Radius Axis (radial lines)
 */
data class PolarRadiusAxisConfig(
    val show: Boolean = true,
    val angle: Float = 0f, // Angle of radius axis in degrees
    val tickCount: Int = 5,
    val showLabels: Boolean = false,
    val labelColor: Color = Color.Gray,
    val labelSize: Float = 12f
)
