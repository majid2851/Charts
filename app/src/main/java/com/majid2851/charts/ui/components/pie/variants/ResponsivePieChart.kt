package com.majid2851.charts.ui.components.pie.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    modifier: Modifier = Modifier
) {
    ResponsiveContainer(modifier = modifier.height(300.dp)) { _, _ ->
        PieChart(
            data = getResponsivePieChartData(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Sample data matching Recharts example
 */
private fun getResponsivePieChartData() = PieChartData(
    title = "Responsive Pie Chart",
    slices = listOf(
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
    ),
    config = PieChartConfig(
        showLabels = true,
        showLegend = true,
        labelPosition = PieLabelPosition.OUTSIDE,
        innerRadius = 0f,
        outerRadius = 0.8f
    )
)

@Preview(showBackground = true, widthDp = 800)
@Composable
private fun ResponsivePieChartPreview() {
    ResponsivePieChart(
        modifier = Modifier.fillMaxSize()
    )
}

