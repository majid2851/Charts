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
import com.majid2851.charts.ui.components.bar.variants.*
import com.majid2851.charts.ui.theme.ChartsTheme

/**
 * Screen demonstrating all BarChart variants
 * Matches Recharts bar chart examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bar Chart Variants") }
            )
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
            
            // Tiny Bar Chart
            item {
                ChartSection(
                    title = "Tiny Bar Chart",
                    description = "Minimal chart with no decorations (300x100dp)"
                ) {
                    TinyBarChart()
                }
            }
            
            // Simple Bar Chart
            item {
                ChartSection(
                    title = "Simple Bar Chart",
                    description = "Standard chart with multiple series, grid, and legend"
                ) {
                    SimpleBarChart(modifier = Modifier.fillMaxWidth().height(300.dp))
                }
            }
            
            // Stacked Bar Chart
            item {
                ChartSection(
                    title = "Stacked Bar Chart",
                    description = "Bars stacked on top of each other"
                ) {
                    StackedBarChart(modifier = Modifier)
                }
            }
            
            // Mix Bar Chart
            item {
                ChartSection(
                    title = "Mix Bar Chart",
                    description = "Combination of stacked and grouped bars"
                ) {
                    MixBarChart(modifier = Modifier)
                }
            }
            
            // Positive and Negative Bar Chart
            item {
                ChartSection(
                    title = "Positive and Negative Bar Chart",
                    description = "Shows bars with both positive and negative values"
                ) {
                    PositiveAndNegativeBarChart(modifier = Modifier.fillMaxWidth().height(300.dp))
                }
            }
            
            // Biaxial Bar Chart
            item {
                ChartSection(
                    title = "Biaxial Bar Chart",
                    description = "Two Y-axes with different scales"
                ) {
                    BiaxialBarChart(modifier = Modifier.fillMaxWidth().height(300.dp))
                }
            }
            
            // Info Section
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
                        Text(
                            text = "About Bar Chart Variants",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "✓ 6 core variants matching Recharts\n" +
                                   "✓ Stacked and grouped modes\n" +
                                   "✓ Positive and negative values\n" +
                                   "✓ Multiple data series\n" +
                                   "✓ Customizable styling",
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
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartVariantsScreenPreview() {
    ChartsTheme {
        BarChartVariantsScreen()
    }
}

