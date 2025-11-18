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
import com.majid2851.charts.ui.components.scatter.variants.*
import com.majid2851.charts.ui.theme.ChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScatterChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Scatter Chart Variants") })
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
                ChartSection("Simple Scatter Chart", "Basic scatter plot") {
                    SimpleScatterChart()
                }
            }
            
            item {
                ChartSection("Bubble Chart", "Scatter with varying bubble sizes") {
                    BubbleChart()
                }
            }
            
            item {
                ChartSection("Joint Line Scatter Chart", "Scatter points connected by lines") {
                    JointLineScatterChart()
                }
            }
            
            item {
                ChartSection("Scatter Chart with Cells", "Grid-based scatter") {
                    ScatterChartWithCells()
                }
            }
            
            item {
                ChartSection("Scatter Chart with Labels", "Points with text labels") {
                    ScatterChartWithLabels()
                }
            }
            
            item {
                ChartSection("3D Scatter Chart", "Three-dimensional data") {
                    ThreeDimScatterChart()
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
                        Text("About Scatter Chart Variants", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = "✓ 6 variants\n✓ Bubble charts\n✓ Connected scatter\n✓ Custom shapes\n✓ Labels and cells",
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
private fun ScatterChartVariantsScreenPreview() {
    ChartsTheme { ScatterChartVariantsScreen() }
}

