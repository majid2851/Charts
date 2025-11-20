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

@Composable
fun TinyLineChart(
    dataPoints: List<DataPoint> = listOf(
        DataPoint(0f, 10f),
        DataPoint(1f, 15f),
        DataPoint(2f, 8f),
        DataPoint(3f, 20f),
        DataPoint(4f, 12f)
    ),
    title: String? = "Tiny Line Chart",
    label: String = "Data",
    lineColor: Color = AppColors.Blue,
    showPoints: Boolean = true,
    pointRadius: Float = 6f,
    customPointShape: PointShape = PointShape.CIRCLE,
    lineWidth: Float = 2f,
    isCurved: Boolean = true,
    showGrid: Boolean = false,
    showAxis: Boolean = false,
    showLegend: Boolean = false,
    chartPadding: Dp = Dimens.paddingSmall,
    animationEnabled: Boolean = true,
    isInteractive: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(Dimens.chartHeightLarge)
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
                chartPadding = chartPadding,
                animationEnabled = animationEnabled,
                isInteractive = isInteractive
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun TinyLineChartPreview() {
    TinyLineChart()
}

