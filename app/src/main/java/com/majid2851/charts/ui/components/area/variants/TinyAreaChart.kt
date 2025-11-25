package com.majid2851.charts.ui.components.area.variants

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
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Tiny Area Chart
 * Minimal area chart with no decorations
 */
@Composable
fun TinyAreaChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    areas: List<AreaDataSet> = getTinyAreaChartData().areas,
    showGrid: Boolean = false,
    showAxis: Boolean = false,
    showLegend: Boolean = false,
    chartPadding: Dp = 5.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = false
) {
    AreaChart(
        data = AreaChartData(
            areas = areas,
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                chartPadding = chartPadding,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive
            )
        ),
        modifier = modifier
            .width(width)
            .height(height)
    )
}

private fun getTinyAreaChartData() = AreaChartData(
    areas = listOf(
        AreaDataSet(
            label = "UV",
            dataPoints = listOf(
                DataPoint(0f, 4000f, "Page A"),
                DataPoint(1f, 3000f, "Page B"),
                DataPoint(2f, 2000f, "Page C"),
                DataPoint(3f, 2780f, "Page D"),
                DataPoint(4f, 1890f, "Page E"),
                DataPoint(5f, 2390f, "Page F"),
                DataPoint(6f, 3490f, "Page G")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
            isCurved = true
        )
    ),
    config = ChartConfig(
        showGrid = false,
        showAxis = false,
        showLegend = false,
        chartPadding = 5.dp
    )
)

@Preview(showBackground = true)
@Composable
private fun TinyAreaChartPreview() {
    TinyAreaChart(
        modifier = Modifier,
        width = Dimens.previewChartWidth,
        height = 100.dp
    )
}

