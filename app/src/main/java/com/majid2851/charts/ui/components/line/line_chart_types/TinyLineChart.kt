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
fun TinyLineChart() {
    LineChart(
        data = LineChartData(
            title = "Tiny Line Chart",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 15f),
                        DataPoint(2f, 8f),
                        DataPoint(3f, 20f),
                        DataPoint(4f, 12f)
                    ),
                    lineColor = AppColors.Blue,
                    showPoints = false,
                )
            ),
            config = ChartConfig(
                showGrid = false,
                showAxis = false,
                showLegend = false,
                chartPadding = Dimens.paddingSmall
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.tinyChartHeight)
    )
}

@Preview(showBackground = true)
@Composable
private fun TinyLineChartPreview() {
    TinyLineChart()
}

