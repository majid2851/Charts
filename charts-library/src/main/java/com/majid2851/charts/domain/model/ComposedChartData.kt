package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

/**
 * Data model for ComposedChart - combines multiple chart types
 */
data class ComposedChartData(
    override val title: String? = null,
    override val description: String? = null,
    val categories: List<String>, // X-axis categories
    val lineDataSets: List<LineDataSet> = emptyList(),
    val barDataSets: List<ComposedBarDataSet> = emptyList(),
    val areaDataSets: List<AreaDataSet> = emptyList(),
    val scatterDataSets: List<ScatterDataSet> = emptyList(),
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val orientation: ChartOrientation = ChartOrientation.VERTICAL
) : ChartData

/**
 * Simple data model for composed chart entries
 */
data class ComposedChartEntry(
    val category: String,
    val values: Map<String, Float> // key: dataKey, value: value
)

/**
 * Bar data set for composed chart
 */
data class ComposedBarDataSet(
    val dataKey: String,
    val label: String,
    val dataPoints: List<DataPoint?>,
    val color: Color = Color.Blue,
    val barSize: Float = 20f
)

