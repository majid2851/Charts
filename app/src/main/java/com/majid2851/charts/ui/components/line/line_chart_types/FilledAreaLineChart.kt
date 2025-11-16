package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens

@Composable
fun FilledAreaLineChart() {
    LineChart(
        data = LineChartData(
            title = "Line Chart with Fill",
            lines = listOf(
                LineDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(0f, 100f),
                        DataPoint(1f, 200f),
                        DataPoint(2f, 150f),
                        DataPoint(3f, 300f),
                        DataPoint(4f, 250f),
                        DataPoint(5f, 350f)
                    ),
                    lineColor = AppColors.Blue,
                    fillArea = true,
                    fillColor = AppColors.Blue.copy(alpha = 0.3f),
                    isCurved = true
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Preview(showBackground = true)
@Composable
private fun FilledAreaLineChartPreview() {
    FilledAreaLineChart()
}

