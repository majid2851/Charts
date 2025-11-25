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
 * Banded Chart
 * Shows a line chart with a range band area
 */
@Composable
fun BandedChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.previewChartHeight,
    title: String = "Banded Chart",
    categories: List<String> = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F"),
    bandData: List<DataPoint?> = getDefaultBandData(),
    lineData: List<DataPoint> = getDefaultLineData(),
    bandColor: Color = Color(0xFFCCCCCC),
    lineColor: Color = Color.Magenta,
    lineWidth: Float = 2f,
    isCurved: Boolean = true,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    gridStyle: GridLineStyle = GridLineStyle.DASHED,
    chartPadding: Dp = 16.dp
) {
    val data = ComposedChartData(
        title = title,
        categories = categories,
        // Range band (shown as area from min to max)
        areaDataSets = listOf(
            AreaDataSet(
                label = "Range",
                dataPoints = bandData,
                lineColor = Color.Transparent,
                fillColor = bandColor,
                fillOpacity = 1f
            )
        ),
        lineDataSets = listOf(
            LineDataSet(
                label = "b",
                dataPoints = lineData,
                lineColor = lineColor,
                lineWidth = lineWidth,
                isCurved = isCurved
            )
        ),
        config = ChartConfig(
            showGrid = showGrid,
            showAxis = showAxis,
            showLegend = showLegend,
            chartPadding = chartPadding,
            cartesianGrid = CartesianGridConfig(
                horizontalLineStyle = gridStyle,
                verticalLineStyle = gridStyle
            )
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

private fun getDefaultBandData() = listOf(
    DataPoint(0f, 0f),       // Min at Page A
    DataPoint(1f, 175f),     // Average of 50 and 300
    DataPoint(2f, 286.5f),   // Average of 150 and 423
    null,                     // Page D has no range
    DataPoint(4f, 522.5f),   // Average of 367 and 678
    DataPoint(5f, 563f)      // Average of 305 and 821
)

private fun getDefaultLineData() = listOf(
    DataPoint(0f, 0f),
    DataPoint(1f, 106f),
    DataPoint(2f, 229f),
    DataPoint(3f, 312f),
    DataPoint(4f, 451f),
    DataPoint(5f, 623f)
)

@Preview(showBackground = true)
@Composable
private fun BandedChartPreview() {
    BandedChart()
}

