package com.majid2851.charts.ui.components.scatter.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.ScatterChartData
import com.majid2851.charts.domain.model.ScatterDataSet
import com.majid2851.charts.domain.model.ScatterPoint
import com.majid2851.charts.ui.components.scatter.ScatterChart

/**
 * Simple Scatter Chart
 * Matches Recharts SimpleScatterChart example
 */
@Composable
fun SimpleScatterChart(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        ScatterPoint(100f, 200f, 200f),
        ScatterPoint(120f, 100f, 260f),
        ScatterPoint(170f, 300f, 400f),
        ScatterPoint(140f, 250f, 280f),
        ScatterPoint(150f, 400f, 500f),
        ScatterPoint(110f, 280f, 200f)
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Simple Scatter Chart",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = data,
                    pointColor = Color(0xFF8884d8),
                    pointSize = 8f
                )
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleScatterChartPreview() {
    SimpleScatterChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

