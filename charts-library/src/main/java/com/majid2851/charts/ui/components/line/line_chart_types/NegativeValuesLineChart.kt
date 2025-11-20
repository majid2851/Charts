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

@Composable
fun NegativeValuesLineChart(
    modifier: Modifier = Modifier,
    dataPoints: List<DataPoint> = listOf(
        DataPoint(1f, -50f),
        DataPoint(2f, -20f),
        DataPoint(3f, 10f),
        DataPoint(4f, 50f),
        DataPoint(5f, 30f),
        DataPoint(6f, 80f)
    ),
    title: String = "Profit/Loss Chart",
    label: String = "Net Profit",
    lineColor: Color = AppColors.Blue,
    lineWidth: Float = 2f,
    showPoints: Boolean = true,
    pointRadius: Float = 6f,
    customPointShape: PointShape = PointShape.CIRCLE,
    isCurved: Boolean = true,
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    showZeroReferenceLine: Boolean = true,
    zeroLineColor: Color = AppColors.Red,
    zeroLineLabel: String = "Break Even",
    chartPadding: Dp = Dimens.paddingDefault,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = true,
    yAxisLabelCount: Int = 7
) {
    LineChart(
        data = LineChartData(
            title = title,
            lines = listOf(
                LineDataSet(
                    label = label,
                    dataPoints = dataPoints,
                    lineColor = lineColor,
                    lineWidth = lineWidth,
                    showPoints = showPoints,
                    pointRadius = pointRadius,
                    customPointShape = customPointShape,
                    isCurved = isCurved,
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
                isInteractive = isInteractive,
                chartPadding = chartPadding,
                cartesianGrid = CartesianGridPresets.rechartsStyleGrid()
            ),
            xAxisConfig = AxisConfig(
                showLabels = showAxis,
                showGridLines = showGrid,
                labelCount = 6,
                axisColor = AppColors.AxisColorDefault,
                gridColor = AppColors.GridColorDefault,
                labelTextSize = FontSizes.bodyMedium.value
            ),
            yAxisConfig = AxisConfig(
                showLabels = showAxis,
                showGridLines = showGrid,
                labelCount = yAxisLabelCount,
                axisColor = AppColors.AxisColorDefault,
                gridColor = AppColors.GridColorDefault,
                labelTextSize = FontSizes.bodyMedium.value
            ),
            referenceLines = if (showZeroReferenceLine) {
                listOf(
                    ReferenceLine(
                        value = 0f,
                        label = zeroLineLabel,
                        color = zeroLineColor,
                        isDashed = false,
                        strokeWidth = 2f
                    )
                )
            } else {
                emptyList()
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightLarge)
    )
}

@Preview(showBackground = true)
@Composable
private fun NegativeValuesLineChartPreview() {
    NegativeValuesLineChart()
}

