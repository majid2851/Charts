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
 * Example demonstrating the drag-to-show vertical line feature
 * Users can drag anywhere on the chart to show the vertical line at the nearest data point
 */
@Composable
fun DragToShowExample() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    var selectedLineIndex by remember { mutableStateOf<Int?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Text(
            text = "Drag to Show Vertical Line",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "👆 Drag anywhere on the chart to see values",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        
        // Chart with drag-to-show
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            LineChart(
                data = createDragToShowData(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onPointSelected = { lineIndex, pointIndex, point ->
                    selectedPoint = point
                    selectedLineIndex = lineIndex
                }
            )
        }
        
        // Info card showing selected value
        if (selectedPoint != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2196F3).copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "📊 Selected Data Point",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Period",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            Text(
                                text = selectedPoint?.label ?: "N/A",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "Value",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            Text(
                                text = "$${String.format("%.0f", selectedPoint?.y ?: 0f)}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = when(selectedLineIndex) {
                                    0 -> Color(0xFF8884d8)
                                    1 -> Color(0xFF82ca9d)
                                    else -> Color.Black
                                }
                            )
                        }
                    }
                    
                    Text(
                        text = when(selectedLineIndex) {
                            0 -> "💼 Revenue"
                            1 -> "📈 Profit"
                            else -> "Unknown"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFEB3B).copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "💡",
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Drag your finger across the chart to see data values",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        
        // Feature highlights
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
                    text = "✨ Features",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                FeatureItem("✅", "Drag anywhere - no need to tap exactly on points")
                FeatureItem("✅", "Automatic snap to nearest data point")
                FeatureItem("✅", "Smooth tracking as you move")
                FeatureItem("✅", "Works with multiple lines")
                FeatureItem("✅", "Shows vertical line and highlights points")
            }
        }
    }
}

@Composable
private fun FeatureItem(icon: String, text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 16.sp)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

private fun createDragToShowData(): LineChartData {
    return LineChartData(
        title = "Monthly Financial Report",
        lines = listOf(
            LineDataSet(
                label = "Revenue",
                dataPoints = listOf(
                    DataPoint(0f, 2400f, "Jan"),
                    DataPoint(1f, 1398f, "Feb"),
                    DataPoint(2f, 9800f, "Mar"),
                    DataPoint(3f, 3908f, "Apr"),
                    DataPoint(4f, 4800f, "May"),
                    DataPoint(5f, 3800f, "Jun"),
                    DataPoint(6f, 4300f, "Jul"),
                    DataPoint(7f, 5600f, "Aug"),
                    DataPoint(8f, 6200f, "Sep"),
                    DataPoint(9f, 4900f, "Oct"),
                    DataPoint(10f, 7100f, "Nov"),
                    DataPoint(11f, 8300f, "Dec")
                ),
                lineColor = Color(0xFF8884d8),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 6f,
                isCurved = true
            ),
            LineDataSet(
                label = "Profit",
                dataPoints = listOf(
                    DataPoint(0f, 1200f, "Jan"),
                    DataPoint(1f, 698f, "Feb"),
                    DataPoint(2f, 4900f, "Mar"),
                    DataPoint(3f, 1954f, "Apr"),
                    DataPoint(4f, 2400f, "May"),
                    DataPoint(5f, 1900f, "Jun"),
                    DataPoint(6f, 2150f, "Jul"),
                    DataPoint(7f, 2800f, "Aug"),
                    DataPoint(8f, 3100f, "Sep"),
                    DataPoint(9f, 2450f, "Oct"),
                    DataPoint(10f, 3550f, "Nov"),
                    DataPoint(11f, 4150f, "Dec")
                ),
                lineColor = Color(0xFF82ca9d),
                lineWidth = 3f,
                showPoints = true,
                pointRadius = 6f,
                isCurved = true
            )
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            backgroundColor = Color.White,
            isInteractive = true  // ✅ Enables drag-to-show feature
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
private fun DragToShowExamplePreview() {
    MaterialTheme {
        DragToShowExample()
    }
}

