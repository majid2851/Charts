package com.majid2851.charts.ui.components.radialbar.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.ChartConfig
import com.majid2851.charts.domain.model.RadialBarChartData
import com.majid2851.charts.domain.model.RadialBarEntry
import com.majid2851.charts.ui.components.radialbar.RadialBarChart

/**
 * Simple Radial Bar Chart
 * Matches the Recharts example with age distribution data
 */
@Composable
fun SimpleRadialBarChart(
    modifier: Modifier = Modifier
) {
    val data = RadialBarChartData(
        title = "Age Distribution",
        bars = listOf(
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
        ),
        config = ChartConfig(
            showLegend = true,
            showGrid = false,
            showAxis = false
        ),
        centerX = 0.3f,
        centerY = 0.5f,
        barSize = 14f
    )

    RadialBarChart(
        data = data,
        modifier = modifier
    )
}

