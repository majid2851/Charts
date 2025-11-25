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
 * Scatter And Line Of Best Fit
 * Shows scatter points with lines of best fit
 * Note: In a real implementation, you would calculate the line of best fit
 */
@Composable
fun ScatterAndLineOfBestFit(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    title: String = "Scatter and Line of Best Fit",
    categories: List<String> = listOf("300", "600", "625", "1666", "10000"),
    scatterDataSets: List<ScatterDataSet> = getDefaultScatterSets(),
    lineDataSets: List<LineDataSet> = getDefaultBestFitLines(),
    xAxisLabel: String = "Index",
    yAxisLabel: String = "Time (ms)",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp
) {
    val data = ComposedChartData(
        title = title,
        categories = categories,
        scatterDataSets = scatterDataSets,
        lineDataSets = lineDataSets,
        config = ChartConfig(
            showGrid = showGrid,
            showAxis = showAxis,
            showLegend = showLegend,
            chartPadding = chartPadding
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = xAxisLabel
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = yAxisLabel
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

private fun getDefaultScatterSets() = listOf(
    ScatterDataSet(
        label = "red",
        dataPoints = listOf(
            ScatterPoint(4f, 1643f),
            ScatterPoint(3f, 182f),
            ScatterPoint(2f, 56f)
        ),
        pointColor = Color.Red,
        pointSize = 8f
    ),
    ScatterDataSet(
        label = "blue",
        dataPoints = listOf(
            ScatterPoint(4f, 790f),
            ScatterPoint(3f, 42f),
            ScatterPoint(2f, 11f)
        ),
        pointColor = Color.Blue,
        pointSize = 8f
    )
)

private fun getDefaultBestFitLines() = listOf(
    // Red line of best fit
    LineDataSet(
        label = "redLine",
        dataPoints = listOf(
            DataPoint(0f, 0f),
            DataPoint(4f, 1522f)
        ),
        lineColor = Color.Red,
        lineWidth = 2f,
        showPoints = false
    ),
    // Blue line of best fit
    LineDataSet(
        label = "blueLine",
        dataPoints = listOf(
            DataPoint(1f, 0f),
            DataPoint(4f, 678f)
        ),
        lineColor = Color.Blue,
        lineWidth = 2f,
        showPoints = false
    )
)

@Preview(showBackground = true)
@Composable
private fun ScatterAndLineOfBestFitPreview() {
    ScatterAndLineOfBestFit()
}

