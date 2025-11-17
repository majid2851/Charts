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
import com.majid2851.charts.ui.components.radar.variants.*

/**
 * Screen showcasing all Radar Chart examples
 * Matches Recharts Radar Chart examples
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadarChartExamplesScreen(
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Radar Chart Examples") },
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
            // Simple Radar Chart
            item {
                ChartSection(
                    title = "Simple Radar Chart",
                    description = "Basic radar chart with single data series and polar grid"
                ) {
                    SimpleRadarChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                }
            }

            // Specified Domain Radar Chart
            item {
                ChartSection(
                    title = "Specified Domain Radar Chart",
                    description = "Multiple series with custom domain [0-150] and angled radius axis"
                ) {
                    SpecifiedDomainRadarChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
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
private fun RadarChartExamplesScreenPreview() {
    MaterialTheme {
        RadarChartExamplesScreen()
    }
}

