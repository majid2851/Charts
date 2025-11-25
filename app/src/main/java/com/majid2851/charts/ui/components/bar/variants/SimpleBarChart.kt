package com.majid2851.charts.ui.components.bar.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.bar.BarChart
import androidx.compose.ui.unit.Dp
import com.majid2851.charts.ui.theme.Dimens

/**
 * Simple Bar Chart
 * Matches Recharts simple bar chart with multiple series and interactions
 */
@Composable
fun SimpleBarChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    bars: List<BarDataSet> = getSimpleBarChartData().bars,
    title: String = "Simple Bar Chart",
    groupingMode: BarGroupingMode = BarGroupingMode.GROUPED,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 5.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true
) {
    BarChart(
        data = BarChartData(
            title = title,
            bars = bars,
            groupingMode = groupingMode,
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive,
                chartPadding = chartPadding,
                cartesianGrid = CartesianGridConfig(
                    horizontalDashPattern = floatArrayOf(3f,3f),
                    verticalDashPattern   = floatArrayOf(3f,3f)
                )
            )
        ),
        modifier = modifier
            .width(width)
            .height(height)
    )
}

private fun getSimpleBarChartData() = BarChartData(
    title = "Simple Bar Chart",
    bars = listOf(
        BarDataSet(
            label = "pv",
            entries = listOf(
                LabeledEntry("Page A", 2400f),
                LabeledEntry("Page B", 1398f),
                LabeledEntry("Page C", 9800f),
                LabeledEntry("Page D", 3908f),
                LabeledEntry("Page E", 4800f),
                LabeledEntry("Page F", 3800f),
                LabeledEntry("Page G", 4300f)
            ),
            barColor = Color(0xFF8884d8)
        ),
        BarDataSet(
            label = "uv",
            entries = listOf(
                LabeledEntry("Page A", 4000f),
                LabeledEntry("Page B", 3000f),
                LabeledEntry("Page C", 2000f),
                LabeledEntry("Page D", 2780f),
                LabeledEntry("Page E", 1890f),
                LabeledEntry("Page F", 2390f),
                LabeledEntry("Page G", 3490f)
            ),
            barColor = Color(0xFF82ca9d)
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        chartPadding = 5.dp,
        cartesianGrid = CartesianGridConfig(
            horizontalDashPattern = floatArrayOf(3f, 3f),
            verticalDashPattern = floatArrayOf(3f, 3f)
        )
    )
)

@Preview(showBackground = true)
@Composable
private fun SimpleBarChartPreview() {
    SimpleBarChart()
}

