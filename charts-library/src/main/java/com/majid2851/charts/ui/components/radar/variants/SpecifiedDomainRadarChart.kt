package com.majid2851.charts.ui.components.radar.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.radar.RadarChart

/**
 * Specified Domain Radar Chart
 * Multiple series with custom domain and radius axis angle
 * Matches Recharts SpecifiedDomainRadarChart example
 */
@Composable
fun SpecifiedDomainRadarChart(
    modifier: Modifier = Modifier
) {
    val dataA = listOf(
        120f, // Math
        98f,  // Chinese
        86f,  // English
        99f,  // Geography
        85f,  // Physics
        65f   // History
    )

    val dataB = listOf(
        110f, // Math
        130f, // Chinese
        130f, // English
        100f, // Geography
        90f,  // Physics
        85f   // History
    )

    RadarChart(
        data = RadarChartData(
            title = "Specified Domain Radar Chart",
            labels = listOf("Math", "Chinese", "English", "Geography", "Physics", "History"),
            dataSets = listOf(
                RadarDataSet(
                    label = "Mike",
                    values = dataA,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8),
                    fillOpacity = 0.6f
                ),
                RadarDataSet(
                    label = "Lily",
                    values = dataB,
                    lineColor = Color(0xFF82ca9d),
                    fillColor = Color(0xFF82ca9d),
                    fillOpacity = 0.6f
                )
            ),
            domain = Pair(0f, 150f), // Specified domain
            outerRadius = 0.8f,
            polarRadiusAxisConfig = PolarRadiusAxisConfig(
                angle = 30f, // Angle of radius axis
                showLabels = false
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun SpecifiedDomainRadarChartPreview() {
    SpecifiedDomainRadarChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

