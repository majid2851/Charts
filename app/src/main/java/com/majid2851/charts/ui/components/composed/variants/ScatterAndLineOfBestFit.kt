package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart

/**
 * Scatter And Line Of Best Fit
 * Shows scatter points with lines of best fit
 */
@Composable
fun ScatterAndLineOfBestFit(
    modifier: Modifier = Modifier
) {
    // Note: In a real implementation, you would calculate the line of best fit
    // For this demo, we're using pre-calculated values
    val categories = listOf("300", "600", "625", "1666", "10000")
    
    val data = ComposedChartData(
        title = "Scatter and Line of Best Fit",
        categories = categories,
        scatterDataSets = listOf(
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
        ),
        lineDataSets = listOf(
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
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Index"
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Time (ms)"
        )
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

