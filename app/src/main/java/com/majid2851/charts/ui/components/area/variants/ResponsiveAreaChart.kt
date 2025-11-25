package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer

/**
 * Responsive Area Chart
 * Adapts to parent container size
 */
@Composable
fun ResponsiveAreaChart(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    title: String = "Responsive Area Chart",
    areas: List<AreaDataSet> = getDefaultResponsiveAreaData(),
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = false,
    chartPadding: Dp = 10.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    gridStyle: GridLineStyle = GridLineStyle.DASHED,
    xAxisLabelSize: Float = 12f,
    yAxisLabelSize: Float = 12f,
    xAxisLabel: String? = null,
    yAxisLabel: String? = null
) {
    ResponsiveContainer(modifier = modifier.height(height)) { _, _ ->
        AreaChart(
            data = AreaChartData(
                title = title,
                areas = areas,
                config = ChartConfig(
                    showGrid = showGrid,
                    showAxis = showAxis,
                    showLegend = showLegend,
                    chartPadding = chartPadding,
                    animationEnabled = animationEnabled,
                    isInteractive = isInteractive,
                    cartesianGrid = if (gridStyle == GridLineStyle.DASHED) {
                        CartesianGridConfig(
                            horizontalDashPattern = floatArrayOf(3f, 3f),
                            verticalDashPattern = floatArrayOf(3f, 3f),
                            horizontalLineStyle = gridStyle,
                            verticalLineStyle = gridStyle
                        )
                    } else {
                        CartesianGridConfig(
                            horizontalLineStyle = gridStyle,
                            verticalLineStyle = gridStyle
                        )
                    }
                ),
                xAxisConfig = AxisConfig(
                    showLabels = true,
                    labelTextSize = xAxisLabelSize,
                    axisLabel = xAxisLabel
                ),
                yAxisConfig = AxisConfig(
                    showLabels = true,
                    labelTextSize = yAxisLabelSize,
                    axisLabel = yAxisLabel
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun getDefaultResponsiveAreaData() = listOf(
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
)


@Preview(showBackground = true, widthDp = 800)
@Composable
private fun ResponsiveAreaChartPreview() {
    ResponsiveAreaChart(
        modifier = Modifier.fillMaxSize()
    )
}

