package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.AreaChartData
import com.majid2851.charts.domain.model.AreaDataSet
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.area.AreaChart

/**
 * Cardinal Area Chart
 * Demonstrates comparing linear vs smooth curved areas
 * Matches Recharts CardinalAreaChart example
 * 
 * Note: In Recharts, cardinal curves use d3-shape's curveCardinal with tension.
 * In Compose, we use quadratic bezier curves for smooth interpolation.
 */
@Composable
fun CardinalAreaChart(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, 2000f, "Page C"),
        DataPoint(3f, 2780f, "Page D"),
        DataPoint(4f, 1890f, "Page E"),
        DataPoint(5f, 2390f, "Page F"),
        DataPoint(6f, 3490f, "Page G")
    )

    AreaChart(
        data = AreaChartData(
            title = "Cardinal Area Chart",
            areas = listOf(
                // Linear/monotone version (less smooth)
                AreaDataSet(
                    label = "UV (Monotone)",
                    dataPoints = data,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.3f),
                    isCurved = false, // Linear interpolation
                    lineWidth = 2f
                ),
                // Cardinal/smooth version (curved)
                AreaDataSet(
                    label = "UV (Cardinal)",
                    dataPoints = data,
                    lineColor = Color(0xFF82ca9d),
                    fillColor = Color(0xFF82ca9d).copy(alpha = 0.3f),
                    isCurved = true, // Smooth curve interpolation
                    lineWidth = 2f
                )
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CardinalAreaChartPreview() {
    CardinalAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

