package com.majid2851.charts.ui.components.gauge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.GaugeChartData
import com.majid2851.charts.domain.model.GaugeRange
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun GaugeChart(
    data: GaugeChartData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding),
        horizontalAlignment = Alignment.CenterHorizontally
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.chartPaddingSmall)
            ) {
                Text(
                    text = Strings.GAUGE_CHART + Strings.PLACEHOLDER_PIE_STYLE + data.gaugeStyle.name,
                    color = AppColors.PlaceholderText
                )
                if (data.showValue) {
                    Text(
                        text = "${data.currentValue.toInt()}",
                        fontSize = Dimens.gaugeValueFontSize.sp,
                        color = AppColors.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GaugeChartPreview() {
    GaugeChart(
        data = GaugeChartData(
            title = Strings.GAUGE_CHART_TITLE,
            currentValue = 65f,
            minValue = 0f,
            maxValue = 100f,
            ranges = listOf(
                GaugeRange(0f, 33f, AppColors.GaugeRangeLow, Strings.RANGE_LOW),
                GaugeRange(33f, 66f, AppColors.GaugeRangeMedium, Strings.RANGE_MEDIUM),
                GaugeRange(66f, 100f, AppColors.GaugeRangeHigh, Strings.RANGE_HIGH)
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

