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
fun MultiSeriesLineChart() {
    LineChart(
        data = LineChartData(
            title = "Multi-Series Comparison",
            lines = listOf(
                LineDataSet(
                    label = "Product A",
                    dataPoints = listOf(
                        DataPoint(1f, 100f),
                        DataPoint(2f, 150f),
                        DataPoint(3f, 120f),
                        DataPoint(4f, 180f),
                        DataPoint(5f, 160f)
                    ),
                    lineColor = AppColors.Blue
                ),
                LineDataSet(
                    label = "Product B",
                    dataPoints = listOf(
                        DataPoint(1f, 80f),
                        DataPoint(2f, 130f),
                        DataPoint(3f, 140f),
                        DataPoint(4f, 150f),
                        DataPoint(5f, 170f)
                    ),
                    lineColor = AppColors.Red
                ),
                LineDataSet(
                    label = "Product C",
                    dataPoints = listOf(
                        DataPoint(1f, 70f),
                        DataPoint(2f, 90f),
                        DataPoint(3f, 110f),
                        DataPoint(4f, 130f),
                        DataPoint(5f, 150f)
                    ),
                    lineColor = AppColors.Green
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightMedium)
    )
}

@Preview(showBackground = true)
@Composable
private fun MultiSeriesLineChartPreview() {
    MultiSeriesLineChart()
}

