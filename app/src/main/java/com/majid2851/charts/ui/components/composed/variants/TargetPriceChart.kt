//package com.majid2851.charts.ui.components.composed.variants
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import com.majid2851.charts.domain.model.*
//import com.majid2851.charts.ui.components.composed.ComposedChart
//import com.majid2851.charts.ui.theme.Dimens
//
///**
// * Target Price Chart
// * Shows actual price, target price, and confidence bands
// * Simplified version of the Recharts Target Price Chart example
// */
//@Composable
//fun TargetPriceChart(
//    modifier: Modifier = Modifier,
//    width: Dp = Dimens.previewChartWidth,
//    height: Dp = Dimens.chartHeightLarge,
//    title: String = "Target Price Chart",
//    categories: List<String> = getDefaultTargetPriceCategories(),
//    actualPriceData: List<Float> = getDefaultActualPriceData(),
//    targetPriceData: List<Float> = getDefaultTargetPriceData(),
//    priceRangeData: List<Float> = getDefaultPriceRangeData(),
//    lowBoundData: List<Float> = getDefaultLowBoundData(),
//    actualPriceColor: Color = Color(0xFF483D8B),
//    targetPriceColor: Color = Color(0xFFFF8C00),
//    rangeColor: Color = Color(0xFFFFA500),
//    showGrid: Boolean = true,
//    showAxis: Boolean = true,
//    showLegend: Boolean = true,
//    xAxisLabel: String = "Date",
//    yAxisLabel: String = "Price ($)",
//    chartPadding: Dp = 16.dp,
//    lineWidth: Float = 2f
//) {
//    val data = ComposedChartData(
//        title = title,
//        categories = categories,
//
//        // Low-High range (confidence band)
//        areaDataSets = listOf(
//            AreaDataSet(
//                label = "Price Range",
//                dataPoints = priceRangeData.mapIndexed { index, value ->
//                    DataPoint(index.toFloat(), value)
//                },
//                lineColor = rangeColor.copy(alpha = 0.3f),
//                fillColor = rangeColor.copy(alpha = 0.12f)
//            )
//        ),
//
//        // Price lines
//        lineDataSets = listOf(
//            LineDataSet(
//                label = "Actual Price",
//                dataPoints = actualPriceData.mapIndexed { index, value ->
//                    DataPoint(index.toFloat(), value)
//                },
//                lineColor = actualPriceColor,
//                lineWidth = lineWidth
//            ),
//            LineDataSet(
//                label = "Target Price",
//                dataPoints = targetPriceData.mapIndexed { index, value ->
//                    DataPoint(index.toFloat(), value)
//                },
//                lineColor = targetPriceColor,
//                lineWidth = lineWidth
//            ),
//            LineDataSet(
//                label = "Low",
//                dataPoints = lowBoundData.mapIndexed { index, value ->
//                    DataPoint(index.toFloat(), value)
//                },
//                lineColor = Color.Transparent,
//                lineWidth = 0f,
//                showPoints = false
//            )
//        ),
//
//        config = ChartConfig(
//            showGrid = showGrid,
//            showAxis = showAxis,
//            showLegend = showLegend,
//            chartPadding = chartPadding,
//            cartesianGrid = CartesianGridConfig(
//                showVerticalLines = true,
//                verticalLineColor = Color.LightGray,
//                horizontalLineColor = Color.LightGray
//            )
//        ),
//        xAxisConfig = AxisConfig(
//            showLabels = true,
//            axisLabel = xAxisLabel
//        ),
//        yAxisConfig = AxisConfig(
//            showLabels = true,
//            axisLabel = yAxisLabel,
//            labelPosition = LabelPosition.OUTSIDE_RIGHT
//        )
//    )
//
//    ComposedChart(
//        data = data,
//        modifier = if (modifier == Modifier) {
//            Modifier
//                .width(width)
//                .height(height)
//        } else {
//            modifier
//        }
//    )
//}
//
//private fun getDefaultTargetPriceCategories() = listOf(
//    "Jan 2023", "Mar 2023", "May 2023", "Jul 2023",
//    "Sep 2023", "Nov 2023", "Jan 2024", "Mar 2024"
//)
//
//private fun getDefaultActualPriceData() = listOf(
//    317f, 340f, 375f, 396f, 421f, 435f, 456f, 478f
//)
//
//private fun getDefaultTargetPriceData() = listOf(
//    396f, 405f, 429f, 460f, 480f, 489f, 510f, 524f
//)
//
//private fun getDefaultPriceRangeData() = listOf(
//    350f, 375f, 400f, 425f, 450f, 475f, 500f, 525f
//)
//
//private fun getDefaultLowBoundData() = listOf(
//    298f, 350f, 370f, 420f, 425f, 432f, 440f, 485f
//)
//
//@Preview(showBackground = true)
//@Composable
//private fun TargetPriceChartPreview() {
//    TargetPriceChart()
//}
//
