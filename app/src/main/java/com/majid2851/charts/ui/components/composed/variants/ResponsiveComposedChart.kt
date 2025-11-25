package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer
import com.majid2851.charts.ui.theme.Dimens

/**
 * Responsive Composed Chart
 * Matches the Recharts ResponsiveContainer + ComposedChart example
 * 
 * Combines Bar, Line, and Area in a responsive container
 */
@Composable
fun ResponsiveComposedChart(
    modifier: Modifier = Modifier,
    height: Dp = Dimens.previewChartHeight,
    title: String = "Responsive Composed Chart",
    categories: List<String> = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F"),
    lineDataSets: List<LineDataSet> = getDefaultResponsiveLineData(),
    barDataSets: List<ComposedBarDataSet> = getDefaultResponsiveBarData(),
    areaDataSets: List<AreaDataSet> = getDefaultResponsiveAreaData(),
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 20.dp,
    gridColor: Color = Color(0xFFF5F5F5),
    gridStyle: GridLineStyle = GridLineStyle.SOLID
) {
    ResponsiveContainer(modifier = modifier.height(height)) { _, _ ->
        ComposedChart(
            data = ComposedChartData(
                title = title,
                categories = categories,
                lineDataSets = lineDataSets,
                barDataSets = barDataSets,
                areaDataSets = areaDataSets,
                config = ChartConfig(
                    showGrid = showGrid,
                    showAxis = showAxis,
                    showLegend = showLegend,
                    chartPadding = chartPadding,
                    cartesianGrid = CartesianGridConfig(
                        horizontalLineColor = gridColor,
                        verticalLineColor = gridColor,
                        horizontalLineStyle = gridStyle,
                        verticalLineStyle = gridStyle
                    )
                ),
                xAxisConfig = AxisConfig(
                    showLabels = true,
                    labelTextSize = 12f
                ),
                yAxisConfig = AxisConfig(
                    showLabels = true,
                    labelTextSize = 12f
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun getDefaultResponsiveLineData() = listOf(
    LineDataSet(
        label = "uv",
        dataPoints = listOf(
            DataPoint(0f, 590f, "Page A"),
            DataPoint(1f, 868f, "Page B"),
            DataPoint(2f, 1397f, "Page C"),
            DataPoint(3f, 1480f, "Page D"),
            DataPoint(4f, 1520f, "Page E"),
            DataPoint(5f, 1400f, "Page F")
        ),
        lineColor = Color(0xFFFF7300),
        lineWidth = 2f,
        isCurved = true
    )
)

private fun getDefaultResponsiveBarData() = listOf(
    ComposedBarDataSet(
        dataKey = "pv",
        label = "pv",
        dataPoints = listOf(
            DataPoint(0f, 800f, "Page A"),
            DataPoint(1f, 967f, "Page B"),
            DataPoint(2f, 1098f, "Page C"),
            DataPoint(3f, 1200f, "Page D"),
            DataPoint(4f, 1108f, "Page E"),
            DataPoint(5f, 680f, "Page F")
        ),
        color = Color(0xFF413ea0),
        barSize = 20f
    )
)

private fun getDefaultResponsiveAreaData() = listOf(
    AreaDataSet(
        label = "amt",
        dataPoints = listOf(
            DataPoint(0f, 1400f, "Page A"),
            DataPoint(1f, 1506f, "Page B"),
            DataPoint(2f, 989f, "Page C"),
            DataPoint(3f, 1228f, "Page D"),
            DataPoint(4f, 1100f, "Page E"),
            DataPoint(5f, 1700f, "Page F")
        ),
        lineColor = Color(0xFF8884d8),
        fillColor = Color(0xFF8884d8),
        isCurved = true
    )
)


@Preview(showBackground = true, widthDp = 800)
@Composable
private fun ResponsiveComposedChartPreview() {
    ResponsiveComposedChart(
        modifier = Modifier.fillMaxSize()
    )
}

