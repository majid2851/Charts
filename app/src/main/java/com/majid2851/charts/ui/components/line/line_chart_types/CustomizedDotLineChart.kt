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
fun CustomizedDotLineChart() {
    LineChart(
        data = LineChartData(
            title = "Custom Point Shapes",
            lines = listOf(
                LineDataSet(
                    label = "Circle",
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 25f),
                        DataPoint(2f, 15f),
                        DataPoint(3f, 30f),
                        DataPoint(4f, 20f)
                    ),
                    lineColor = AppColors.Blue,
                    customPointShape = PointShape.CIRCLE
                ),
                LineDataSet(
                    label = "Square",
                    dataPoints = listOf(
                        DataPoint(0f, 15f),
                        DataPoint(1f, 20f),
                        DataPoint(2f, 25f),
                        DataPoint(3f, 22f),
                        DataPoint(4f, 28f)
                    ),
                    lineColor = AppColors.Red,
                    customPointShape = PointShape.SQUARE
                ),
                LineDataSet(
                    label = "Diamond",
                    dataPoints = listOf(
                        DataPoint(0f, 8f),
                        DataPoint(1f, 18f),
                        DataPoint(2f, 12f),
                        DataPoint(3f, 26f),
                        DataPoint(4f, 16f)
                    ),
                    lineColor = AppColors.Green,
                    customPointShape = PointShape.DIAMOND
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
private fun CustomizedDotLineChartPreview() {
    CustomizedDotLineChart()
}

