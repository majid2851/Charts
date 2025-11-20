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
 * Scatter Chart With Labels
 * Shows data labels on each point
 * Matches Recharts ScatterChartWithLabels example
 */
@Composable
fun ScatterChartWithLabels(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        ScatterPoint(100f, 200f, 200f, "100"),
        ScatterPoint(120f, 100f, 260f, "120"),
        ScatterPoint(170f, 300f, 400f, "170"),
        ScatterPoint(140f, 250f, 280f, "140"),
        ScatterPoint(150f, 400f, 500f, "150"),
        ScatterPoint(110f, 280f, 200f, "110")
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Scatter Chart With Labels",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = data,
                    pointColor = Color(0xFF8884d8),
                    showLabels = true
                )
            ),
            zAxisConfig = ZAxisConfig(
                dataKey = "z",
                range = Pair(900f, 4000f)
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ScatterChartWithLabelsPreview() {
    ScatterChartWithLabels(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

