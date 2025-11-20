package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart

/**
 * Composed Chart With Axis Labels
 * Shows composed chart with custom axis labels
 */
@Composable
fun ComposedChartWithAxisLabels(
    modifier: Modifier = Modifier
) {
    val categories = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F")
    
    val data = ComposedChartData(
        title = "Composed Chart With Axis Labels",
        categories = categories,
        areaDataSets = listOf(
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
        ),
        barDataSets = listOf(
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
        ),
        lineDataSets = listOf(
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
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Pages",
            labelPosition = LabelPosition.INSIDE_BOTTOM
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Index",
            labelPosition = LabelPosition.INSIDE_LEFT
        )
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

