package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Line Bar Area Composed Chart
 * Combines Line, Bar, Area, and Scatter in a single chart
 */
@Composable
fun LineBarAreaComposedChart(
    modifier: Modifier = Modifier
        .width(Dimens.previewChartWidth)
        .height(Dimens.previewChartHeight),
    title: String = "Line Bar Area Composed Chart",
    categories: List<String> = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F"),
    areaDataSets: List<AreaDataSet> = getDefaultAreaDataSets(),
    barDataSets: List<ComposedBarDataSet> = getDefaultBarDataSets(),
    lineDataSets: List<LineDataSet> = getDefaultLineDataSets(),
    scatterDataSets: List<ScatterDataSet> = getDefaultScatterDataSets(),
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    xAxisConfig: AxisConfig = AxisConfig(),
    yAxisConfig: AxisConfig = AxisConfig(),
    cartesianGrid: CartesianGridConfig = CartesianGridConfig()
) {
    val data = ComposedChartData(
        title = title,
        categories = categories,
        areaDataSets = areaDataSets,
        barDataSets = barDataSets,
        lineDataSets = lineDataSets,
        scatterDataSets = scatterDataSets,
        config = ChartConfig(
            showGrid = showGrid,
            showAxis = showAxis,
            showLegend = showLegend,
            chartPadding = chartPadding,
            cartesianGrid = cartesianGrid
        ),
        xAxisConfig = xAxisConfig,
        yAxisConfig = yAxisConfig
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

private fun getDefaultAreaDataSets() = listOf(
    AreaDataSet(
        label = "amt",
        dataPoints = listOf(
            DataPoint(0f, 1400f),
            DataPoint(1f, 1506f),
            DataPoint(2f, 989f),
            DataPoint(3f, 1228f),
            DataPoint(4f, 1100f),
            DataPoint(5f, 1700f)
        ),
        lineColor = Color(0xFF8884d8),
        fillColor = Color(0xFF8884d8),
        fillOpacity = 0.3f
    )
)

private fun getDefaultBarDataSets() = listOf(
    ComposedBarDataSet(
        dataKey = "pv",
        label = "pv",
        dataPoints = listOf(
            DataPoint(0f, 800f),
            DataPoint(1f, 967f),
            DataPoint(2f, 1098f),
            DataPoint(3f, 1200f),
            DataPoint(4f, 1108f),
            DataPoint(5f, 680f)
        ),
        color = Color(0xFF413ea0),
        barSize = 20f
    )
)

private fun getDefaultLineDataSets() = listOf(
    LineDataSet(
        label = "uv",
        dataPoints = listOf(
            DataPoint(0f, 590f),
            DataPoint(1f, 868f),
            DataPoint(2f, 1397f),
            DataPoint(3f, 1480f),
            DataPoint(4f, 1520f),
            DataPoint(5f, 1400f)
        ),
        lineColor = Color(0xFFff7300),
        lineWidth = 2f
    )
)

private fun getDefaultScatterDataSets() = listOf(
    ScatterDataSet(
        label = "cnt",
        dataPoints = listOf(
            ScatterPoint(0f, 490f),
            ScatterPoint(1f, 590f),
            ScatterPoint(2f, 350f),
            ScatterPoint(3f, 480f),
            ScatterPoint(4f, 460f),
            ScatterPoint(5f, 380f)
        ),
        pointColor = Color.Red,
        pointSize = 6f
    )
)

