package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Synchronized Area Charts
 * Multiple charts that share interaction state
 * Matches Recharts SynchronizedAreaChart example
 * 
 * When a user interacts with one chart, all synchronized charts
 * respond to show the same data point or interaction.
 */
@Composable
fun SynchronizedAreaChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    chartHeight: Dp = 200.dp,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true
) {
    // Shared state for synchronized interaction
    var selectedPointIndex by remember { mutableStateOf<Int?>(null) }
    
    val data = getSynchronizedAreaChartData()
    val uvData = data.first
    val pvData = data.second

    Column(modifier = modifier.width(width)) {
        Text(
            text = "Chart 1 - UV Data",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        
        // First synchronized chart
        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "UV",
                        dataPoints = uvData,
                        lineColor = Color(0xFF8884d8),
                        fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
                        isCurved = true
                    )
                ),
                config = ChartConfig(
                    showGrid = showGrid,
                    showAxis = showAxis,
                    showLegend = showLegend,
                    chartPadding = chartPadding,
                    animationEnabled = animationEnabled,
                    isInteractive = isInteractive,
                    cartesianGrid = CartesianGridConfig(
                        horizontalDashPattern = floatArrayOf(3f, 3f),
                        verticalDashPattern = floatArrayOf(3f, 3f)
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(chartHeight),
            onPointSelected = { _, pointIndex, _ ->
                // Synchronize selection across charts
                selectedPointIndex = pointIndex
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Chart 2 - PV Data",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        // Second synchronized chart
        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "PV",
                        dataPoints = pvData,
                        lineColor = Color(0xFF82ca9d),
                        fillColor = Color(0xFF82ca9d).copy(alpha = 0.6f),
                        isCurved = true
                    )
                ),
                config = ChartConfig(
                    showGrid = showGrid,
                    showAxis = showAxis,
                    showLegend = showLegend,
                    chartPadding = chartPadding,
                    animationEnabled = animationEnabled,
                    isInteractive = isInteractive,
                    cartesianGrid = CartesianGridConfig(
                        horizontalDashPattern = floatArrayOf(3f, 3f),
                        verticalDashPattern = floatArrayOf(3f, 3f)
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(chartHeight),
            onPointSelected = { _, pointIndex, _ ->
                // Synchronize selection across charts
                selectedPointIndex = pointIndex
            }
        )

        // Display selected point info
        selectedPointIndex?.let { index ->
            if (index < uvData.size && index < pvData.size) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Selected: ${uvData[index].label}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "UV: ${uvData[index].y.toInt()} | PV: ${pvData[index].y.toInt()}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

private fun getSynchronizedAreaChartData(): Pair<List<DataPoint>, List<DataPoint>> {
    val uvData = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, 2000f, "Page C"),
        DataPoint(3f, 2780f, "Page D"),
        DataPoint(4f, 1890f, "Page E"),
        DataPoint(5f, 2390f, "Page F"),
        DataPoint(6f, 3490f, "Page G")
    )

    val pvData = listOf(
        DataPoint(0f, 2400f, "Page A"),
        DataPoint(1f, 1398f, "Page B"),
        DataPoint(2f, 9800f, "Page C"),
        DataPoint(3f, 3908f, "Page D"),
        DataPoint(4f, 4800f, "Page E"),
        DataPoint(5f, 3800f, "Page F"),
        DataPoint(6f, 4300f, "Page G")
    )
    
    return uvData to pvData
}

@Preview(showBackground = true)
@Composable
private fun SynchronizedAreaChartPreview() {
    SynchronizedAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

