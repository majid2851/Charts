package com.majid2851.charts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.line.line_chart.LineChart

/**
 * Example demonstrating multi-point highlight feature
 * When you tap/drag, ALL points at the same X position are highlighted
 */
@Composable
fun MultiPointHighlightExample() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    var selectedLineIndex by remember { mutableStateOf<Int?>(null) }
    val chartData = remember { createMultiLineData() }
    
    // Collect all values at the selected X position
    val allValuesAtX = remember(selectedPoint) {
        selectedPoint?.let { point ->
            chartData.lines.mapIndexed { index, lineDataSet ->
                val dataPoint = lineDataSet.dataPoints.find { it?.x == point.x }
                Triple(lineDataSet.label, lineDataSet.lineColor, dataPoint?.y)
            }
        } ?: emptyList()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Text(
            text = "Multi-Point Highlight",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "👆 Tap or drag - all points at same position are highlighted!",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        
        // Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            LineChart(
                data = chartData,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onPointSelected = { lineIndex, pointIndex, point ->
                    selectedPoint = point
                    selectedLineIndex = lineIndex
                }
            )
        }
        
        // Info card showing all values
        if (selectedPoint != null && allValuesAtX.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "📊 ${selectedPoint?.label}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "All Points Highlighted ✨",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    // Show all values at this X position
                    allValuesAtX.forEach { (label, color, value) ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = color.copy(alpha = 0.1f)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Color indicator
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .background(color, shape = RoundedCornerShape(6.dp))
                                    )
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                
                                Text(
                                    text = value?.let { "$${String.format("%.0f", it)}" } ?: "N/A",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = color
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "💡",
                        fontSize = 24.sp
                    )
                    Column {
                        Text(
                            text = "Tap or drag on the chart",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "All points at the same position will be highlighted",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        
        // Feature explanation
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "✨ How It Works",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                FeatureExplanation("1️⃣", "Tap or drag anywhere on the chart")
                FeatureExplanation("2️⃣", "Finds nearest X position (month/date)")
                FeatureExplanation("3️⃣", "Highlights ALL points at that X position")
                FeatureExplanation("4️⃣", "Shows vertical line and values")
                FeatureExplanation("5️⃣", "Compare multiple metrics at once!")
            }
        }
    }
}

@Composable
private fun FeatureExplanation(icon: String, text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(text = icon, fontSize = 16.sp)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

private fun createMultiLineData(): LineChartData {
    return LineChartData(
        title = "Revenue vs Profit vs Expenses",
        lines = listOf(
            LineDataSet(
                label = "Revenue",
                dataPoints = listOf(
                    DataPoint(0f, 5000f, "Jan"),
                    DataPoint(1f, 5500f, "Feb"),
                    DataPoint(2f, 6200f, "Mar"),
                    DataPoint(3f, 5800f, "Apr"),
                    DataPoint(4f, 6500f, "May"),
                    DataPoint(5f, 7000f, "Jun"),
                    DataPoint(6f, 7500f, "Jul"),
                    DataPoint(7f, 8000f, "Aug"),
                    DataPoint(8f, 7800f, "Sep"),
                    DataPoint(9f, 8200f, "Oct"),
                    DataPoint(10f, 8500f, "Nov"),
                    DataPoint(11f, 9000f, "Dec")
                ),
                lineColor = Color(0xFF2196F3),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 7f,
                isCurved = true
            ),
            LineDataSet(
                label = "Profit",
                dataPoints = listOf(
                    DataPoint(0f, 1500f, "Jan"),
                    DataPoint(1f, 1650f, "Feb"),
                    DataPoint(2f, 1860f, "Mar"),
                    DataPoint(3f, 1740f, "Apr"),
                    DataPoint(4f, 1950f, "May"),
                    DataPoint(5f, 2100f, "Jun"),
                    DataPoint(6f, 2250f, "Jul"),
                    DataPoint(7f, 2400f, "Aug"),
                    DataPoint(8f, 2340f, "Sep"),
                    DataPoint(9f, 2460f, "Oct"),
                    DataPoint(10f, 2550f, "Nov"),
                    DataPoint(11f, 2700f, "Dec")
                ),
                lineColor = Color(0xFF4CAF50),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 7f,
                isCurved = true
            ),
            LineDataSet(
                label = "Expenses",
                dataPoints = listOf(
                    DataPoint(0f, 3500f, "Jan"),
                    DataPoint(1f, 3850f, "Feb"),
                    DataPoint(2f, 4340f, "Mar"),
                    DataPoint(3f, 4060f, "Apr"),
                    DataPoint(4f, 4550f, "May"),
                    DataPoint(5f, 4900f, "Jun"),
                    DataPoint(6f, 5250f, "Jul"),
                    DataPoint(7f, 5600f, "Aug"),
                    DataPoint(8f, 5460f, "Sep"),
                    DataPoint(9f, 5740f, "Oct"),
                    DataPoint(10f, 5950f, "Nov"),
                    DataPoint(11f, 6300f, "Dec")
                ),
                lineColor = Color(0xFFFF5722),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 7f,
                isCurved = true
            )
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            backgroundColor = Color.White,
            isInteractive = true
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 28f,
            axisColor = Color.Black
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 28f,
            labelCount = 5,
            axisColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MultiPointHighlightExamplePreview() {
    MaterialTheme {
        MultiPointHighlightExample()
    }
}

