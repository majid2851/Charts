package com.majid2851.charts.ui.components.scatter.variants

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.scatter.ScatterChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Bubble Chart
 * Multiple rows of bubble charts (similar to heatmap)
 * Matches Recharts BubbleChart example
 * 
 * Note: This is a simplified version. Full Recharts implementation
 * shows hourly data across multiple days in a compact format.
 */
@Composable
fun BubbleChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = 300.dp,
    scatterSets: List<ScatterDataSet> = getBubbleChartData().scatterSets,
    title: String = "Bubble Chart - Activity by Hour",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    zAxisConfig: ZAxisConfig = ZAxisConfig(dataKey = "z", range = Pair(10f, 50f))
) {
    ScatterChart(
        data = ScatterChartData(
            title = title,
            scatterSets = scatterSets,
            zAxisConfig = zAxisConfig,
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

private fun getBubbleChartData() = ScatterChartData(
title = "Bubble Chart - Activity by Hour",
    scatterSets = listOf(
        ScatterDataSet(
            label = "Sunday",
            dataPoints = listOf(
                ScatterPoint(0f, 1f, 170f),
                ScatterPoint(1f, 1f, 180f),
                ScatterPoint(2f, 1f, 150f),
                ScatterPoint(3f, 1f, 120f),
                ScatterPoint(4f, 1f, 200f),
                ScatterPoint(5f, 1f, 300f),
                ScatterPoint(6f, 1f, 400f),
                ScatterPoint(7f, 1f, 200f),
                ScatterPoint(8f, 1f, 100f),
                ScatterPoint(9f, 1f, 150f),
                ScatterPoint(10f, 1f, 160f),
                ScatterPoint(11f, 1f, 170f)
            ),
            pointColor = Color(0xFF8884d8),
            showLine = false  // Explicitly disable lines for bubble chart
        ),
        ScatterDataSet(
            label = "Monday",
            dataPoints = listOf(
                ScatterPoint(0f, 2f, 160f),
                ScatterPoint(1f, 2f, 180f),
                ScatterPoint(2f, 2f, 150f),
                ScatterPoint(3f, 2f, 120f),
                ScatterPoint(4f, 2f, 200f),
                ScatterPoint(5f, 2f, 300f),
                ScatterPoint(6f, 2f, 100f),
                ScatterPoint(7f, 2f, 200f),
                ScatterPoint(8f, 2f, 100f),
                ScatterPoint(9f, 2f, 150f),
                ScatterPoint(10f, 2f, 160f),
                ScatterPoint(11f, 2f, 160f)
            ),
            pointColor = Color(0xFF82ca9d),
            showLine = false  // Explicitly disable lines for bubble chart
        )
    ),
    zAxisConfig = ZAxisConfig(
        dataKey = "z",
        range = Pair(10f, 50f)  // Reduced bubble size range to prevent overlap
    ),
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
private fun BubbleChartPreview() {
    BubbleChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

