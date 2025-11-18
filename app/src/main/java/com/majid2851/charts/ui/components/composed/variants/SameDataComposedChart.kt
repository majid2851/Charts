package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart

/**
 * Same Data Composed Chart
 * Shows the same data as both a Bar and Line chart
 */
@Composable
fun SameDataComposedChart(
    modifier: Modifier = Modifier
) {
    val categories = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F")
    
    val sharedDataPoints = listOf(
        DataPoint(0f, 590f),
        DataPoint(1f, 868f),
        DataPoint(2f, 1397f),
        DataPoint(3f, 1480f),
        DataPoint(4f, 1520f),
        DataPoint(5f, 1400f)
    )
    
    val data = ComposedChartData(
        title = "Same Data Composed Chart",
        categories = categories,
        barDataSets = listOf(
            ComposedBarDataSet(
                dataKey = "uv",
                label = "uv (Bar)",
                dataPoints = sharedDataPoints,
                color = Color(0xFF413ea0),
                barSize = 20f
            )
        ),
        lineDataSets = listOf(
            LineDataSet(
                label = "uv (Line)",
                dataPoints = sharedDataPoints,
                lineColor = Color(0xFFff7300),
                lineWidth = 2f
            )
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true
        )
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

