package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.components.line.line_chart.components.rememberZoomState

/**
 * Example demonstrating zoom and pan functionality in LineChart
 */
@Composable
fun ZoomableLineChartExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Zoomable Line Chart Examples",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
        
        // Example 1: Chart with pinch-to-zoom and pan
        Text(
            text = "1. Pinch to Zoom & Pan (No Controls)",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        LineChart(
            data = createSampleData(
                title = "Sales Data - Pinch to Zoom",
                enableZoom = true,
                enablePan = true,
                showZoomControls = false
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        
        // Example 2: Chart with zoom controls
        Text(
            text = "2. With Zoom Control Buttons",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        LineChart(
            data = createSampleData(
                title = "Revenue Data - Use Buttons",
                enableZoom = true,
                enablePan = true,
                showZoomControls = true
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        
        // Example 3: Chart with custom zoom state
        Text(
            text = "3. Custom Zoom Range (0.5x - 10x)",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        val customZoomState = rememberZoomState(
            minZoom = 0.5f,
            maxZoom = 10f
        )
        LineChart(
            data = createSampleData(
                title = "Custom Zoom Range",
                enableZoom = true,
                enablePan = true,
                showZoomControls = true,
                minZoom = 0.5f,
                maxZoom = 10f
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            zoomState = customZoomState
        )
    }
}

private fun createSampleData(
    title: String,
    enableZoom: Boolean = false,
    enablePan: Boolean = false,
    showZoomControls: Boolean = false,
    minZoom: Float = 0.5f,
    maxZoom: Float = 5f
): LineChartData {
    return LineChartData(
        title = title,
        lines = listOf(
            LineDataSet(
                label = "Product A",
                dataPoints = listOf(
                    DataPoint(0f, 2400f, "Jan"),
                    DataPoint(1f, 1398f, "Feb"),
                    DataPoint(2f, 9800f, "Mar"),
                    DataPoint(3f, 3908f, "Apr"),
                    DataPoint(4f, 4800f, "May"),
                    DataPoint(5f, 3800f, "Jun"),
                    DataPoint(6f, 4300f, "Jul"),
                    DataPoint(7f, 5600f, "Aug"),
                    DataPoint(8f, 6200f, "Sep"),
                    DataPoint(9f, 4900f, "Oct"),
                    DataPoint(10f, 7100f, "Nov"),
                    DataPoint(11f, 8300f, "Dec")
                ),
                lineColor = Color(0xFF8884d8),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 6f
            ),
            LineDataSet(
                label = "Product B",
                dataPoints = listOf(
                    DataPoint(0f, 4000f, "Jan"),
                    DataPoint(1f, 3000f, "Feb"),
                    DataPoint(2f, 2000f, "Mar"),
                    DataPoint(3f, 2780f, "Apr"),
                    DataPoint(4f, 1890f, "May"),
                    DataPoint(5f, 2390f, "Jun"),
                    DataPoint(6f, 3490f, "Jul"),
                    DataPoint(7f, 4200f, "Aug"),
                    DataPoint(8f, 3800f, "Sep"),
                    DataPoint(9f, 5100f, "Oct"),
                    DataPoint(10f, 4600f, "Nov"),
                    DataPoint(11f, 5900f, "Dec")
                ),
                lineColor = Color(0xFF82ca9d),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 6f
            )
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            backgroundColor = Color.White,
            enableZoom = enableZoom,
            enablePan = enablePan,
            showZoomControls = showZoomControls,
            minZoom = minZoom,
            maxZoom = maxZoom
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 28f,
            axisColor = Color.Black
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 28f,
            labelCount = 5,
            axisColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ZoomableLineChartExamplePreview() {
    ZoomableLineChartExample()
}

