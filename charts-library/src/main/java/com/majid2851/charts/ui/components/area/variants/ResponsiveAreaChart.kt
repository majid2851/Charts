package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer

@Composable
fun ResponsiveAreaChart(
    modifier: Modifier = Modifier
) {
    ResponsiveContainer(modifier = modifier.height(300.dp)) { _, _ ->
        AreaChart(
            data = getResponsiveAreaChartData(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun getResponsiveAreaChartData() = AreaChartData(
    title = "Responsive Area Chart",
    areas = listOf(
        AreaDataSet(
            label = "uv",
            dataPoints = listOf(
                DataPoint(0f, 4000f, "Page A"),
                DataPoint(1f, 3000f, "Page B"),
                DataPoint(2f, 2000f, "Page C"),
                DataPoint(3f, 2780f, "Page D"),
                DataPoint(4f, 1890f, "Page E"),
                DataPoint(5f, 2390f, "Page F"),
                DataPoint(6f, 3490f, "Page G")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8),
            isCurved = true,
            lineWidth = 2f
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = false,
        chartPadding = 10.dp,
        cartesianGrid = CartesianGridConfig(
            horizontalDashPattern = floatArrayOf(3f, 3f),
            verticalDashPattern = floatArrayOf(3f, 3f)
        )
    ),
    xAxisConfig = AxisConfig(
        showLabels = true,
        labelTextSize = 12f
    ),
    yAxisConfig = AxisConfig(
        showLabels = true,
        labelTextSize = 12f
    )
)

@Preview(showBackground = true, widthDp = 800)
@Composable
private fun ResponsiveAreaChartPreview() {
    ResponsiveAreaChart(
        modifier = Modifier.fillMaxSize()
    )
}

