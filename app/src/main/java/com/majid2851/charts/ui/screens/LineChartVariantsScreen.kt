package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.ui.components.line.line_chart_types.*
import com.majid2851.charts.ui.theme.ChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Line Chart Variants") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            
            item {
                ChartSection("Tiny Line Chart", "Minimal line chart") {
                    TinyLineChart()
                }
            }
            
            item {
                ChartSection("Simple Line Chart", "Basic line chart with single series") {
                    SimpleLineChart()
                }
            }
            
            item {
                ChartSection("Multi-Series Line Chart", "Multiple lines on one chart") {
                    MultiSeriesLineChart()
                }
            }
            
            item {
                ChartSection("Curved Line Chart", "Smooth curved lines") {
                    CurvedLineChart()
                }
            }
            
            item {
                ChartSection("Dashed Line Chart", "Line with dashed pattern") {
                    DashedLineChart()
                }
            }
            
            item {
                ChartSection("Filled Area Line Chart", "Line with filled area underneath") {
                    FilledAreaLineChart()
                }
            }
            
            item {
                ChartSection("Customized Dot Line Chart", "Custom point markers") {
                    CustomizedDotLineChart()
                }
            }
            
            item {
                ChartSection("Line Chart with Reference Lines", "Horizontal/vertical reference lines") {
                    LineChartWithReferenceLines()
                }
            }
            
            item {
                ChartSection("Negative Values Line Chart", "Lines with positive and negative values") {
                    NegativeValuesLineChart()
                }
            }
            
            item {
                ChartSection("Connect Nulls Line Chart", "Handles null/missing data points") {
                    LineChartConnectNulls()
                }
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("About Line Chart Variants", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = "✓ 10+ variants\n✓ Curved and straight lines\n✓ Filled areas\n✓ Custom markers\n✓ Reference lines\n✓ Null handling",
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun ChartSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LineChartVariantsScreenPreview() {
    ChartsTheme { LineChartVariantsScreen() }
}

