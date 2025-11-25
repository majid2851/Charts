package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.theme.Dimens

/**
 * Area Chart with Fill By Value (gradient split at zero)
 * Matches Recharts AreaChartFillByValue example
 */
@Composable
fun AreaChartFillByValue(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    areas: List<AreaDataSet> = getAreaChartFillByValueData().areas,
    title: String = "Area Chart Fill By Value",
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    chartPadding: Dp = 16.dp,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true
) {
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
                cartesianGrid = CartesianGridConfig(
                    horizontalDashPattern = floatArrayOf(3f, 3f),
                    verticalDashPattern = floatArrayOf(3f, 3f)
                )
            )
        ),
        modifier = modifier
            .width(width)
            .height(height)
    )
}

private fun getAreaChartFillByValueData(): AreaChartData {
    val data = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, -1000f, "Page C"),
        DataPoint(3f, 500f, "Page D"),
        DataPoint(4f, -2000f, "Page E"),
        DataPoint(5f, -250f, "Page F"),
        DataPoint(6f, 3490f, "Page G")
    )

    // Calculate gradient offset (where positive meets negative)
    val dataMax = data.maxOfOrNull { it.y } ?: 0f
    val dataMin = data.minOfOrNull { it.y } ?: 0f
    val offset = if (dataMax <= 0f) {
        0f
    } else if (dataMin >= 0f) {
        1f
    } else {
        dataMax / (dataMax - dataMin)
    }

    // Create gradient that changes color at zero
    val gradientBrush = Brush.verticalGradient(
        0f to Color.Green.copy(alpha = 0.8f),
        offset to Color.Green.copy(alpha = 0.1f),
        offset to Color.Red.copy(alpha = 0.1f),
        1f to Color.Red.copy(alpha = 0.8f)
    )

    return AreaChartData(
        title = "Area Chart Fill By Value",
        areas = listOf(
            AreaDataSet(
                label = "UV",
                dataPoints = data,
                lineColor = Color.Black,
                fillColor = Color.Transparent, // Will use brush instead
                fillBrush = gradientBrush,
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
}

@Preview(showBackground = true)
@Composable
private fun AreaChartFillByValuePreview() {
    AreaChartFillByValue(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

