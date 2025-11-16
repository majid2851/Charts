package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes

@Preview(showBackground = true)
@Composable
fun LineChartVariantsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.paddingDefault),
        verticalArrangement = Arrangement.spacedBy(Dimens.paddingLarge)
    ) {
        Text("LineChart Variants Gallery", fontSize = FontSizes.titleLarge)
        Divider()

        SimpleLineChart()
        Divider()

        TinyLineChart()
        Divider()

        DashedLineChart()
        Divider()

        LineChartWithReferenceLines()
        Divider()

        LineChartConnectNulls(false)
        Divider()

        LineChartConnectNulls(true)
        Divider()

        CustomizedDotLineChart()
        Divider()

        CurvedLineChart()
        Divider()

        FilledAreaLineChart()
        Divider()

        MultiSeriesLineChart()
        Divider()

        NegativeValuesLineChart()
    }
}

