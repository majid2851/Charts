package com.majid2851.charts.ui.components.bar.variants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.bar.BarChart

/**
 * Positive and Negative Bar Chart
 * Shows bars with both positive and negative values
 */
@Composable
fun PositiveAndNegativeBarChart(
    modifier: Modifier = Modifier
) {
    BarChart(
        data = getPositiveAndNegativeBarChartData(),
        modifier = modifier
    )
}

private fun getPositiveAndNegativeBarChartData() = BarChartData(
    title = "Positive and Negative Bar Chart",
    bars = listOf(
        BarDataSet(
            label = "pv",
            entries = listOf(
                LabeledEntry("Page A", 2400f),
                LabeledEntry("Page B", 1398f),
                LabeledEntry("Page C", -9800f),
                LabeledEntry("Page D", 3908f),
                LabeledEntry("Page E", 4800f),
                LabeledEntry("Page F", -3800f),
                LabeledEntry("Page G", 4300f)
            ),
            barColor = Color(0xFF8884d8)
        ),
        BarDataSet(
            label = "uv",
            entries = listOf(
                LabeledEntry("Page A", 4000f),
                LabeledEntry("Page B", -3000f),
                LabeledEntry("Page C", -2000f),
                LabeledEntry("Page D", 2780f),
                LabeledEntry("Page E", -1890f),
                LabeledEntry("Page F", 2390f),
                LabeledEntry("Page G", 3490f)
            ),
            barColor = Color(0xFF82ca9d)
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        chartPadding = 5.dp,
        cartesianGrid = CartesianGridConfig(
            horizontalDashPattern = floatArrayOf(3f, 3f),
            verticalDashPattern = floatArrayOf(3f, 3f)
        )
    )
)

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun PositiveAndNegativeBarChartPreview() {
    PositiveAndNegativeBarChart(modifier = Modifier.fillMaxSize())
}

