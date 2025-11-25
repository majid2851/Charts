package com.majid2851.charts.ui.components.pie.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.PieChartConfig
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieLabelPosition
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer

/**
 * Responsive Pie Chart
 * Matches the Recharts ResponsiveContainer + PieChart example
 * 
 * Automatically adapts to parent container size
 */
@Composable
fun ResponsivePieChart(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    title: String = "Responsive Pie Chart",
    slices: List<PieSlice> = getDefaultResponsivePieSlices(),
    showLabels: Boolean = true,
    showLegend: Boolean = true,
    labelPosition: PieLabelPosition = PieLabelPosition.OUTSIDE,
    innerRadius: Float = 0f,
    outerRadius: Float = 0.8f,
    chartPadding: Dp = 16.dp,
    isInteractive: Boolean = true,
    animationEnabled: Boolean = true
) {
    ResponsiveContainer(modifier = modifier.height(height)) { _, _ ->
        PieChart(
            data = PieChartData(
                title = title,
                slices = slices,
                config = PieChartConfig(
                    showLabels = showLabels,
                    showLegend = showLegend,
                    labelPosition = labelPosition,
                    innerRadius = innerRadius,
                    outerRadius = outerRadius,
                    chartPadding = chartPadding,
                    isInteractive = isInteractive,
                    animationEnabled = animationEnabled
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun getDefaultResponsivePieSlices() = listOf(
    PieSlice(
        label = "Group A",
        value = 400f,
        color = Color(0xFF0088FE)
    ),
    PieSlice(
        label = "Group B",
        value = 300f,
        color = Color(0xFF00C49F)
    ),
    PieSlice(
        label = "Group C",
        value = 300f,
        color = Color(0xFFFFBB28)
    ),
    PieSlice(
        label = "Group D",
        value = 200f,
        color = Color(0xFFFF8042)
    )
)

@Preview(showBackground = true, widthDp = 800)
@Composable
private fun ResponsivePieChartPreview() {
    ResponsivePieChart(
        modifier = Modifier.fillMaxSize()
    )
}

