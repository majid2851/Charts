package com.majid2851.charts.ui.components.radar.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.radar.RadarChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Simple Radar Chart
 * Matches Recharts SimpleRadarChart example
 */
@Composable
fun SimpleRadarChart(
    modifier: Modifier = Modifier
        .width(Dimens.previewChartWidth)
        .height(Dimens.chartHeightLarge),
    title: String = "Simple Radar Chart",
    labels: List<String> = listOf("Math", "Chinese", "English", "Geography", "Physics", "History"),
    values: List<Float> = listOf(120f, 98f, 86f, 99f, 85f, 65f),
    dataSetLabel: String = "Mike",
    lineColor: Color = Color(0xFF8884d8),
    fillColor: Color = Color(0xFF8884d8),
    fillOpacity: Float = 0.6f,
    lineWidth: Float = 2f,
    domain: Pair<Float, Float> = Pair(0f, 150f),
    outerRadius: Float = 0.8f,
    showGrid: Boolean = true,
    showLegend: Boolean = true,
    showPoints: Boolean = true,
    pointSize: Float = 6f,
    chartPadding: Dp = 16.dp,
    polarGridConfig: PolarGridConfig = PolarGridConfig(),
    polarAngleAxisConfig: PolarAngleAxisConfig = PolarAngleAxisConfig(),
    isInteractive: Boolean = true,
    onPointSelected: ((dataSetIndex: Int, pointIndex: Int, value: Float) -> Unit)? = null
) {
    RadarChart(
        data = RadarChartData(
            title = title,
            labels = labels,
            dataSets = listOf(
                RadarDataSet(
                    label = dataSetLabel,
                    values = values,
                    lineColor = lineColor,
                    fillColor = fillColor,
                    fillOpacity = fillOpacity,
                    lineWidth = lineWidth,
                    showPoints = showPoints,
                    pointSize = pointSize
                )
            ),
            domain = domain,
            outerRadius = outerRadius,
            polarGridConfig = polarGridConfig,
            polarAngleAxisConfig = polarAngleAxisConfig,
            config = ChartConfig(
                showGrid = showGrid,
                showLegend = showLegend,
                chartPadding = chartPadding,
                isInteractive = isInteractive
            )
        ),
        modifier = modifier,
        onPointSelected = onPointSelected
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleRadarChartPreview() {
    SimpleRadarChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

