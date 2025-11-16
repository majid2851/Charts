package com.majid2851.charts.ui.components.line.line_chart_types

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.FontSizes

@Composable
fun LineChartWithSolidGrid() {
    LineChart(
        data = LineChartData(
            title = "Solid Grid Lines",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = AppColors.RechartsBlue,
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridPresets.solidGrid()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Composable
fun LineChartWithDashedGrid() {
    LineChart(
        data = LineChartData(
            title = "Dashed Grid Lines",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = AppColors.RechartsGreen,
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridPresets.dashedGrid()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Composable
fun LineChartWithDottedGrid() {
    LineChart(
        data = LineChartData(
            title = "Dotted Grid Lines",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = AppColors.Blue,
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridPresets.dottedGrid()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Composable
fun LineChartWithHorizontalGridOnly() {
    LineChart(
        data = LineChartData(
            title = "Horizontal Grid Only",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = AppColors.Red,
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridPresets.horizontalOnlyGrid()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Composable
fun LineChartWithVerticalGridOnly() {
    LineChart(
        data = LineChartData(
            title = "Vertical Grid Only",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = AppColors.Green,
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridPresets.verticalOnlyGrid()
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Composable
fun LineChartWithCustomGrid() {
    LineChart(
        data = LineChartData(
            title = "Custom Grid Configuration",
            lines = listOf(
                LineDataSet(
                    label = "Data",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f),
                        DataPoint(1f, 1398f),
                        DataPoint(2f, 9800f),
                        DataPoint(3f, 3908f),
                        DataPoint(4f, 4800f)
                    ),
                    lineColor = Color(0xFFFF6B6B),
                    lineWidth = Dimens.lineWidthEnhanced
                )
            ),
            config = ChartConfig(
                cartesianGrid = CartesianGridConfig(
                    showHorizontalLines = true,
                    showVerticalLines = true,
                    horizontalLineStyle = GridLineStyle.DASHED,
                    verticalLineStyle = GridLineStyle.SOLID,
                    horizontalLineColor = Color(0xFF4ECDC4),
                    verticalLineColor = Color(0xFFFFE66D),
                    horizontalLineWidth = 2f,
                    verticalLineWidth = 1f,
                    horizontalLineAlpha = 0.5f,
                    verticalLineAlpha = 0.4f,
                    horizontalDashPattern = floatArrayOf(5f, 5f),
                    horizontalLineCount = 6,
                    verticalLineCount = 8
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.chartHeightSmall)
    )
}

@Preview(showBackground = true)
@Composable
fun CartesianGridExamplesPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.paddingDefault),
        verticalArrangement = Arrangement.spacedBy(Dimens.paddingDefault)
    ) {
        Text(
            text = "CartesianGrid Examples",
            fontSize = FontSizes.titleLarge
        )
        Divider()
        
        LineChartWithSolidGrid()
        Divider()
        
        LineChartWithDashedGrid()
        Divider()
        
        LineChartWithDottedGrid()
        Divider()
        
        LineChartWithHorizontalGridOnly()
        Divider()
        
        LineChartWithVerticalGridOnly()
        Divider()
        
        LineChartWithCustomGrid()
    }
}



