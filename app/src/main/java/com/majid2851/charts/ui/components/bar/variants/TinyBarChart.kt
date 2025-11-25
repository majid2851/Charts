package com.majid2851.charts.ui.components.bar.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.ui.theme.Dimens

@Composable
fun TinyBarChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp =Dimens.chartHeightLarge,
    data: BarChartData = getTinyBarChartData()
) {
    BarChart(
        data = data,
        modifier = modifier
            .width(width)
            .height(height)
    )
}

private fun getTinyBarChartData() = BarChartData(
    bars = listOf(
        BarDataSet(
            label = "uv",
            entries = listOf(
                LabeledEntry("Page A", 4000f),
                LabeledEntry("Page B", 3000f),
                LabeledEntry("Page C", 2000f),
                LabeledEntry("Page D", 2780f),
                LabeledEntry("Page E", 1890f),
                LabeledEntry("Page F", 2390f),
                LabeledEntry("Page G", 3490f)
            ),
            barColor = Color(0xFF8884d8)
        )
    ),
    config = ChartConfig(
        showGrid = false,
        showAxis = false,
        showLegend = false,
        chartPadding = 0.dp
    )
)

@Preview(showBackground = true)
@Composable
private fun TinyBarChartPreview() {
    TinyBarChart(modifier = Modifier.fillMaxSize())
}

