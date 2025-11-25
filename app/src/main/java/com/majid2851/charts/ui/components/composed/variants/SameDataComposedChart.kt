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
 * Same Data Composed Chart
 * Shows the same data as both a Bar and Line chart
 */
@Composable
fun SameDataComposedChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.previewChartHeight,
    title: String = "Same Data Composed Chart",
    categories: List<String> = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F"),
    dataValues: List<Float> = listOf(590f, 868f, 1397f, 1480f, 1520f, 1400f),
    barColor: Color = Color(0xFF413ea0),
    lineColor: Color = Color(0xFFff7300),
    barSize: Float = 20f,
    lineWidth: Float = 2f,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp
) {
    val sharedDataPoints = dataValues.mapIndexed { index, value ->
        DataPoint(index.toFloat(), value)
    }
    
    val data = ComposedChartData(
        title = title,
        categories = categories,
        barDataSets = listOf(
            ComposedBarDataSet(
                dataKey = "uv",
                label = "uv (Bar)",
                dataPoints = sharedDataPoints,
                color = barColor,
                barSize = barSize
            )
        ),
        lineDataSets = listOf(
            LineDataSet(
                label = "uv (Line)",
                dataPoints = sharedDataPoints,
                lineColor = lineColor,
                lineWidth = lineWidth
            )
        ),
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

@Preview(showBackground = true)
@Composable
private fun SameDataComposedChartPreview() {
    SameDataComposedChart()
}

