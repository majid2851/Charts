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
                DataPoint(0f, 4000f, "Page A"),
                DataPoint(1f, 3000f, "Page B"),
                DataPoint(2f, 2000f, "Page C"),
                DataPoint(3f, 2780f, "Page D"),
                DataPoint(4f, 1890f, "Page E"),
                DataPoint(5f, 2390f, "Page F"),
                DataPoint(6f, 3490f, "Page G")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "PV",
            dataPoints = listOf(
                DataPoint(0f, 2400f, "Page A"),
                DataPoint(1f, 1398f, "Page B"),
                DataPoint(2f, 9800f, "Page C"),
                DataPoint(3f, 3908f, "Page D"),
                DataPoint(4f, 4800f, "Page E"),
                DataPoint(5f, 3800f, "Page F"),
                DataPoint(6f, 4300f, "Page G")
            ),
            lineColor = Color(0xFF82ca9d),
            fillColor = Color(0xFF82ca9d).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "AMT",
            dataPoints = listOf(
                DataPoint(0f, 2400f, "Page A"),
                DataPoint(1f, 2210f, "Page B"),
                DataPoint(2f, 2290f, "Page C"),
                DataPoint(3f, 2000f, "Page D"),
                DataPoint(4f, 2181f, "Page E"),
                DataPoint(5f, 2500f, "Page F"),
                DataPoint(6f, 2100f, "Page G")
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

