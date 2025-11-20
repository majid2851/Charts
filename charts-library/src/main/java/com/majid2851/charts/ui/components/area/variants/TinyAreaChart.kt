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
import com.majid2851.charts.domain.model.ChartConfig
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.area.AreaChart

/**
 * Tiny Area Chart (minimal sparkline-style chart)
 * Matches Recharts TinyAreaChart example
 */
@Composable
fun TinyAreaChart(
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
            areas = listOf(
                AreaDataSet(
                    label = "UV",
                    dataPoints = data,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
                    isCurved = true
                )
            ),
            config = ChartConfig(
                showGrid = false,
                showAxis = false,
                showLegend = false,
                chartPadding = 5.dp
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun TinyAreaChartPreview() {
    TinyAreaChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}

