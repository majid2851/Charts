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
import com.majid2851.charts.ui.components.composed.variants.*
import com.majid2851.charts.ui.theme.ChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposedChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Composed Chart Variants") })
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
                ChartSection("Line + Bar + Area Composed", "All chart types combined") {
                    LineBarAreaComposedChart()
                }
            }
            
            item {
                ChartSection("Composed Chart with Axis Labels", "Custom axis styling") {
                    ComposedChartWithAxisLabels()
                }
            }
            
            item {
                ChartSection("Same Data Composed Chart", "Multiple visualizations of same data") {
                    SameDataComposedChart()
                }
            }
            
            item {
                ChartSection("Vertical Composed Chart", "Horizontal orientation") {
                    VerticalComposedChart()
                }
            }
            
            item {
                ChartSection("Banded Chart", "Range bands with lines") {
                    BandedChart()
                }
            }
            
            item {
                ChartSection("Scatter and Line of Best Fit", "Regression line") {
                    ScatterAndLineOfBestFit()
                }
            }
            
            item {
                ChartSection("Target Price Chart", "Price targets with ranges") {
                    TargetPriceChart()
                }
            }
            
            item {
                ChartSection("Responsive Composed Chart", "Auto-sizing") {
                    ResponsiveComposedChart(modifier = Modifier.fillMaxWidth())
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
                        Text("About Composed Chart Variants", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = "✓ 8 variants\n✓ Mix Line, Bar, Area\n✓ Multiple axes\n✓ Scatter combinations\n✓ Financial charts",
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
private fun ComposedChartVariantsScreenPreview() {
    ChartsTheme { ComposedChartVariantsScreen() }
}

