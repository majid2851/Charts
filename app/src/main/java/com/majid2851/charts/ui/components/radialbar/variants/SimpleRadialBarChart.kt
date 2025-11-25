package com.majid2851.charts.ui.components.radialbar.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.ChartConfig
import com.majid2851.charts.domain.model.RadialBarChartData
import com.majid2851.charts.domain.model.RadialBarEntry
import com.majid2851.charts.ui.components.radialbar.RadialBarChart
import com.majid2851.charts.ui.theme.Dimens

@Composable
fun SimpleRadialBarChart(
    modifier: Modifier = Modifier,
    width: Dp = Dimens.previewChartWidth,
    height: Dp = Dimens.chartHeightLarge,
    title: String = "Age Distribution",
    bars: List<RadialBarEntry> = getDefaultRadialBarData(),
    centerX: Float = 0.4f,  // Adjusted from 0.3f to prevent left clipping
    centerY: Float = 0.5f,
    barSize: Float = 14f,
    startAngle: Float = 0f,
    endAngle: Float = 360f,
    showBackground: Boolean = true,
    backgroundOpacity: Float = 0.3f,
    showLegend: Boolean = true,
    showGrid: Boolean = false,
    showAxis: Boolean = false,
    chartPadding: Dp = 16.dp,
    isInteractive: Boolean = true
) {
    val data = RadialBarChartData(
        title = title,
        bars = bars,
        config = ChartConfig(
            showLegend = showLegend,
            showGrid = showGrid,
            showAxis = showAxis,
            chartPadding = chartPadding,
            isInteractive = isInteractive
        ),
        centerX = centerX,
        centerY = centerY,
        barSize = barSize,
        startAngle = startAngle,
        endAngle = endAngle,
        showBackground = showBackground,
        backgroundOpacity = backgroundOpacity
    )

    RadialBarChart(
        data = data,
        modifier = if (modifier == Modifier) {
            Modifier.width(width).height(height)
        } else {
            modifier
        }
    )
}

private fun getDefaultRadialBarData() = listOf(
    RadialBarEntry(
        name = "18-24",
        value = 31.47f,
        fill = Color(0xFF8884d8)
    ),
    RadialBarEntry(
        name = "25-29",
        value = 26.69f,
        fill = Color(0xFF83a6ed)
    ),
    RadialBarEntry(
        name = "30-34",
        value = 15.69f,
        fill = Color(0xFF8dd1e1)
    ),
    RadialBarEntry(
        name = "35-39",
        value = 8.22f,
        fill = Color(0xFF82ca9d)
    ),
    RadialBarEntry(
        name = "40-49",
        value = 8.63f,
        fill = Color(0xFFa4de6c)
    ),
    RadialBarEntry(
        name = "50+",
        value = 2.63f,
        fill = Color(0xFFd0ed57)
    ),
    RadialBarEntry(
        name = "unknown",
        value = 6.67f,
        fill = Color(0xFFffc658)
    )
)

@Preview(showBackground = true)
@Composable
private fun SimpleRadialBarChartPreview() {
    SimpleRadialBarChart()
}

