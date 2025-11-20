# 🚀 Quick Start Guide - Compose Charts Library

Get up and running with Compose Charts in just a few minutes!

## Table of Contents

1. [Installation](#installation)
2. [Your First Chart](#your-first-chart)
3. [Common Patterns](#common-patterns)
4. [Next Steps](#next-steps)

---

## Installation

### Step 1: Add Repository

Add JitPack to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add Dependency

Add to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.majid2851:charts:1.0.0")
}
```

### Step 3: Sync Project

Click "Sync Now" in Android Studio.

---

## Your First Chart

### Simple Line Chart

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.domain.model.DataPoint

@Composable
fun MyFirstChart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Monthly Sales",
                style = MaterialTheme.typography.titleLarge
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LineChart(
                data = LineChartData(
                    lines = listOf(
                        LineDataSet(
                            label = "Sales",
                            dataPoints = listOf(
                                DataPoint(x = 0f, y = 100f),
                                DataPoint(x = 1f, y = 200f),
                                DataPoint(x = 2f, y = 150f),
                                DataPoint(x = 3f, y = 300f),
                                DataPoint(x = 4f, y = 250f),
                                DataPoint(x = 5f, y = 400f)
                            ),
                            lineColor = Color(0xFF8884d8),
                            isCurved = true,
                            fillArea = true
                        )
                    ),
                    xAxisLabels = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
    }
}
```

**Result:** A beautiful curved line chart with area fill showing sales data!

---

## Common Patterns

### 1. Bar Chart for Categories

```kotlin
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.domain.model.BarDataSet

@Composable
fun QuarterlyRevenueChart() {
    BarChart(
        data = BarChartData(
            categories = listOf("Q1", "Q2", "Q3", "Q4"),
            datasets = listOf(
                BarDataSet(
                    label = "2024",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 4000f),
                        DataPoint(x = 1f, y = 3000f),
                        DataPoint(x = 2f, y = 5000f),
                        DataPoint(x = 3f, y = 4500f)
                    ),
                    color = Color(0xFF8884d8)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### 2. Pie Chart for Distributions

```kotlin
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.domain.model.PieChartConfig

@Composable
fun MarketShareChart() {
    PieChart(
        data = PieChartData(
            slices = listOf(
                PieSlice(name = "Product A", value = 400f, color = Color(0xFF0088FE)),
                PieSlice(name = "Product B", value = 300f, color = Color(0xFF00C49F)),
                PieSlice(name = "Product C", value = 200f, color = Color(0xFFFFBB28)),
                PieSlice(name = "Product D", value = 100f, color = Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                showLabels = true,
                showPercentages = true,
                innerRadius = 0.4f // Makes it a donut chart
            )
        ),
        modifier = Modifier.size(300.dp)
    )
}
```

### 3. Multi-Series Comparison

```kotlin
@Composable
fun ComparisonChart() {
    LineChart(
        data = LineChartData(
            lines = listOf(
                LineDataSet(
                    label = "2023",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 100f),
                        DataPoint(x = 1f, y = 150f),
                        DataPoint(x = 2f, y = 120f),
                        DataPoint(x = 3f, y = 180f)
                    ),
                    lineColor = Color(0xFF8884d8),
                    isCurved = true
                ),
                LineDataSet(
                    label = "2024",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 120f),
                        DataPoint(x = 1f, y = 180f),
                        DataPoint(x = 2f, y = 160f),
                        DataPoint(x = 3f, y = 220f)
                    ),
                    lineColor = Color(0xFF82ca9d),
                    isCurved = true
                )
            ),
            xAxisLabels = listOf("Q1", "Q2", "Q3", "Q4")
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### 4. Responsive Chart

```kotlin
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer

@Composable
fun ResponsiveChart() {
    ResponsiveContainer(
        width = null, // Fill parent width
        height = 400.dp,
        minHeight = 200.dp
    ) { containerWidth, containerHeight ->
        LineChart(
            data = LineChartData(
                lines = listOf(
                    LineDataSet(
                        label = "Data",
                        dataPoints = listOf(
                            DataPoint(x = 0f, y = 100f),
                            DataPoint(x = 1f, y = 200f),
                            DataPoint(x = 2f, y = 150f)
                        ),
                        lineColor = Color.Blue
                    )
                )
            ),
            modifier = Modifier.size(containerWidth, containerHeight)
        )
    }
}
```

### 5. Interactive Chart with State

```kotlin
import androidx.compose.runtime.*

@Composable
fun InteractiveChart() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    
    Column {
        LineChart(
            data = LineChartData(
                lines = listOf(
                    LineDataSet(
                        label = "Sales",
                        dataPoints = listOf(
                            DataPoint(x = 0f, y = 100f),
                            DataPoint(x = 1f, y = 200f),
                            DataPoint(x = 2f, y = 150f)
                        ),
                        lineColor = Color.Blue
                    )
                ),
                showCrosshair = true
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        
        selectedPoint?.let { point ->
            Text(
                text = "Selected: (${point.x}, ${point.y})",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

### 6. Chart in LazyColumn

```kotlin
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun ChartList(charts: List<ChartData>) {
    LazyColumn {
        items(charts) { chartData ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                when (chartData) {
                    is LineChartData -> LineChart(
                        data = chartData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    is BarChartData -> BarChart(
                        data = chartData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    // ... other chart types
                }
            }
        }
    }
}
```

### 7. Chart with ViewModel

```kotlin
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChartViewModel : ViewModel() {
    private val _chartData = MutableStateFlow<LineChartData?>(null)
    val chartData: StateFlow<LineChartData?> = _chartData
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            // Simulate API call
            val data = LineChartData(
                lines = listOf(
                    LineDataSet(
                        label = "Sales",
                        dataPoints = listOf(
                            DataPoint(x = 0f, y = 100f),
                            DataPoint(x = 1f, y = 200f),
                            DataPoint(x = 2f, y = 150f)
                        ),
                        lineColor = Color.Blue
                    )
                )
            )
            _chartData.value = data
        }
    }
}

@Composable
fun ChartScreen(viewModel: ChartViewModel = viewModel()) {
    val chartData by viewModel.chartData.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        chartData?.let { data ->
            LineChart(
                data = data,
                modifier = Modifier.fillMaxSize()
            )
        } ?: CircularProgressIndicator()
    }
}
```

---

## Next Steps

### Explore More Chart Types

1. **Area Chart** - For showing trends with filled areas
2. **Scatter Chart** - For correlation analysis
3. **Radar Chart** - For multi-dimensional data
4. **Composed Chart** - Combine multiple chart types
5. **TreeMap** - For hierarchical data

### Advanced Features

- **Zoom & Pan**: Add `enableZoom = true` to LineChart
- **Stacked Charts**: Use `groupMode = BarGroupMode.STACKED` for BarChart
- **Custom Colors**: Define your own color palettes
- **Grid Styles**: Use `CartesianGridPresets` for different grid patterns
- **Reference Lines**: Add horizontal/vertical reference lines

### Check Out Examples

Explore the demo app (`:app` module) for:
- 50+ pre-built variants
- Interactive examples
- Customization demos
- Best practices

### Read the Docs

- [README.md](README.md) - Complete overview
- [API.md](API.md) - Full API reference
- [CHANGELOG.md](CHANGELOG.md) - Version history

---

## Quick Reference

### Common Imports

```kotlin
// Line Chart
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet

// Bar Chart
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.domain.model.BarDataSet

// Pie Chart
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.domain.model.PieChartConfig

// Data
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.domain.model.ChartConfig

// Responsive
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer
```

### Color Palette

```kotlin
val chartColors = listOf(
    Color(0xFF8884d8), // Blue
    Color(0xFF82ca9d), // Green
    Color(0xFFffc658), // Yellow
    Color(0xFFff7c7c), // Red
    Color(0xFF8dd1e1), // Cyan
    Color(0xFFd084d0), // Purple
    Color(0xFFa4de6c)  // Light Green
)
```

---

## Troubleshooting

### Chart Not Showing?

1. Make sure you've added the dependency and synced
2. Check that modifier has size: `.fillMaxWidth().height(300.dp)`
3. Verify data points are not empty
4. Check that colors have proper alpha values

### Build Errors?

1. Ensure Compose is enabled in `build.gradle.kts`
2. Check Kotlin version compatibility (1.9+)
3. Verify Compose BOM version matches

### Performance Issues?

1. Use `remember` for data that doesn't change
2. Limit data points for large datasets
3. Consider pagination for very large charts
4. Use `key` parameter in LazyColumn for chart items

---

## Get Help

- 🐛 [Report Issues](https://github.com/majid2851/charts/issues)
- 💬 [Discussions](https://github.com/majid2851/charts/discussions)
- 📧 Email: majid2851@example.com

---

Happy charting! 📊✨


