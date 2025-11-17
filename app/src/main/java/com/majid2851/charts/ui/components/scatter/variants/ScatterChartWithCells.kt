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
 * Scatter Chart With Cells
 * Each point has a different color
 * Matches Recharts ScatterChartWithCells example
 */
@Composable
fun ScatterChartWithCells(
    modifier: Modifier = Modifier
) {
    val colors = listOf(
        Color(0xFF0088FE),
        Color(0xFF00C49F),
        Color(0xFFFFBB28),
        Color(0xFFFF8042),
        Color(0xFFFF0000),
        Color(0xFFFFC0CB)
    )

    val data = listOf(
        ScatterPoint(100f, 200f, 200f, color = colors[0]),
        ScatterPoint(120f, 100f, 260f, color = colors[1]),
        ScatterPoint(170f, 300f, 400f, color = colors[2]),
        ScatterPoint(140f, 250f, 280f, color = colors[3]),
        ScatterPoint(150f, 400f, 500f, color = colors[4]),
        ScatterPoint(110f, 280f, 200f, color = colors[5])
    )

    ScatterChart(
        data = ScatterChartData(
            title = "Scatter Chart With Individual Colors",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "A school",
                    dataPoints = data,
                    pointColor = Color(0xFF8884d8), // Default color
                    customColors = colors
                )
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ScatterChartWithCellsPreview() {
    ScatterChartWithCells(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

