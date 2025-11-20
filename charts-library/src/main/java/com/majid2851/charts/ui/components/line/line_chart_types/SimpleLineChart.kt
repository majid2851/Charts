package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Strings

@Composable
fun SimpleLineChart(
    modifier: Modifier = Modifier,
    line1DataPoints: List<DataPoint> = listOf(
        DataPoint(x = 0f, y = 2400f, label = Strings.PAGE_A),
        DataPoint(x = 1f, y = 1398f, label = Strings.PAGE_B),
        DataPoint(x = 2f, y = 9800f, label = Strings.PAGE_C),
        DataPoint(x = 3f, y = 3908f, label = Strings.PAGE_D),
        DataPoint(x = 4f, y = 4800f, label = Strings.PAGE_E),
        DataPoint(x = 5f, y = 3800f, label = Strings.PAGE_F),
        DataPoint(x = 6f, y = 4300f, label = Strings.PAGE_G)
    ),
    line2DataPoints: List<DataPoint> = listOf(
        DataPoint(x = 0f, y = 4000f, label = Strings.PAGE_A),
        DataPoint(x = 1f, y = 3000f, label = Strings.PAGE_B),
        DataPoint(x = 2f, y = 2000f, label = Strings.PAGE_C),
        DataPoint(x = 3f, y = 2780f, label = Strings.PAGE_D),
        DataPoint(x = 4f, y = 1890f, label = Strings.PAGE_E),
        DataPoint(x = 5f, y = 2390f, label = Strings.PAGE_F),
        DataPoint(x = 6f, y = 3490f, label = Strings.PAGE_G)
    ),
    title: String = Strings.SIMPLE_LINE_CHART_TITLE,
    line1Label: String = Strings.LABEL_PV,
    line2Label: String = Strings.LABEL_UV,
    line1Color: Color = AppColors.RechartsBlue,
    line1Width: Float = Dimens.lineWidthEnhanced,
    line1ShowPoints: Boolean = true,
    line1PointRadius: Float = Dimens.pointRadiusActive,
    line1PointShape: PointShape = PointShape.CIRCLE,
    line2Color: Color = AppColors.RechartsGreen,
    line2Width: Float = Dimens.lineWidthEnhanced,
    line2ShowPoints: Boolean = true,
    line2PointRadius: Float = Dimens.pointRadiusDefault,
    line2PointShape: PointShape = PointShape.CIRCLE,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    enableCurvedLines: Boolean = true,
    animationEnabled: Boolean = true,
    animationDuration: Int = 300,
    isInteractive: Boolean = true,
    chartPadding: Dp = Dimens.paddingDefault,
    backgroundColor: Color = AppColors.Transparent,
    cartesianGridConfig: CartesianGridConfig = CartesianGridPresets.rechartsStyleGrid(),
    xAxisLabelCount: Int = 7,
    yAxisLabelCount: Int = 5,
    axisColor: Color = AppColors.AxisColorDefault,
    gridColor: Color = AppColors.GridColorDefault,
    axisLabelTextSize: Float = FontSizes.bodyMedium.value
) {
    LineChart(
        data = LineChartData(
            title = title,
            lines = listOf(
                LineDataSet(
                    label = line1Label,
                    dataPoints = line1DataPoints,
                    lineColor = line1Color,
                    lineWidth = line1Width,
                    showPoints = line1ShowPoints,
                    pointRadius = line1PointRadius,
                    isCurved = enableCurvedLines,
                    customPointShape = line1PointShape,
                    interactionConfig = PointInteractionConfig(
                        enableInteraction = isInteractive
                    )
                ),
                LineDataSet(
                    label = line2Label,
                    dataPoints = line2DataPoints,
                    lineColor = line2Color,
                    lineWidth = line2Width,
                    showPoints = line2ShowPoints,
                    pointRadius = line2PointRadius,
                    isCurved = enableCurvedLines,
                    customPointShape = line2PointShape,
                    interactionConfig = PointInteractionConfig(
                        enableInteraction = isInteractive
                    )
                )
            ),
            config = ChartConfig(
                showGrid = showGrid,
                showAxis = showAxis,
                showLegend = showLegend,
                animationEnabled = animationEnabled,
                animationDuration = animationDuration,
                backgroundColor = backgroundColor,
                chartPadding = chartPadding,
                isInteractive = isInteractive,
                cartesianGrid = cartesianGridConfig
            ),
            xAxisConfig = AxisConfig(
                showLabels = showAxis,
                showGridLines = showGrid,
                labelCount = xAxisLabelCount,
                axisColor = axisColor,
                gridColor = gridColor,
                labelTextSize = axisLabelTextSize
            ),
            yAxisConfig = AxisConfig(
                showLabels = showAxis,
                showGridLines = showGrid,
                labelCount = yAxisLabelCount,
                axisColor = axisColor,
                gridColor = gridColor,
                labelTextSize = axisLabelTextSize
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

