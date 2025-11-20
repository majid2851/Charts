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
fun DashedLineChart()
{
    LineChart(
        data = LineChartData(
            title = "Dashed Line Chart",
            lines = listOf(
                LineDataSet(
                    label = "Target",
                    dataPoints = listOf(
                        DataPoint(0f, 100f),
                        DataPoint(1f, 200f),
                        DataPoint(2f, 150f),
                        DataPoint(3f, 300f),
                        DataPoint(4f, 250f)
                    ),
                    lineColor = AppColors.Blue,
                    isDashed = true,
                    dashPattern = floatArrayOf(10f, 5f),
                ),
                LineDataSet(
                    label = "Actual",
                    dataPoints = listOf(
                        DataPoint(0f, 80f),
                        DataPoint(1f, 180f),
                        DataPoint(2f, 170f),
                        DataPoint(3f, 280f),
                        DataPoint(4f, 260f)
                    ),
                    lineColor = AppColors.Green,
                    isDashed = false,
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
private fun DashedLineChartPreview() {
    DashedLineChart()
}

