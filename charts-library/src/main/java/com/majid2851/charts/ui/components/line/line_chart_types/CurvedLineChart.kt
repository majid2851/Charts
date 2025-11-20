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
fun CurvedLineChart() {
    LineChart(
        data = LineChartData(
            title = "Smooth Curved Line",
            lines = listOf(
                LineDataSet(
                    label = "Curved",
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 25f),
                        DataPoint(2f, 15f),
                        DataPoint(3f, 30f),
                        DataPoint(4f, 20f),
                        DataPoint(5f, 35f)
                    ),
                    lineColor = AppColors.Blue,
                    isCurved = true
                ),
                LineDataSet(
                    label = "Straight",
                    dataPoints = listOf(
                        DataPoint(0f, 8f),
                        DataPoint(1f, 20f),
                        DataPoint(2f, 18f),
                        DataPoint(3f, 25f),
                        DataPoint(4f, 22f),
                        DataPoint(5f, 30f)
                    ),
                    lineColor = AppColors.Red,
                    isCurved = false
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightLarge)
    )
}

@Preview(showBackground = true)
@Composable
private fun CurvedLineChartPreview() {
    CurvedLineChart()
}

