package com.majid2851.charts.ui.components.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun LineChart(
    data: LineChartData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {
        data.title?.let {
            Text(
                text = it,
                fontSize = FontSizes.titleLarge,
                modifier = Modifier.padding(bottom = Dimens.chartPaddingSmall)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ChartCanvas { }

            Text(
                text = Strings.LINE_CHART + " - ${data.lines.size}" + Strings.PLACEHOLDER_LINE_COUNT,
                color = AppColors.PlaceholderText
            )
        }

        if (data.config.showLegend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.legendTopPadding),
                horizontalArrangement = Arrangement.spacedBy(Dimens.legendItemSpacing)
            ) {
                data.lines.forEach { line ->
                    Text(
                        text = line.label,
                        fontSize = FontSizes.bodySmall,
                        color = line.lineColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LineChartPreview() {
    LineChart(
        data = LineChartData(
            title = Strings.LINE_CHART_TITLE,
            lines = listOf(
                LineDataSet(
                    label = Strings.SERIES_1,
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 25f),
                        DataPoint(2f, 15f),
                        DataPoint(3f, 30f),
                        DataPoint(4f, 20f)
                    ),
                    lineColor = AppColors.Blue
                ),
                LineDataSet(
                    label = Strings.SERIES_2,
                    dataPoints = listOf(
                        DataPoint(0f, 5f),
                        DataPoint(1f, 15f),
                        DataPoint(2f, 25f),
                        DataPoint(3f, 20f),
                        DataPoint(4f, 30f)
                    ),
                    lineColor = AppColors.Red
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

