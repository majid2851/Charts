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
 * Simple Area Chart
 * Matches Recharts simple area chart with customizable properties
 */
@Composable
fun SimpleAreaChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    areas: List<AreaDataSet> = getSimpleAreaChartData().areas,
    title: String = "Simple Area Chart",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    isCurved: Boolean = true,
    stackingMode: AreaStackingMode = AreaStackingMode.NONE
) {
    AreaChart(
        data = AreaChartData(
            title = title,
            areas = areas,
            stackingMode = stackingMode,
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive,
                chartPadding = chartPadding,
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

private fun getSimpleAreaChartData() = AreaChartData(
    title = "Simple Area Chart",
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
private fun SimpleAreaChartPreview() {
    SimpleAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

