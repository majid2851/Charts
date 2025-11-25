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
 * Specified Domain Radar Chart
 * Multiple series with custom domain and radius axis angle
 * Matches Recharts SpecifiedDomainRadarChart example
 */
@Composable
fun SpecifiedDomainRadarChart(
    modifier: Modifier = Modifier
        .width(Dimens.previewChartWidth)
        .height(Dimens.chartHeightLarge),
    title: String = "Specified Domain Radar Chart",
    labels: List<String> = listOf("Math", "Chinese", "English", "Geography", "Physics", "History"),
    dataSets: List<RadarDataSet> = getDefaultSpecifiedDomainDataSets(),
    domain: Pair<Float, Float> = Pair(0f, 150f),
    outerRadius: Float = 0.8f,
    showGrid: Boolean = true,
    showLegend: Boolean = true,
    showRadiusAxisLabels: Boolean = false,
    radiusAxisAngle: Float = 30f,
    chartPadding: Dp = 16.dp,
    polarGridConfig: PolarGridConfig = PolarGridConfig(),
    polarAngleAxisConfig: PolarAngleAxisConfig = PolarAngleAxisConfig(),
    polarRadiusAxisConfig: PolarRadiusAxisConfig = PolarRadiusAxisConfig(
        angle = radiusAxisAngle,
        showLabels = showRadiusAxisLabels
    ),
    isInteractive: Boolean = true,
    onPointSelected: ((dataSetIndex: Int, pointIndex: Int, value: Float) -> Unit)? = null
) {
    RadarChart(
        data = RadarChartData(
            title = title,
            labels = labels,
            dataSets = dataSets,
            domain = domain,
            outerRadius = outerRadius,
            polarGridConfig = polarGridConfig,
            polarAngleAxisConfig = polarAngleAxisConfig,
            polarRadiusAxisConfig = polarRadiusAxisConfig,
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

/**
 * Get default data sets for SpecifiedDomainRadarChart
 */
private fun getDefaultSpecifiedDomainDataSets() = listOf(
    RadarDataSet(
        label = "Mike",
        values = listOf(120f, 98f, 86f, 99f, 85f, 65f),
        lineColor = Color(0xFF8884d8),
        fillColor = Color(0xFF8884d8),
        fillOpacity = 0.6f
    ),
    RadarDataSet(
        label = "Lily",
        values = listOf(110f, 130f, 130f, 100f, 90f, 85f),
        lineColor = Color(0xFF82ca9d),
        fillColor = Color(0xFF82ca9d),
        fillOpacity = 0.6f
    )
)

@Preview(showBackground = true)
@Composable
private fun SpecifiedDomainRadarChartPreview() {
    SpecifiedDomainRadarChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

