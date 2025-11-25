package com.majid2851.charts.ui.components.composed.variants

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
import com.majid2851.charts.ui.components.composed.ComposedChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Vertical Composed Chart
 * Shows a vertical orientation of composed chart
 */
@Composable
fun VerticalComposedChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.previewChartHeight,
    title: String = "Vertical Composed Chart",
    categories: List<String> = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F"),
    areaDataSets: List<AreaDataSet> = getDefaultVerticalAreaDataSets(),
    barDataSets: List<ComposedBarDataSet> = getDefaultVerticalBarDataSets(),
    lineDataSets: List<LineDataSet> = getDefaultVerticalLineDataSets(),
    orientation: ChartOrientation = ChartOrientation.VERTICAL,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp
) {
    val data = ComposedChartData(
        title = title,
        categories = categories,
        orientation = orientation,
        areaDataSets = areaDataSets,
        barDataSets = barDataSets,
        lineDataSets = lineDataSets,
        config = ChartConfig(
            showGrid = showGrid,
            showAxis = showAxis,
            showLegend = showLegend,
            chartPadding = chartPadding
        )
    )
    
    ComposedChart(
        data = data,
        modifier = if (modifier == Modifier) {
            Modifier.width(width).height(height)
        } else {
            modifier
        }
    )
}

private fun getDefaultVerticalAreaDataSets() = listOf(
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

private fun getDefaultVerticalBarDataSets() = listOf(
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

private fun getDefaultVerticalLineDataSets() = listOf(
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

@Preview(showBackground = true)
@Composable
private fun VerticalComposedChartPreview() {
    VerticalComposedChart()
}

