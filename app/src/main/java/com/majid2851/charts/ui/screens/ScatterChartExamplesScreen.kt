package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.ui.components.scatter.variants.*

/**
 * Screen showcasing all Scatter Chart examples
 * Matches Recharts Scatter Chart examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScatterChartExamplesScreen(
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scatter Chart Examples") },
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
            // Simple Scatter Chart
            item {
                ChartSection(
                    title = "Simple Scatter Chart",
                    description = "Basic scatter plot with single data series"
                ) {
                    SimpleScatterChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Three-Dimensional Scatter Chart
            item {
                ChartSection(
                    title = "Three-Dimensional Scatter Chart",
                    description = "Multiple series with Z-axis controlling point size (bubble effect)"
                ) {
                    ThreeDimScatterChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Joint Line Scatter Chart
            item {
                ChartSection(
                    title = "Joint Line Scatter Chart",
                    description = "Scatter points connected with lines"
                ) {
                    JointLineScatterChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Bubble Chart
            item {
                ChartSection(
                    title = "Bubble Chart",
                    description = "Scatter chart with varying bubble sizes based on Z-axis values"
                ) {
                    BubbleChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Scatter Chart With Labels
            item {
                ChartSection(
                    title = "Scatter Chart With Labels",
                    description = "Display data labels on each scatter point"
                ) {
                    ScatterChartWithLabels(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }
            }

            // Scatter Chart With Cells
            item {
                ChartSection(
                    title = "Scatter Chart With Cells (Individual Colors)",
                    description = "Each point with a unique color"
                ) {
                    ScatterChartWithCells(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
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
private fun ScatterChartExamplesScreenPreview() {
    MaterialTheme {
        ScatterChartExamplesScreen()
    }
}

