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
 * Stacked Area Chart
 * Matches Recharts StackedAreaChart example
 */
@Composable
fun StackedAreaChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    areas: List<AreaDataSet> = getStackedAreaChartData().areas,
    title: String = "Stacked Area Chart",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true
) {
    AreaChart(
        data = AreaChartData(
            title = title,
            areas = areas,
            stackingMode = AreaStackingMode.STACKED,
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                chartPadding = chartPadding,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive,
                cartesianGrid = CartesianGridConfig(
                    horizontalDashPattern = floatArrayOf(3f, 3f),
                    verticalDashPattern = floatArrayOf(3f, 3f)
                )
            )
        ),
        modifier = modifier
            .width(width)
            .height(height)
    )
}

private fun getStackedAreaChartData() = AreaChartData(
    title = "Stacked Area Chart",
    areas = listOf(
        AreaDataSet(
            label = "UV",
            dataPoints = listOf(
                DataPoint(0f, 40f, "Page A"),
                DataPoint(1f, 30f, "Page B"),
                DataPoint(2f, 25f, "Page C"),
                DataPoint(3f, 16f, "Page D"),
                DataPoint(4f, 19f, "Page E"),
                DataPoint(5f, 20f, "Page F"),
                DataPoint(6f, 34f, "Page G")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "PV",
            dataPoints = listOf(
                DataPoint(0f, 24f, "Page A"),
                DataPoint(1f, 13f, "Page B"),
                DataPoint(2f, 98f, "Page C"),
                DataPoint(3f, 39f, "Page D"),
                DataPoint(4f, 48f, "Page E"),
                DataPoint(5f, 38f, "Page F"),
                DataPoint(6f, 43f, "Page G")
            ),
            lineColor = Color(0xFF82ca9d),
            fillColor = Color(0xFF82ca9d).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "AMT",
            dataPoints = listOf(
                DataPoint(0f, 24f, "Page A"),
                DataPoint(1f, 22f, "Page B"),
                DataPoint(2f, 22f, "Page C"),
                DataPoint(3f, 20f, "Page D"),
                DataPoint(4f, 21f, "Page E"),
                DataPoint(5f, 25f, "Page F"),
                DataPoint(6f, 21f, "Page G")
            ),
            lineColor = Color(0xFFffc658),
            fillColor = Color(0xFFffc658).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        )
    ),
    stackingMode = AreaStackingMode.STACKED,
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        chartPadding = 16.dp,
        cartesianGrid = CartesianGridConfig(
            horizontalDashPattern = floatArrayOf(3f, 3f),
            verticalDashPattern = floatArrayOf(3f, 3f)
        )
    )
)

@Preview(showBackground = true)
@Composable
private fun StackedAreaChartPreview() {
    StackedAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

