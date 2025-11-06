package com.majid2851.charts.ui.components.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.domain.model.BarDataSet
import com.majid2851.charts.domain.model.LabeledEntry
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun BarChart(
    data: BarChartData,
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
                text = Strings.BAR_CHART + Strings.PLACEHOLDER_BAR_STYLE + data.barStyle.name,
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
                data.bars.forEach { bar ->
                    Text(
                        text = bar.label,
                        fontSize = FontSizes.bodySmall,
                        color = bar.barColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    BarChart(
        data = BarChartData(
            title = Strings.BAR_CHART_TITLE,
            bars = listOf(
                BarDataSet(
                    label = Strings.DATASET_1,
                    entries = listOf(
                        LabeledEntry(Strings.Q1, 100f),
                        LabeledEntry(Strings.Q2, 150f),
                        LabeledEntry(Strings.Q3, 120f),
                        LabeledEntry(Strings.Q4, 180f)
                    ),
                    barColor = AppColors.Blue
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

