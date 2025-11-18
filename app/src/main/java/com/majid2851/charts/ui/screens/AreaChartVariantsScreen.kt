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
import com.majid2851.charts.ui.components.area.variants.*
import com.majid2851.charts.ui.theme.ChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Area Chart Variants") })
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
                ChartSection("Tiny Area Chart", "Minimal area chart") {
                    TinyAreaChart()
                }
            }
            
            item {
                ChartSection("Simple Area Chart", "Basic area chart") {
                    SimpleAreaChart()
                }
            }
            
            item {
                ChartSection("Stacked Area Chart", "Multiple areas stacked") {
                    StackedAreaChart()
                }
            }
            
            item {
                ChartSection("Percent Area Chart", "Normalized to 100%") {
                    PercentAreaChart()
                }
            }
            
            item {
                ChartSection("Cardinal Area Chart", "Smooth cardinal spline") {
                    CardinalAreaChart()
                }
            }
            
            item {
                ChartSection("Area Chart Connect Nulls", "Handles missing data") {
                    AreaChartConnectNulls()
                }
            }
            
            item {
                ChartSection("Area Chart Fill By Value", "Dynamic fill colors") {
                    AreaChartFillByValue()
                }
            }
            
            item {
                ChartSection("Synchronized Area Chart", "Multiple synchronized charts") {
                    SynchronizedAreaChart()
                }
            }
            
            item {
                ChartSection("Responsive Area Chart", "Adapts to container") {
                    ResponsiveAreaChart(modifier = Modifier.fillMaxWidth())
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
                        Text("About Area Chart Variants", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = "✓ 9 variants\n✓ Stacked and percent modes\n✓ Smooth curves\n✓ Null handling\n✓ Dynamic fills",
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
private fun AreaChartVariantsScreenPreview() {
    ChartsTheme { AreaChartVariantsScreen() }
}

