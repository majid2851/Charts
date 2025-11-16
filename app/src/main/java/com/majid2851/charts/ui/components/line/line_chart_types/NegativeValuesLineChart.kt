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
fun NegativeValuesLineChart() {
    LineChart(
        data = LineChartData(
            title = "Profit/Loss Chart",
            lines = listOf(
                LineDataSet(
                    label = "Net Profit",
                    dataPoints = listOf(
                        DataPoint(1f, -50f),
                        DataPoint(2f, -20f),
                        DataPoint(3f, 10f),
                        DataPoint(4f, 50f),
                        DataPoint(5f, 30f),
                        DataPoint(6f, 80f)
                    ),
                    lineColor = AppColors.Blue
                )
            ),
            referenceLines = listOf(
                ReferenceLine(
                    value = 0f,
                    label = "Break Even",
                    color = AppColors.Red,
                    isDashed = false,
                    strokeWidth = 2f
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
private fun NegativeValuesLineChartPreview() {
    NegativeValuesLineChart()
}

