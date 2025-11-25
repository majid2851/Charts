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
 * Joint Line Scatter Chart
 * Scatter points connected with lines
 * Matches Recharts JointLineScatterChart example
 */
@Composable
fun JointLineScatterChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    scatterSets: List<ScatterDataSet> = getJointLineScatterChartData().scatterSets,
    title: String = "Joint Line Scatter Chart",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    zAxisConfig: ZAxisConfig = ZAxisConfig(range = Pair(100f, 100f))
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

private fun getJointLineScatterChartData() = ScatterChartData(
    title = "Joint Line Scatter Chart",
    scatterSets = listOf(
        ScatterDataSet(
            label = "A school",
            dataPoints = listOf(
                ScatterPoint(10f, 30f),
                ScatterPoint(30f, 200f),
                ScatterPoint(45f, 100f),
                ScatterPoint(50f, 400f),
                ScatterPoint(70f, 150f),
                ScatterPoint(100f, 250f)
            ),
            pointColor = Color(0xFF8884d8),
            pointShape = PointShape.CROSS,
            showLine = true,
            lineColor = Color(0xFF8884d8)
        ),
        ScatterDataSet(
            label = "B school",
            dataPoints = listOf(
                ScatterPoint(30f, 20f),
                ScatterPoint(50f, 180f),
                ScatterPoint(75f, 240f),
                ScatterPoint(100f, 100f),
                ScatterPoint(120f, 190f)
            ),
            pointColor = Color(0xFF82ca9d),
            pointShape = PointShape.DIAMOND,
            showLine = true,
            lineColor = Color(0xFF82ca9d)
        )
    ),
    zAxisConfig = ZAxisConfig(
        range = Pair(100f, 100f) // Fixed size
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
private fun JointLineScatterChartPreview() {
    JointLineScatterChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

