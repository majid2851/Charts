package com.majid2851.charts.ui.components.candlestick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.CandleEntry
import com.majid2851.charts.domain.model.CandlestickChartData
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun CandlestickChart(
    data: CandlestickChartData,
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
                text = Strings.CANDLESTICK_CHART + " - ${data.candles.size}" + Strings.PLACEHOLDER_CANDLE_COUNT,
                color = AppColors.PlaceholderText
            )
        }

        if (data.showVolume) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.volumeChartHeight)
                    .background(AppColors.VolumeBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = Strings.VOLUME,
                    fontSize = FontSizes.bodySmall,
                    color = AppColors.PlaceholderText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CandlestickChartPreview() {
    CandlestickChart(
        data = CandlestickChartData(
            title = Strings.CANDLESTICK_CHART_TITLE,
            candles = listOf(
                CandleEntry(1L, 100f, 110f, 95f, 105f),
                CandleEntry(2L, 105f, 115f, 100f, 110f),
                CandleEntry(3L, 110f, 120f, 105f, 115f),
                CandleEntry(4L, 115f, 118f, 108f, 112f),
                CandleEntry(5L, 112f, 125f, 110f, 120f)
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.previewChartHeight)
    )
}

