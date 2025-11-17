package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.ui.components.area.variants.*

/**
 * Screen showcasing all Area Chart examples
 * Matches Recharts Area Chart examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaChartExamplesScreen(
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Area Chart Examples") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←", fontSize = 24.sp)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Simple Area Chart
            item {
                ChartSection(
                    title = "Simple Area Chart",
                    description = "Basic area chart with smooth curves"
                ) {
                    SimpleAreaChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Stacked Area Chart
            item {
                ChartSection(
                    title = "Stacked Area Chart",
                    description = "Multiple areas stacked on top of each other"
                ) {
                    StackedAreaChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Percent Area Chart
            item {
                ChartSection(
                    title = "Percent Area Chart (100% Stacked)",
                    description = "Areas normalized to show percentage distribution"
                ) {
                    PercentAreaChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Area Chart Connect Nulls
            item {
                ChartSection(
                    title = "Area Chart Connect Nulls",
                    description = "Comparison of connecting vs not connecting null values"
                ) {
                    AreaChartConnectNulls(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Tiny Area Chart
            item {
                ChartSection(
                    title = "Tiny Area Chart (Sparkline)",
                    description = "Minimal area chart without axes or grid"
                ) {
                    TinyAreaChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }

            // Area Chart Fill By Value
            item {
                ChartSection(
                    title = "Area Chart Fill By Value",
                    description = "Dynamic gradient fill based on positive/negative values"
                ) {
                    AreaChartFillByValue(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Cardinal Area Chart
            item {
                ChartSection(
                    title = "Cardinal Area Chart",
                    description = "Comparison of linear and smooth curved area interpolation"
                ) {
                    CardinalAreaChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Synchronized Area Charts
            item {
                ChartSection(
                    title = "Synchronized Area Charts",
                    description = "Multiple charts with synchronized interactions"
                ) {
                    SynchronizedAreaChart(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AreaChartExamplesScreenPreview() {
    MaterialTheme {
        AreaChartExamplesScreen()
    }
}

