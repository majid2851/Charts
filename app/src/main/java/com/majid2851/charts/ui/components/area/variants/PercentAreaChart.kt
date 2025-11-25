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
 * Percent Area Chart (100% Stacked)
 * Matches Recharts PercentAreaChart example
 */
@Composable
fun PercentAreaChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    areas: List<AreaDataSet> = getPercentAreaChartData().areas,
    title: String = "Percent Area Chart",
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
            stackingMode = AreaStackingMode.PERCENTAGE,
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

private fun getPercentAreaChartData() = AreaChartData(
    title = "Percent Area Chart",
    areas = listOf(
        AreaDataSet(
            label = "A",
            dataPoints = listOf(
                DataPoint(0f, 4000f, "2015.01"),
                DataPoint(1f, 3000f, "2015.02"),
                DataPoint(2f, 2000f, "2015.03"),
                DataPoint(3f, 2780f, "2015.04"),
                DataPoint(4f, 1890f, "2015.05"),
                DataPoint(5f, 2390f, "2015.06"),
                DataPoint(6f, 3490f, "2015.07")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "B",
            dataPoints = listOf(
                DataPoint(0f, 2400f, "2015.01"),
                DataPoint(1f, 1398f, "2015.02"),
                DataPoint(2f, 9800f, "2015.03"),
                DataPoint(3f, 3908f, "2015.04"),
                DataPoint(4f, 4800f, "2015.05"),
                DataPoint(5f, 3800f, "2015.06"),
                DataPoint(6f, 4300f, "2015.07")
            ),
            lineColor = Color(0xFF82ca9d),
            fillColor = Color(0xFF82ca9d).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        ),
        AreaDataSet(
            label = "C",
            dataPoints = listOf(
                DataPoint(0f, 2400f, "2015.01"),
                DataPoint(1f, 2210f, "2015.02"),
                DataPoint(2f, 2290f, "2015.03"),
                DataPoint(3f, 2000f, "2015.04"),
                DataPoint(4f, 2181f, "2015.05"),
                DataPoint(5f, 2500f, "2015.06"),
                DataPoint(6f, 2100f, "2015.07")
            ),
            lineColor = Color(0xFFffc658),
            fillColor = Color(0xFFffc658).copy(alpha = 0.8f),
            isCurved = true,
            stackId = "1"
        )
    ),
    stackingMode = AreaStackingMode.PERCENTAGE,
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
private fun PercentAreaChartPreview() {
    PercentAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

