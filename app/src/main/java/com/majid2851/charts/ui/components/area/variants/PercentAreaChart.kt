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
import com.majid2851.charts.domain.model.AreaStackingMode
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.area.AreaChart

/**
 * Percent Area Chart (100% Stacked)
 * Matches Recharts PercentAreaChart example
 */
@Composable
fun PercentAreaChart(
    modifier: Modifier = Modifier
) {
    val aData = listOf(
        DataPoint(0f, 4000f, "2015.01"),
        DataPoint(1f, 3000f, "2015.02"),
        DataPoint(2f, 2000f, "2015.03"),
        DataPoint(3f, 2780f, "2015.04"),
        DataPoint(4f, 1890f, "2015.05"),
        DataPoint(5f, 2390f, "2015.06"),
        DataPoint(6f, 3490f, "2015.07")
    )

    val bData = listOf(
        DataPoint(0f, 2400f, "2015.01"),
        DataPoint(1f, 1398f, "2015.02"),
        DataPoint(2f, 9800f, "2015.03"),
        DataPoint(3f, 3908f, "2015.04"),
        DataPoint(4f, 4800f, "2015.05"),
        DataPoint(5f, 3800f, "2015.06"),
        DataPoint(6f, 4300f, "2015.07")
    )

    val cData = listOf(
        DataPoint(0f, 2400f, "2015.01"),
        DataPoint(1f, 2210f, "2015.02"),
        DataPoint(2f, 2290f, "2015.03"),
        DataPoint(3f, 2000f, "2015.04"),
        DataPoint(4f, 2181f, "2015.05"),
        DataPoint(5f, 2500f, "2015.06"),
        DataPoint(6f, 2100f, "2015.07")
    )

    AreaChart(
        data = AreaChartData(
            title = "Percent Area Chart",
            areas = listOf(
                AreaDataSet(
                    label = "A",
                    dataPoints = aData,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.8f),
                    isCurved = true,
                    stackId = "1"
                ),
                AreaDataSet(
                    label = "B",
                    dataPoints = bData,
                    lineColor = Color(0xFF82ca9d),
                    fillColor = Color(0xFF82ca9d).copy(alpha = 0.8f),
                    isCurved = true,
                    stackId = "1"
                ),
                AreaDataSet(
                    label = "C",
                    dataPoints = cData,
                    lineColor = Color(0xFFffc658),
                    fillColor = Color(0xFFffc658).copy(alpha = 0.8f),
                    isCurved = true,
                    stackId = "1"
                )
            ),
            stackingMode = AreaStackingMode.PERCENTAGE
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PercentAreaChartPreview() {
    PercentAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

