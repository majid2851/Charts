package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

data class LineChartData(
    override val title: String? = null,
    override val description: String? = null,
    val lines: List<LineDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val referenceLines: List<ReferenceLine> = emptyList(),
    val connectNulls: Boolean = false,
    val orientation: ChartOrientation = ChartOrientation.HORIZONTAL
) : ChartData

data class LineDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val lineWidth: Float = 2f,
    val showPoints: Boolean = true,
    val pointRadius: Float = 10f,
    val isCurved: Boolean = true,
    val fillArea: Boolean = false,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val isDashed: Boolean = false,
    val dashPattern: FloatArray = floatArrayOf(10f, 5f),
    val useSecondaryYAxis: Boolean = false,
    val customPointShape: PointShape = PointShape.CIRCLE,
    val interactionConfig: PointInteractionConfig = PointInteractionConfig()
)

data class PointInteractionConfig(
    val enableInteraction: Boolean = true,
    val activePointRadius: Float = 8f,
    val activePointColor: Color? = null,
    val activeLineColor: Color? = null,
    val activeLineWidth: Float? = null,
    val showActivePointBorder: Boolean = true,
    val activePointBorderColor: Color = Color.White,
    val activePointBorderWidth: Float = 2f
)

data class ReferenceLine(
    val value: Float,
    val label: String? = null,
    val color: Color = Color.Gray,
    val strokeWidth: Float = 1f,
    val isDashed: Boolean = true,
    val isVertical: Boolean = false
)
