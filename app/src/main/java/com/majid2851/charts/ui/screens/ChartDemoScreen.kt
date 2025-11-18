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
import com.majid2851.charts.ui.components.composed.ComposedChart
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.ui.components.radar.RadarChart
import com.majid2851.charts.ui.components.radialbar.RadialBarChart
import com.majid2851.charts.ui.components.scatter.ScatterChart
import com.majid2851.charts.ui.components.treemap.TreeMapChart
import com.majid2851.charts.ui.screens.ResponsiveChartsScreen
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
            Strings.COMPOSED_CHART,
            Strings.RADIAL_BAR_CHART,
            Strings.TREEMAP_CHART,
            Strings.RESPONSIVE_CHARTS
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
                            Strings.COMPOSED_CHART -> ComposedChart(
                                data = getSampleComposedChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.RADIAL_BAR_CHART -> RadialBarChart(
                                data = getSampleRadialBarChartData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.TREEMAP_CHART -> TreeMapChart(
                                data = getSampleTreeMapData(),
                                modifier = Modifier.fillMaxSize()
                            )
                            Strings.RESPONSIVE_CHARTS -> ResponsiveChartsScreen(
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
    domain = Pair(0f, 100f)
)

private fun getSampleComposedChartData() = ComposedChartData(
    title = Strings.COMPOSED_CHART_TITLE,
    categories = listOf(
        Strings.PAGE_A, Strings.PAGE_B, Strings.PAGE_C,
        Strings.PAGE_D, Strings.PAGE_E, Strings.PAGE_F
    ),
    areaDataSets = listOf(
        AreaDataSet(
            label = "amt",
            dataPoints = listOf(
                DataPoint(0f, 1400f),
                DataPoint(1f, 1506f),
                DataPoint(2f, 989f),
                DataPoint(3f, 1228f),
                DataPoint(4f, 1100f),
                DataPoint(5f, 1700f)
            ),
            lineColor = androidx.compose.ui.graphics.Color(0xFF8884d8),
            fillColor = androidx.compose.ui.graphics.Color(0xFF8884d8),
            fillOpacity = 0.3f
        )
    ),
    barDataSets = listOf(
        ComposedBarDataSet(
            dataKey = Strings.LABEL_PV,
            label = Strings.LABEL_PV,
            dataPoints = listOf(
                DataPoint(0f, 800f),
                DataPoint(1f, 967f),
                DataPoint(2f, 1098f),
                DataPoint(3f, 1200f),
                DataPoint(4f, 1108f),
                DataPoint(5f, 680f)
            ),
            color = androidx.compose.ui.graphics.Color(0xFF413ea0),
            barSize = 20f
        )
    ),
    lineDataSets = listOf(
        LineDataSet(
            label = Strings.LABEL_UV,
            dataPoints = listOf(
                DataPoint(0f, 590f),
                DataPoint(1f, 868f),
                DataPoint(2f, 1397f),
                DataPoint(3f, 1480f),
                DataPoint(4f, 1520f),
                DataPoint(5f, 1400f)
            ),
            lineColor = androidx.compose.ui.graphics.Color(0xFFff7300),
            lineWidth = 2f
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true
    )
)

private fun getSampleRadialBarChartData() = RadialBarChartData(
    title = Strings.RADIAL_BAR_CHART_TITLE,
    bars = listOf(
        RadialBarEntry(
            name = "18-24",
            value = 31.47f,
            fill = androidx.compose.ui.graphics.Color(0xFF8884d8)
        ),
        RadialBarEntry(
            name = "25-29",
            value = 26.69f,
            fill = androidx.compose.ui.graphics.Color(0xFF83a6ed)
        ),
        RadialBarEntry(
            name = "30-34",
            value = 15.69f,
            fill = androidx.compose.ui.graphics.Color(0xFF8dd1e1)
        ),
        RadialBarEntry(
            name = "35-39",
            value = 8.22f,
            fill = androidx.compose.ui.graphics.Color(0xFF82ca9d)
        ),
        RadialBarEntry(
            name = "40-49",
            value = 8.63f,
            fill = androidx.compose.ui.graphics.Color(0xFFa4de6c)
        ),
        RadialBarEntry(
            name = "50+",
            value = 2.63f,
            fill = androidx.compose.ui.graphics.Color(0xFFd0ed57)
        ),
        RadialBarEntry(
            name = "unknown",
            value = 6.67f,
            fill = androidx.compose.ui.graphics.Color(0xFFffc658)
        )
    ),
    config = ChartConfig(
        showLegend = true,
        showGrid = false,
        showAxis = false
    ),
    centerX = 0.3f,
    barSize = 14f
)

private fun getSampleTreeMapData() = TreeMapData(
    title = Strings.TREEMAP_CHART_TITLE,
    nodes = listOf(
        TreeMapNode(
            name = "axis",
            children = listOf(
                TreeMapNode("Axes", size = 1302f),
                TreeMapNode("Axis", size = 24593f),
                TreeMapNode("AxisGridLine", size = 652f),
                TreeMapNode("AxisLabel", size = 636f),
                TreeMapNode("CartesianAxes", size = 6703f)
            )
        ),
        TreeMapNode(
            name = "controls",
            children = listOf(
                TreeMapNode("AnchorControl", size = 2138f),
                TreeMapNode("ClickControl", size = 3824f),
                TreeMapNode("Control", size = 1353f),
                TreeMapNode("ControlList", size = 4665f),
                TreeMapNode("DragControl", size = 2649f)
            )
        ),
        TreeMapNode(
            name = "data",
            children = listOf(
                TreeMapNode("Data", size = 20544f),
                TreeMapNode("DataList", size = 19788f),
                TreeMapNode("DataSprite", size = 10349f),
                TreeMapNode("EdgeSprite", size = 3301f)
            )
        ),
        TreeMapNode(
            name = "events",
            children = listOf(
                TreeMapNode("DataEvent", size = 7313f),
                TreeMapNode("SelectionEvent", size = 6880f),
                TreeMapNode("TooltipEvent", size = 3701f)
            )
        ),
        TreeMapNode(
            name = "legend",
            children = listOf(
                TreeMapNode("Legend", size = 20859f),
                TreeMapNode("LegendItem", size = 4614f),
                TreeMapNode("LegendRange", size = 10530f)
            )
        )
    ),
    strokeColor = androidx.compose.ui.graphics.Color.White,
    defaultFill = androidx.compose.ui.graphics.Color(0xFF8884d8),
    aspectRatio = 4f / 3f
)

@Preview(showBackground = true)
@Composable
private fun ChartDemoScreenPreview() {
    ChartsTheme {
        ChartDemoScreen()
    }
}

