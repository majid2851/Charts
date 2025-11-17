package com.majid2851.charts.ui.components.scatter.variants

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.scatter.ScatterChart

/**
 * Bubble Chart
 * Multiple rows of bubble charts (similar to heatmap)
 * Matches Recharts BubbleChart example
 * 
 * Note: This is a simplified version. Full Recharts implementation
 * shows hourly data across multiple days in a compact format.
 */
@Composable
fun BubbleChart(
    modifier: Modifier = Modifier
) {
    val sundayData = listOf(
        ScatterPoint(0f, 1f, 170f),
        ScatterPoint(1f, 1f, 180f),
        ScatterPoint(2f, 1f, 150f),
        ScatterPoint(3f, 1f, 120f),
        ScatterPoint(4f, 1f, 200f),
        ScatterPoint(5f, 1f, 300f),
        ScatterPoint(6f, 1f, 400f),
        ScatterPoint(7f, 1f, 200f),
        ScatterPoint(8f, 1f, 100f),
        ScatterPoint(9f, 1f, 150f),
        ScatterPoint(10f, 1f, 160f),
        ScatterPoint(11f, 1f, 170f)
    )

    val mondayData = listOf(
        ScatterPoint(0f, 2f, 160f),
        ScatterPoint(1f, 2f, 180f),
        ScatterPoint(2f, 2f, 150f),
        ScatterPoint(3f, 2f, 120f),
        ScatterPoint(4f, 2f, 200f),
        ScatterPoint(5f, 2f, 300f),
        ScatterPoint(6f, 2f, 100f),
        ScatterPoint(7f, 2f, 200f),
        ScatterPoint(8f, 2f, 100f),
        ScatterPoint(9f, 2f, 150f),
        ScatterPoint(10f, 2f, 160f),
        ScatterPoint(11f, 2f, 160f)
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Bubble Chart - Activity by Hour",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "Sunday",
                    dataPoints = sundayData,
                    pointColor = Color(0xFF8884d8)
                ),
                ScatterDataSet(
                    label = "Monday",
                    dataPoints = mondayData,
                    pointColor = Color(0xFF82ca9d)
                )
            ),
            zAxisConfig = ZAxisConfig(
                dataKey = "z",
                range = Pair(16f, 225f)
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun BubbleChartPreview() {
    BubbleChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

