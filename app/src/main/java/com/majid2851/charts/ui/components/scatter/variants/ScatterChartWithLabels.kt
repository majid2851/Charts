package com.majid2851.charts.ui.components.scatter.variants

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
import com.majid2851.charts.ui.components.scatter.ScatterChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Scatter Chart With Labels
 * Shows data labels on each point
 * Matches Recharts ScatterChartWithLabels example
 */
@Composable
fun ScatterChartWithLabels(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    scatterSets: List<ScatterDataSet> = getScatterChartWithLabelsData().scatterSets,
    title: String = "Scatter Chart With Labels",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    zAxisConfig: ZAxisConfig = ZAxisConfig(dataKey = "z", range = Pair(5f, 15f))
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

private fun getScatterChartWithLabelsData() = ScatterChartData(
    title = "Scatter Chart With Labels",
    scatterSets = listOf(
        ScatterDataSet(
            label = "A school",
            dataPoints = listOf(
                ScatterPoint(100f, 200f, 200f, "100"),
                ScatterPoint(120f, 100f, 260f, "120"),
                ScatterPoint(170f, 300f, 400f, "170"),
                ScatterPoint(140f, 250f, 280f, "140"),
                ScatterPoint(150f, 400f, 500f, "150"),
                ScatterPoint(110f, 280f, 200f, "110")
            ),
            pointColor = Color(0xFF8884d8),
            showLabels = true
        )
    ),
    zAxisConfig = ZAxisConfig(
        dataKey = "z",
        range = Pair(5f, 15f)  // Small bubble size range in pixels to avoid overlap
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
private fun ScatterChartWithLabelsPreview() {
    ScatterChartWithLabels(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

