package com.majid2851.charts.ui.components.area

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.AreaChartData
import com.majid2851.charts.domain.model.AreaDataSet
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.AppColors.withAlpha
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun AreaChart(
    data: AreaChartData,
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
                text = Strings.AREA_CHART + Strings.PLACEHOLDER_AREA_MODE + data.stackingMode.name,
                color = AppColors.PlaceholderText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AreaChartPreview() {
    AreaChart(
        data = AreaChartData(
            title = Strings.AREA_CHART_TITLE,
            areas = listOf(
                AreaDataSet(
                    label = Strings.AREA_1,
                    dataPoints = listOf(
                        DataPoint(0f, 10f),
                        DataPoint(1f, 25f),
                        DataPoint(2f, 15f),
                        DataPoint(3f, 30f)
                    ),
                    lineColor = AppColors.Blue,
                    fillColor = AppColors.Blue.withAlpha(0.3f)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

