package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.majid2851.charts.ui.components.line.line_chart_types.CurvedLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.CustomizedDotLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.DashedLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.FilledAreaLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.LineChartConnectNulls
import com.majid2851.charts.ui.components.line.line_chart_types.LineChartWithReferenceLines
import com.majid2851.charts.ui.components.line.line_chart_types.MultiSeriesLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.NegativeValuesLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.SimpleLineChart
import com.majid2851.charts.ui.components.line.line_chart_types.TinyLineChart
import com.majid2851.charts.ui.theme.ChartsTheme
import com.majid2851.charts.ui.theme.Dimens

/**
 * Screen showcasing all LineChart variants inspired by Recharts
 * Reference: https://recharts.github.io/en-US/examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineChartExamplesScreen(modifier: Modifier = Modifier) {
    val chartTypes = remember {
        listOf(
            "Simple Line Chart",
            "Tiny Line Chart",
            "Dashed Line Chart",
            "With Reference Lines",
            "Connect Nulls OFF",
            "Connect Nulls ON",
            "Customized Dots",
            "Curved Line",
            "Filled Area",
            "Multi Series",
            "Negative Values"
        )
    }

    var selectedChart by remember { mutableStateOf(chartTypes[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LineChart Examples") }
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
                        text = "Select Chart Type",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = Dimens.paddingSmall)
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.paddingSmall)
                    ) {
                        items(chartTypes) { chartType ->
                            FilterChip(
                                selected = selectedChart == chartType,
                                onClick = { selectedChart = chartType },
                                label = { Text(chartType) },
                                modifier = Modifier.padding(vertical = Dimens.chipVerticalPadding)
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
                        Box(modifier = Modifier.fillMaxSize().padding(Dimens.paddingSmall)) {
                            when (selectedChart) {
                                "Simple Line Chart" -> SimpleLineChart()
                                "Tiny Line Chart" -> TinyLineChart()
                                "Dashed Line Chart" -> DashedLineChart()
                                "With Reference Lines" -> LineChartWithReferenceLines()
                                "Connect Nulls OFF" -> LineChartConnectNulls(false)
                                "Connect Nulls ON" -> LineChartConnectNulls(true)
                                "Customized Dots" -> CustomizedDotLineChart()
                                "Curved Line" -> CurvedLineChart()
                                "Filled Area" -> FilledAreaLineChart()
                                "Multi Series" -> MultiSeriesLineChart()
                                "Negative Values" -> NegativeValuesLineChart()
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(Dimens.spacerHeight))
                }

                // Description section
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.paddingDefault),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(Dimens.paddingDefault)
                        ) {
                            Text(
                                text = "About ${selectedChart}",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Spacer(modifier = Modifier.height(Dimens.paddingSmall))
                            Text(
                                text = getChartDescription(selectedChart),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getChartDescription(chartType: String): String {
    return when (chartType) {
        "Simple Line Chart" -> "A basic line chart showing a single data series with default settings. Perfect for displaying trends over time."
        "Tiny Line Chart" -> "A minimal version with no axes, grid, or legend. Ideal for sparklines or dashboard widgets where space is limited."
        "Dashed Line Chart" -> "Shows lines with different stroke patterns. Useful for distinguishing between actual and projected data, or target vs actual values."
        "With Reference Lines" -> "Includes horizontal or vertical reference lines to mark important thresholds like targets, averages, or limits."
        "Connect Nulls OFF" -> "When data contains null values (gaps), the line breaks at those points. Useful for showing data availability."
        "Connect Nulls ON" -> "Null values are interpolated, creating continuous lines even when data is missing. Good for showing trends despite missing data."
        "Customized Dots" -> "Demonstrates different point shapes (circles, squares, diamonds, etc.) to distinguish between multiple data series."
        "Curved Line" -> "Uses smooth bezier curves instead of straight line segments. Creates a more elegant, flowing appearance."
        "Filled Area" -> "The area under the line is filled with color. Emphasizes the magnitude of values and is visually striking."
        "Multi Series" -> "Displays multiple data series on the same chart for comparison. Each series has its own color and legend entry."
        "Negative Values" -> "Handles both positive and negative values with a zero baseline reference line. Perfect for profit/loss or variance charts."
        else -> "Select a chart type to see its description."
    }
}

@Preview(showBackground = true)
@Composable
private fun LineChartExamplesScreenPreview() {
    ChartsTheme {
        LineChartExamplesScreen()
    }
}










