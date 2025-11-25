package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Strings

@Composable
fun InteractiveLineChartExample(modifier: Modifier = Modifier) {
    var selectedInfo by remember { mutableStateOf("Tap on a point to see its data") }
    
    Column(modifier = modifier.padding(Dimens.paddingSmall)) {

        
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
                        pointRadius = Dimens.pointRadiusDefault,
                        customPointShape = PointShape.CIRCLE,
                        interactionConfig = PointInteractionConfig(
                            enableInteraction = true,
                            activePointRadius = 12f,
                            activePointColor = AppColors.RechartsBlue,
                            activeLineColor = AppColors.RechartsBlue.copy(alpha = 1f),
                            activeLineWidth = 4f
                        )
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
                        pointRadius = Dimens.pointRadiusDefault,
                        customPointShape = PointShape.CIRCLE,
                        interactionConfig = PointInteractionConfig(
                            enableInteraction = true,
                            activePointRadius = 12f,
                            activePointColor = AppColors.RechartsGreen,
                            activeLineColor = AppColors.RechartsGreen.copy(alpha = 1f),
                            activeLineWidth = 4f
                        )
                    )
                ),
                config = ChartConfig(
                    showGrid = true,
                    showAxis = true,
                    showLegend = true,
                    animationEnabled = true,
                    backgroundColor = AppColors.Transparent,
                    chartPadding = Dimens.paddingDefault,
                    isInteractive = true,
                    cartesianGrid = CartesianGridPresets.rechartsStyleGrid()
                ),
                xAxisConfig = AxisConfig(
                    showLabels = true,
                    showGridLines = true,
                    labelCount = 7,
                    axisColor = AppColors.AxisColorDefault,
                    gridColor = AppColors.GridColorDefault,
                    labelTextSize = FontSizes.bodySmall.value
                ),
                yAxisConfig = AxisConfig(
                    showLabels = true,
                    showGridLines = true,
                    labelCount = 5,
                    axisColor = AppColors.AxisColorDefault,
                    gridColor = AppColors.GridColorDefault,
                    labelTextSize = FontSizes.bodySmall.value
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.chartHeightLarge),
            onPointSelected = { lineIndex, pointIndex, dataPoint ->
                dataPoint?.let {
                    val lineName = if (lineIndex == 0) Strings.LABEL_PV else Strings.LABEL_UV
                    selectedInfo = "Line: $lineName\nPoint: ${it.label}\nValue: ${it.y}"
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InteractiveLineChartExamplePreview() {
    InteractiveLineChartExample()
}
