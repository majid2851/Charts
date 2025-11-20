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
fun LineChartWithReferenceLines() {
    LineChart(
        data = LineChartData(
            title = "Sales with Target & Average",
            lines = listOf(
                LineDataSet(
                    label = "Sales",
                    dataPoints = listOf(
                        DataPoint(1f, 400f),
                        DataPoint(2f, 300f),
                        DataPoint(3f, 600f),
                        DataPoint(4f, 800f),
                        DataPoint(5f, 500f)
                    ),
                    lineColor = AppColors.Blue,
                )
            ),
            referenceLines = listOf(
                ReferenceLine(
                    value = 600f,
                    label = "Target",
                    color = AppColors.Red,
                    isDashed = true
                ),
                ReferenceLine(
                    value = 520f,
                    label = "Average",
                    color = AppColors.Green,
                    isDashed = true
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
private fun LineChartWithReferenceLinesPreview() {
    LineChartWithReferenceLines()
}

