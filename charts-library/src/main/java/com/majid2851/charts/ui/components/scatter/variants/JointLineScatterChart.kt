package com.majid2851.charts.ui.components.scatter.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.scatter.ScatterChart

/**
 * Joint Line Scatter Chart
 * Scatter points connected with lines
 * Matches Recharts JointLineScatterChart example
 */
@Composable
fun JointLineScatterChart(
    modifier: Modifier = Modifier
) {
    val data01 = listOf(
        ScatterPoint(10f, 30f),
        ScatterPoint(30f, 200f),
        ScatterPoint(45f, 100f),
        ScatterPoint(50f, 400f),
        ScatterPoint(70f, 150f),
        ScatterPoint(100f, 250f)
    )

    val data02 = listOf(
        ScatterPoint(30f, 20f),
        ScatterPoint(50f, 180f),
        ScatterPoint(75f, 240f),
        ScatterPoint(100f, 100f),
        ScatterPoint(120f, 190f)
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Joint Line Scatter Chart",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = data01,
                    pointColor = Color(0xFF8884d8),
                    pointShape = PointShape.CROSS,
                    showLine = true,
                    lineColor = Color(0xFF8884d8)
                ),
                ScatterDataSet(
                    label = "B school",
                    dataPoints = data02,
                    pointColor = Color(0xFF82ca9d),
                    pointShape = PointShape.DIAMOND,
                    showLine = true,
                    lineColor = Color(0xFF82ca9d)
                )
            ),
            zAxisConfig = ZAxisConfig(
                range = Pair(100f, 100f) // Fixed size
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun JointLineScatterChartPreview() {
    JointLineScatterChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

