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
import com.majid2851.charts.ui.components.pie.variants.*
import com.majid2851.charts.ui.theme.ChartsTheme
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.PieChartConfig
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.domain.model.TwoLevelPieChartData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieChartVariantsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pie Chart Variants") })
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
                ChartSection("Donut Chart", "Pie chart with center hole") {
                    DonutChart(
                        data = getSampleDonutData(),
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }
            }
            
            item {
                ChartSection("Two-Level Pie Chart", "Nested pie charts") {
                    TwoLevelPieChart(
                        data = getSampleTwoLevelPieData(),
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }
            }
            
            item {
                ChartSection("Responsive Pie Chart", "Adapts to container size") {
                    ResponsivePieChart(modifier = Modifier.fillMaxWidth())
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
                        Text("About Pie Chart Variants", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = "✓ Donut charts\n✓ Nested/Two-level pies\n✓ Responsive sizing\n✓ Custom colors\n✓ Labels and legends",
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

private fun getSampleDonutData() = PieChartData(
    title = "Donut Chart",
    slices = listOf(
        PieSlice("Category A", 400f, Color(0xFF0088FE)),
        PieSlice("Category B", 300f, Color(0xFF00C49F)),
        PieSlice("Category C", 300f, Color(0xFFFFBB28)),
        PieSlice("Category D", 200f, Color(0xFFFF8042))
    ),
    config = PieChartConfig(showLabels = true, showLegend = true)
)

private fun getSampleTwoLevelPieData() = TwoLevelPieChartData(
    title = "Two Level Pie Chart",
    innerSlices = listOf(
        PieSlice("Group A", 400f, Color(0xFF8884d8)),
        PieSlice("Group B", 300f, Color(0xFF83a6ed)),
        PieSlice("Group C", 300f, Color(0xFF8dd1e1)),
        PieSlice("Group D", 200f, Color(0xFF82ca9d))
    ),
    outerSlices = listOf(
        PieSlice("A1", 100f, Color(0xFF8884d8)),
        PieSlice("A2", 300f, Color(0xFF83a6ed)),
        PieSlice("B1", 100f, Color(0xFF8dd1e1)),
        PieSlice("B2", 80f, Color(0xFF82ca9d)),
        PieSlice("B3", 40f, Color(0xFFa4de6c)),
        PieSlice("B4", 30f, Color(0xFFd0ed57)),
        PieSlice("B5", 50f, Color(0xFFffc658))
    )
)

@Preview(showBackground = true)
@Composable
private fun PieChartVariantsScreenPreview() {
    ChartsTheme { PieChartVariantsScreen() }
}

