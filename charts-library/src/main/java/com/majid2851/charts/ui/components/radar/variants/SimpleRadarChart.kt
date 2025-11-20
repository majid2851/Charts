package com.majid2851.charts.ui.components.radar.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.RadarChartData
import com.majid2851.charts.domain.model.RadarDataSet
import com.majid2851.charts.ui.components.radar.RadarChart

/**
 * Simple Radar Chart
 * Matches Recharts SimpleRadarChart example
 */
@Composable
fun SimpleRadarChart(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        120f, // Math
        98f,  // Chinese
        86f,  // English
        99f,  // Geography
        85f,  // Physics
        65f   // History
    )

    RadarChart(
        data = RadarChartData(
            title = "Simple Radar Chart",
            labels = listOf("Math", "Chinese", "English", "Geography", "Physics", "History"),
            dataSets = listOf(
                RadarDataSet(
                    label = "Mike",
                    values = data,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8),
                    fillOpacity = 0.6f
                )
            ),
            domain = Pair(0f, 150f),
            outerRadius = 0.8f
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleRadarChartPreview() {
    SimpleRadarChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

