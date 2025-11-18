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
import com.majid2851.charts.ui.components.area.variants.ResponsiveAreaChart
import com.majid2851.charts.ui.components.composed.variants.ResponsiveComposedChart
import com.majid2851.charts.ui.components.pie.variants.ResponsivePieChart
import com.majid2851.charts.ui.theme.ChartsTheme
import com.majid2851.charts.ui.theme.Strings

/**
 * Screen demonstrating ResponsiveContainer with different chart types
 * Matches Recharts responsive examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveChartsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Strings.RESPONSIVE_CHARTS_TITLE) }
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
            
            // Responsive Area Chart
            item {
                ChartSection(
                    title = "Responsive Area Chart",
                    description = "Container with fixed height (300dp), width fills parent"
                ) {
                    ResponsiveAreaChart(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // Responsive Composed Chart
            item {
                ChartSection(
                    title = "Responsive Composed Chart",
                    description = "Bar + Line + Area in responsive container"
                ) {
                    ResponsiveComposedChart(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // Responsive Pie Chart
            item {
                ChartSection(
                    title = "Responsive Pie Chart",
                    description = "Pie chart that adapts to container size"
                ) {
                    ResponsivePieChart(
                        modifier = Modifier.fillMaxWidth()
                    )
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
                            text = "About ResponsiveContainer",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "• Automatically adapts to parent size\n" +
                                   "• Maintains aspect ratios when specified\n" +
                                   "• Supports min/max constraints\n" +
                                   "• Works with all chart types\n" +
                                   "• Matches Recharts API",
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
private fun ResponsiveChartsScreenPreview() {
    ChartsTheme {
        ResponsiveChartsScreen()
    }
}

