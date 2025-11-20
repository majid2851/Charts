package com.majid2851.charts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.ui.components.pie.variants.DonutChart
import com.majid2851.charts.ui.components.pie.variants.TwoLevelPieChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieChartExamplesScreen() {
    val examples = remember {
        listOf(
            "Basic Pie Chart",
            "Donut Chart",
            "Two Level Pie Chart",
            "Straight Angle Pie Chart",
            "Pie Chart with Gap",
            "Pie Chart with Custom Labels",
            "Interactive Pie Chart"
        )
    }
    
    var selectedExample by remember { mutableStateOf(examples[0]) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pie Chart Examples") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Select Example",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    examples.take(3).forEach { example ->
                        FilterChip(
                            selected = selectedExample == example,
                            onClick = { selectedExample = example },
                            label = { Text(example, fontSize = 12.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    examples.drop(3).forEach { example ->
                        FilterChip(
                            selected = selectedExample == example,
                            onClick = { selectedExample = example },
                            label = { Text(example, fontSize = 11.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    when (selectedExample) {
                        "Basic Pie Chart" -> BasicPieChartExample()
                        "Donut Chart" -> DonutChartExample()
                        "Two Level Pie Chart" -> TwoLevelPieChartExample()
                        "Straight Angle Pie Chart" -> StraightAnglePieChartExample()
                        "Pie Chart with Gap" -> PieChartWithGapExample()
                        "Pie Chart with Custom Labels" -> PieChartWithCustomLabelsExample()
                        "Interactive Pie Chart" -> InteractivePieChartExample()
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun BasicPieChartExample() {
    PieChart(
        data = PieChartData(
            title = "Basic Pie Chart",
            slices = listOf(
                PieSlice("Group A", 400f, Color(0xFF0088FE)),
                PieSlice("Group B", 300f, Color(0xFF00C49F)),
                PieSlice("Group C", 300f, Color(0xFFFFBB28)),
                PieSlice("Group D", 200f, Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                showLabels = true,
                showPercentage = true,
                labelPosition = PieLabelPosition.INSIDE
            )
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun DonutChartExample() {
    DonutChart(
        data = PieChartData(
            title = "Donut Chart",
            slices = listOf(
                PieSlice("Product A", 400f, Color(0xFF0088FE)),
                PieSlice("Product B", 300f, Color(0xFF00C49F)),
                PieSlice("Product C", 300f, Color(0xFFFFBB28)),
                PieSlice("Product D", 200f, Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                showLabels = true,
                showPercentage = true
            )
        ),
        centerLabel = "Total\n1200",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun TwoLevelPieChartExample() {
    TwoLevelPieChart(
        data = TwoLevelPieChartData(
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
                PieSlice("B5", 50f, Color(0xFFffc658)),
                PieSlice("C1", 100f, Color(0xFFff8042)),
                PieSlice("C2", 200f, Color(0xFFffbb28)),
                PieSlice("D1", 150f, Color(0xFF00c49f)),
                PieSlice("D2", 50f, Color(0xFF0088fe))
            )
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun StraightAnglePieChartExample() {
    PieChart(
        data = PieChartData(
            title = "Straight Angle Pie Chart (Semi-Circle)",
            slices = listOf(
                PieSlice("Group A", 400f, Color(0xFF0088FE)),
                PieSlice("Group B", 300f, Color(0xFF00C49F)),
                PieSlice("Group C", 300f, Color(0xFFFFBB28)),
                PieSlice("Group D", 200f, Color(0xFFFF8042)),
                PieSlice("Group E", 278f, Color(0xFFAA00FF)),
                PieSlice("Group F", 189f, Color(0xFFFF00AA))
            ),
            config = PieChartConfig(
                startAngle = 180f,
                endAngle = 0f,
                showLabels = true,
                showPercentage = true,
                labelPosition = PieLabelPosition.OUTSIDE,
                showLabelLine = true
            )
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun PieChartWithGapExample() {
    PieChart(
        data = PieChartData(
            title = "Pie Chart with Gap & Rounded Corners",
            slices = listOf(
                PieSlice("Group A", 400f, Color(0xFF0088FE)),
                PieSlice("Group B", 300f, Color(0xFF00C49F)),
                PieSlice("Group C", 300f, Color(0xFFFFBB28)),
                PieSlice("Group D", 200f, Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                innerRadius = 0.7f,
                outerRadius = 0.9f,
                paddingAngle = 5f,
                cornerRadius = 10f,
                showLabels = false,
                showLegend = true
            )
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun PieChartWithCustomLabelsExample() {
    PieChart(
        data = PieChartData(
            title = "Custom Labels Outside",
            slices = listOf(
                PieSlice("Category A", 400f, Color(0xFF0088FE)),
                PieSlice("Category B", 300f, Color(0xFF00C49F)),
                PieSlice("Category C", 300f, Color(0xFFFFBB28)),
                PieSlice("Category D", 200f, Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                showLabels = true,
                showPercentage = false,
                labelPosition = PieLabelPosition.OUTSIDE,
                showLabelLine = true,
                labelLineLength = 30f,
                labelColor = Color.Black,
                labelTextSize = 14f
            )
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun InteractivePieChartExample() {
    var selectedSlice by remember { mutableStateOf<String?>(null) }
    
    Column(modifier = Modifier.fillMaxSize()) {
        PieChart(
            data = PieChartData(
                title = "Interactive Pie Chart (Tap slices)",
                slices = listOf(
                    PieSlice("Sales", 400f, Color(0xFF0088FE)),
                    PieSlice("Marketing", 300f, Color(0xFF00C49F)),
                    PieSlice("Development", 300f, Color(0xFFFFBB28)),
                    PieSlice("Support", 200f, Color(0xFFFF8042))
                ),
                config = PieChartConfig(
                    isInteractive = true,
                    activeSliceOffset = 15f,
                    showLabels = true,
                    showPercentage = true
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onSliceClick = { slice, index ->
                selectedSlice = "${slice.label}: ${slice.value}"
            }
        )
        
        if (selectedSlice != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            ) {
                Text(
                    text = "Selected: $selectedSlice",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PieChartExamplesScreenPreview() {
    MaterialTheme {
        PieChartExamplesScreen()
    }
}






