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
fun LineChartConnectNulls(connectNulls: Boolean = false) {
    LineChart(
        data = LineChartData(
            title = if (connectNulls) "Connect Nulls: ON" else "Connect Nulls: OFF",
            lines = listOf(
                LineDataSet(
                    label = "Data with Gaps",
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 25f),
                        null, // Gap in data
                        DataPoint(3f, 30f),
                        DataPoint(4f, 20f),
                        null, // Another gap
                        DataPoint(6f, 35f)
                    ),
                    lineColor = AppColors.Blue
                )
            ),
            connectNulls = connectNulls
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightLarge)
    )
}

@Preview(showBackground = true)
@Composable
private fun LineChartConnectNullsOffPreview() {
    LineChartConnectNulls(connectNulls = false)
}

@Preview(showBackground = true)
@Composable
private fun LineChartConnectNullsOnPreview() {
    LineChartConnectNulls(connectNulls = true)
}

