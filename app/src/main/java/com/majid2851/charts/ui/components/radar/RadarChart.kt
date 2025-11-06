package com.majid2851.charts.ui.components.radar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.RadarChartData
import com.majid2851.charts.domain.model.RadarDataSet
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.AppColors.withAlpha
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun RadarChart(
    data: RadarChartData,
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
                text = Strings.RADAR_CHART + " - ${data.labels.size}" + Strings.PLACEHOLDER_RADAR_AXES,
                color = AppColors.PlaceholderText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RadarChartPreview() {
    RadarChart(
        data = RadarChartData(
            title = Strings.RADAR_CHART_TITLE,
            labels = listOf(Strings.SPEED, Strings.POWER, Strings.DEFENSE, Strings.SKILL, Strings.ACCURACY),
            dataSets = listOf(
                RadarDataSet(
                    label = Strings.PLAYER_1,
                    values = listOf(80f, 90f, 70f, 85f, 75f),
                    lineColor = AppColors.Blue,
                    fillColor = AppColors.Blue.withAlpha(0.3f)
                )
            ),
            maxValue = 100f
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

