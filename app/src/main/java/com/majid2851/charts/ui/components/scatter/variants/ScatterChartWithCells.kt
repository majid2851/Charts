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
 * Scatter Chart With Cells
 * Each point has a different color
 * Matches Recharts ScatterChartWithCells example
 */
@Composable
fun ScatterChartWithCells(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    scatterSets: List<ScatterDataSet> = getScatterChartWithCellsData().scatterSets,
    title: String = "Scatter Chart With Individual Colors",
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

private fun getScatterChartWithCellsData(): ScatterChartData {
    val colors = listOf(
        Color(0xFF0088FE),
        Color(0xFF00C49F),
        Color(0xFFFFBB28),
        Color(0xFFFF8042),
        Color(0xFFFF0000),
        Color(0xFFFFC0CB)
    )

    return ScatterChartData(
        title = "Scatter Chart With Individual Colors",
        scatterSets = listOf(
            ScatterDataSet(
                label = "A school",
                dataPoints = listOf(
                    ScatterPoint(100f, 200f, 200f, color = colors[0]),
                    ScatterPoint(120f, 100f, 260f, color = colors[1]),
                    ScatterPoint(170f, 300f, 400f, color = colors[2]),
                    ScatterPoint(140f, 250f, 280f, color = colors[3]),
                    ScatterPoint(150f, 400f, 500f, color = colors[4]),
                    ScatterPoint(110f, 280f, 200f, color = colors[5])
                ),
                pointColor = Color(0xFF8884d8), // Default color
                customColors = colors
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
}

@Preview(showBackground = true)
@Composable
private fun ScatterChartWithCellsPreview() {
    ScatterChartWithCells(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

