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
    height: Dp = Dimens.chartHeightLarge,
    title: String? = null,
    bars: List<BarDataSet> = getDefaultTinyBarData(),
    groupingMode: BarGroupingMode = BarGroupingMode.GROUPED,
    showGrid: Boolean = false,
    showAxis: Boolean = false,
    showLegend: Boolean = false,
    chartPadding: Dp = 0.dp,
    backgroundColor: Color = Color.White,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = false
) {
    BarChart(
        data = BarChartData(
            title = title,
            bars = bars,
            groupingMode = groupingMode,
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                chartPadding = chartPadding,
                backgroundColor = backgroundColor,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive
            )
        ),
        modifier = if (modifier == Modifier) {
            Modifier.width(width).height(height)
        } else {
            modifier
        }
    )
}

private fun getDefaultTinyBarData() = listOf(
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
)

@Preview(showBackground = true)
@Composable
private fun TinyBarChartPreview() {
    TinyBarChart()
}

