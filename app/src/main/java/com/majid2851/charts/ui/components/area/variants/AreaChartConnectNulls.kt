package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
 * Area Chart with Connect Nulls comparison
 * Matches Recharts AreaChartConnectNulls example
 */
@Composable
fun AreaChartConnectNulls(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    chartHeight: Dp = 200.dp,
    areas: List<AreaDataSet> = getAreaChartConnectNullsData().areas,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true
) {
    Column(modifier = modifier.width(width)) {
        Text(
            text = "Without Connect Nulls",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        
        AreaChart(
            data = AreaChartData(
                areas = areas,
                connectNulls = false,
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
                .height(chartHeight)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "With Connect Nulls",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        AreaChart(
            data = AreaChartData(
                areas = areas,
                connectNulls = true,
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
                .height(chartHeight)
        )
    }
}

private fun getAreaChartConnectNullsData() = AreaChartData(
    areas = listOf(
        AreaDataSet(
            label = "UV",
            dataPoints = listOf(
                DataPoint(0f, 4000f, "Page A"),
                DataPoint(1f, 3000f, "Page B"),
                DataPoint(2f, 2000f, "Page C"),
                null, // Missing data for Page D
                DataPoint(4f, 1890f, "Page E"),
                DataPoint(5f, 2390f, "Page F"),
                DataPoint(6f, 3490f, "Page G")
            ),
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
            isCurved = true
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        chartPadding = 16.dp,
        cartesianGrid = CartesianGridConfig(
            horizontalDashPattern = floatArrayOf(3f, 3f),
            verticalDashPattern = floatArrayOf(3f, 3f)
        )
    )
)

@Preview(showBackground = true)
@Composable
private fun AreaChartConnectNullsPreview() {
    AreaChartConnectNulls(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

