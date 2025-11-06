package com.majid2851.charts.ui.components.scatter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.ScatterChartData
import com.majid2851.charts.domain.model.ScatterDataSet
import com.majid2851.charts.domain.model.ScatterPoint
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun ScatterChart(
    data: ScatterChartData,
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
                text = Strings.SCATTER_CHART,
                color = AppColors.PlaceholderText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScatterChartPreview() {
    ScatterChart(
        data = ScatterChartData(
            title = Strings.SCATTER_CHART_TITLE,
            scatterSets = listOf(
                ScatterDataSet(
                    label = Strings.DATASET_1,
                    dataPoints = listOf(
                        ScatterPoint(10f, 20f, Strings.POINT_1),
                        ScatterPoint(15f, 30f, Strings.POINT_2),
                        ScatterPoint(20f, 25f, Strings.POINT_3),
                        ScatterPoint(25f, 35f, Strings.POINT_4)
                    ),
                    pointColor = AppColors.Blue
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

