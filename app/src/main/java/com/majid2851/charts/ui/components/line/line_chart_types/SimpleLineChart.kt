package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Strings

@Composable
fun SimpleLineChart(
    modifier: Modifier = Modifier,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    enableCurvedLines: Boolean = true,
    activeDotRadius: Float = Dimens.pointRadiusActive,
    standardDotRadius: Float = Dimens.pointRadiusDefault,
    cartesianGridConfig: CartesianGridConfig = CartesianGridPresets.rechartsStyleGrid()
) {
    LineChart(
        data = LineChartData(
            title = Strings.SIMPLE_LINE_CHART_TITLE,
            lines = listOf(
                LineDataSet(
                    label = Strings.LABEL_PV,
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 2400f, label = Strings.PAGE_A),
                        DataPoint(x = 1f, y = 1398f, label = Strings.PAGE_B),
                        DataPoint(x = 2f, y = 9800f, label = Strings.PAGE_C),
                        DataPoint(x = 3f, y = 3908f, label = Strings.PAGE_D),
                        DataPoint(x = 4f, y = 4800f, label = Strings.PAGE_E),
                        DataPoint(x = 5f, y = 3800f, label = Strings.PAGE_F),
                        DataPoint(x = 6f, y = 4300f, label = Strings.PAGE_G)
                    ),
                    lineColor = AppColors.RechartsBlue,
                    lineWidth = Dimens.lineWidthEnhanced,
                    showPoints = true,
                    pointRadius = activeDotRadius,
                    isCurved = enableCurvedLines,
                    customPointShape = PointShape.CIRCLE
                ),
                LineDataSet(
                    label = Strings.LABEL_UV,
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 4000f, label = Strings.PAGE_A),
                        DataPoint(x = 1f, y = 3000f, label = Strings.PAGE_B),
                        DataPoint(x = 2f, y = 2000f, label = Strings.PAGE_C),
                        DataPoint(x = 3f, y = 2780f, label = Strings.PAGE_D),
                        DataPoint(x = 4f, y = 1890f, label = Strings.PAGE_E),
                        DataPoint(x = 5f, y = 2390f, label = Strings.PAGE_F),
                        DataPoint(x = 6f, y = 3490f, label = Strings.PAGE_G)
                    ),
                    lineColor = AppColors.RechartsGreen,
                    lineWidth = Dimens.lineWidthEnhanced,
                    showPoints = true,
                    pointRadius = standardDotRadius,
                    isCurved = enableCurvedLines,
                    customPointShape = PointShape.CIRCLE
                )
            ),
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                animationEnabled = true,
                animationDuration = 300,
                backgroundColor = AppColors.Transparent,
                chartPadding = Dimens.paddingDefault,
                isInteractive = true,
                cartesianGrid = cartesianGridConfig
            ),
            xAxisConfig = AxisConfig(
                showLabels = true,
                showGridLines = true,
                labelCount = 7,
                axisColor = AppColors.AxisColorDefault,
                gridColor = AppColors.GridColorDefault,
                labelTextSize = FontSizes.bodyMedium.value
            ),
            yAxisConfig = AxisConfig(
                showLabels = true,
                showGridLines = true,
                labelCount = 5,
                axisColor = AppColors.AxisColorDefault,
                gridColor = AppColors.GridColorDefault,
                labelTextSize = FontSizes.bodyMedium.value
            )
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightLarge)
            .padding(horizontal = Dimens.paddingSmall, vertical = Dimens.paddingSmall)
    )
}


@Preview(showBackground = true)
@Composable
private fun SimpleLineChartPreview() {
    SimpleLineChart()
}

