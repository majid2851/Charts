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
 * Three-Dimensional Scatter Chart
 * Uses Z-axis for point size (bubble effect)
 * Matches Recharts ThreeDimScatterChart example
 */
@Composable
fun ThreeDimScatterChart(
    modifier: Modifier = Modifier
) {
    val data01 = listOf(
        ScatterPoint(100f, 200f, 200f),
        ScatterPoint(120f, 100f, 260f),
        ScatterPoint(170f, 300f, 400f),
        ScatterPoint(140f, 250f, 280f),
        ScatterPoint(150f, 400f, 500f),
        ScatterPoint(110f, 280f, 200f)
    )

    val data02 = listOf(
        ScatterPoint(200f, 260f, 240f),
        ScatterPoint(240f, 290f, 220f),
        ScatterPoint(190f, 290f, 250f),
        ScatterPoint(198f, 250f, 210f),
        ScatterPoint(180f, 280f, 260f),
        ScatterPoint(210f, 220f, 230f)
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Three-Dimensional Scatter Chart",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = data01,
                    pointColor = Color(0xFF8884d8),
                    pointShape = PointShape.STAR
                ),
                ScatterDataSet(
                    label = "B school",
                    dataPoints = data02,
                    pointColor = Color(0xFF82ca9d),
                    pointShape = PointShape.TRIANGLE
                )
            ),
            zAxisConfig = ZAxisConfig(
                dataKey = "z",
                range = Pair(60f, 400f),
                unit = "km",
                name = "score"
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ThreeDimScatterChartPreview() {
    ThreeDimScatterChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

