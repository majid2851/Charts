package com.majid2851.charts.ui.components.pie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun PieChart(
    data: PieChartData,
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
                modifier = Modifier.padding(bottom = Dimens.paddingSmall)
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
                text = Strings.PIE_CHART + Strings.PLACEHOLDER_PIE_STYLE + data.pieStyle.name,
                color = AppColors.PlaceholderText
            )
        }

        if (data.config.showLegend) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.legendTopPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.legendItemInternalSpacing)
            ) {
                data.slices.forEach { slice ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.legendItemInternalSpacing),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(Dimens.legendIconSize)
                                .background(slice.color)
                        )
                        Text(
                            text = slice.label,
                            fontSize = FontSizes.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PieChartPreview() {
    PieChart(
        data = PieChartData(
            title = Strings.PIE_CHART_TITLE,
            slices = listOf(
                PieSlice(Strings.CATEGORY_A, 30f, AppColors.Blue),
                PieSlice(Strings.CATEGORY_B, 25f, AppColors.Red),
                PieSlice(Strings.CATEGORY_C, 20f, AppColors.Green),
                PieSlice(Strings.CATEGORY_D, 25f, AppColors.Yellow)
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

