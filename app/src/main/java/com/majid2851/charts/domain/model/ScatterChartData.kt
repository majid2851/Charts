package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

data class ScatterChartData(
    override val title: String? = null,
    override val description: String? = null,
    val scatterSets: List<ScatterDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val zAxisConfig: ZAxisConfig? = null // For 3D and bubble charts
) : ChartData

data class ScatterDataSet(
    val label: String,
    val dataPoints: List<ScatterPoint>,
    val pointColor: Color = Color.Blue,
    val pointShape: PointShape = PointShape.CIRCLE,
    val pointSize: Float = 8f,
    val showLine: Boolean = false, // For joint line scatter
    val lineColor: Color? = null,
    val lineWidth: Float = 2f,
    val showLabels: Boolean = false,
    val customColors: List<Color>? = null, // For cells variant
    val yAxisId: String? = null // For multiple Y-axes
)

data class ScatterPoint(
    val x: Float,
    val y: Float,
    val z: Float? = null, // For 3D visualization (bubble size)
    val label: String? = null,
    val color: Color? = null // For individual point colors
)

/**
 * Configuration for Z-axis (used for bubble charts and 3D scatter)
 */
data class ZAxisConfig(
    val dataKey: String = "z",
    val range: Pair<Float, Float> = Pair(60f, 400f), // Min to max size in pixels
    val unit: String? = null,
    val name: String? = null
)

