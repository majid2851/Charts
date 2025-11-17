package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.area.AreaChart
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.ui.components.candlestick.CandlestickChart
import com.majid2851.charts.ui.components.gauge.GaugeChart
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.ui.components.radar.RadarChart
import com.majid2851.charts.ui.components.scatter.ScatterChart
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.AppColors.withAlpha
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.ChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartDemoScreen(modifier: Modifier = Modifier) {
    val chartTypes = remember {
        listOf(
            Strings.LINE_CHART,
            Strings.BAR_CHART,
            Strings.PIE_CHART,
            Strings.AREA_CHART,
            Strings.SCATTER_CHART,
            Strings.RADAR_CHART,
            Strings.CANDLESTICK_CHART,
            Strings.GAUGE_CHART
        )
    }

    var selectedChart by remember { mutableStateOf(chartTypes[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Strings.APP_TITLE) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.paddingDefault),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = Strings.SELECT_CHART_TYPE,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = Dimens.paddingSmall)
                    )
                }

                item {
                    LazyRow {
                        items(chartTypes) { chartType ->
                            FilterChip(
                                selected = selectedChart == chartType,
                                onClick = { selectedChart = chartType },
                                label = { Text(chartType) },
                                modifier = Modifier
                                    .padding(horizontal = Dimens.paddingSmall)
                            )
                        }
                    }
                }

                item {
                    Divider(modifier = Modifier.padding(vertical = Dimens.dividerVerticalPadding))
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens.cardHeight),
                        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.cardElevation)
                    ) {
                        when (selectedChart) {
                            Strings.LINE_CHART -> LineChart(
                                data = getSampleLineChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.BAR_CHART -> BarChart(
                                data = getSampleBarChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.PIE_CHART -> PieChart(
                                data = getSamplePieChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.AREA_CHART -> AreaChart(
                                data = getSampleAreaChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.SCATTER_CHART -> ScatterChart(
                                data = getSampleScatterChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.RADAR_CHART -> RadarChart(
                                data = getSampleRadarChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.CANDLESTICK_CHART -> CandlestickChart(
                                data = getSampleCandlestickChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.GAUGE_CHART -> GaugeChart(
                                data = getSampleGaugeChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(Dimens.spacerHeight))
                }
            }
        }
    }
}

private fun getSampleLineChartData() = LineChartData(
    title = Strings.LINE_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    lines = listOf(
        LineDataSet(
            label = "pv",
            dataPoints = listOf(
                DataPoint(0f, 2400f, "Page A"),
                DataPoint(1f, 1398f, "Page B"),
                DataPoint(2f, 9800f, "Page C"),
                DataPoint(3f, 3908f, "Page D"),
                DataPoint(4f, 4800f, "Page E"),
                DataPoint(5f, 3800f, "Page F"),
                DataPoint(6f, 4300f, "Page G")
            ),
            lineColor = androidx.compose.ui.graphics.Color(0xFF8884d8)
        ),
        LineDataSet(
            label = "uv",
            dataPoints = listOf(
                DataPoint(0f, 4000f, "Page A"),
                DataPoint(1f, 3000f, "Page B"),
                DataPoint(2f, 2000f, "Page C"),
                DataPoint(3f, 2780f, "Page D"),
                DataPoint(4f, 1890f, "Page E"),
                DataPoint(5f, 2390f, "Page F"),
                DataPoint(6f, 3490f, "Page G")
            ),
            lineColor = androidx.compose.ui.graphics.Color(0xFF82ca9d)
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        isInteractive = true
    ),
    xAxisConfig = AxisConfig(
        showLabels = true,
        labelTextSize = 32f,  // ✅ Increased X-axis font size
        axisColor = androidx.compose.ui.graphics.Color.Black
    ),
    yAxisConfig = AxisConfig(
        showLabels = true,
        labelTextSize = 32f,  // ✅ Increased Y-axis font size
        labelCount = 5,
        axisColor = androidx.compose.ui.graphics.Color.Black
    )
)

private fun getSampleBarChartData() = BarChartData(
    title = Strings.BAR_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    bars = listOf(
        BarDataSet(
            label = Strings.DATASET_1,
            entries = listOf(
                LabeledEntry(Strings.Q1, 100f),
                LabeledEntry(Strings.Q2, 150f),
                LabeledEntry(Strings.Q3, 120f),
                LabeledEntry(Strings.Q4, 180f)
            ),
            barColor = AppColors.Blue
        )
    )
)

private fun getSamplePieChartData() = PieChartData(
    title = Strings.PIE_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    slices = listOf(
        PieSlice(Strings.CATEGORY_A, 30f, AppColors.Blue),
        PieSlice(Strings.CATEGORY_B, 25f, AppColors.Red),
        PieSlice(Strings.CATEGORY_C, 20f, AppColors.Green),
        PieSlice(Strings.CATEGORY_D, 25f, AppColors.Yellow)
    )
)

private fun getSampleAreaChartData() = AreaChartData(
    title = Strings.AREA_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    areas = listOf(
        AreaDataSet(
            label = Strings.AREA_1,
            dataPoints = listOf(
                DataPoint(0f, 10f),
                DataPoint(1f, 25f),
                DataPoint(2f, 15f),
                DataPoint(3f, 30f)
            ),
            lineColor = AppColors.Blue,
            fillColor = AppColors.Blue.withAlpha(0.3f)
        )
    )
)

private fun getSampleScatterChartData() = ScatterChartData(
    title = Strings.SCATTER_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    scatterSets = listOf(
        ScatterDataSet(
            label = Strings.DATASET_1,
            dataPoints = listOf(
                ScatterPoint(10f, 20f, label = Strings.POINT_1),
                ScatterPoint(15f, 30f, label = Strings.POINT_2),
                ScatterPoint(20f, 25f, label = Strings.POINT_3),
                ScatterPoint(25f, 35f, label = Strings.POINT_4)
            ),
            pointColor = AppColors.Blue
        )
    )
)

private fun getSampleRadarChartData() = RadarChartData(
    title = Strings.RADAR_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    labels = listOf(
        Strings.SPEED, Strings.POWER,
        Strings.DEFENSE, Strings.SKILL,
        Strings.ACCURACY
    ),
    dataSets = listOf(
        RadarDataSet(
            label = Strings.PLAYER_1,
            values = listOf(80f, 90f, 70f, 85f, 75f),
            lineColor = AppColors.Blue,
            fillColor = AppColors.Blue.withAlpha(0.3f)
        )
    ),
    maxValue = 100f
)

private fun getSampleCandlestickChartData() = CandlestickChartData(
    title = Strings.CANDLESTICK_CHART_TITLE,
    description = Strings.FINANCIAL_CHART_DESCRIPTION,
    candles = listOf(
        CandleEntry(1L, 100f, 110f, 95f, 105f),
        CandleEntry(2L, 105f, 115f, 100f, 110f),
        CandleEntry(3L, 110f, 120f, 105f, 115f),
        CandleEntry(4L, 115f, 118f, 108f, 112f),
        CandleEntry(5L, 112f, 125f, 110f, 120f)
    )
)

private fun getSampleGaugeChartData() = GaugeChartData(
    title = Strings.GAUGE_CHART_TITLE,
    description = Strings.SAMPLE_DESCRIPTION,
    currentValue = 65f,
    minValue = 0f,
    maxValue = 100f,
    ranges = listOf(
        GaugeRange(0f, 33f, AppColors.GaugeRangeLow, Strings.RANGE_LOW),
        GaugeRange(33f, 66f, AppColors.GaugeRangeMedium, Strings.RANGE_MEDIUM),
        GaugeRange(66f, 100f, AppColors.GaugeRangeHigh, Strings.RANGE_HIGH)
    )
)

@Preview(showBackground = true)
@Composable
private fun ChartDemoScreenPreview() {
    ChartsTheme {
        ChartDemoScreen()
    }
}

