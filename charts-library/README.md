# 📊 Compose Charts Library

A comprehensive, feature-rich charting library for Jetpack Compose with 9+ chart types and 50+ pre-built variants.

[![](https://img.shields.io/badge/Compose-1.5.1-blue.svg)](https://developer.android.com/jetpack/compose)
[![](https://img.shields.io/badge/Kotlin-1.9+-purple.svg)](https://kotlinlang.org/)
[![](https://img.shields.io/badge/minSdk-24-green.svg)](https://developer.android.com/studio/releases/platforms)
[![](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](LICENSE)

## ✨ Features

- 📈 **9+ Chart Types**: Line, Bar, Pie, Area, Scatter, Radar, Composed, Radial Bar, TreeMap
- 🎨 **50+ Variants**: Pre-configured examples for common use cases
- 🎯 **Material 3 Support**: Modern design following Material Design guidelines
- 🔧 **Highly Customizable**: Fine-grained control over every aspect
- 📱 **Responsive Design**: Automatic adaptation to screen sizes
- ⚡ **Performance Optimized**: Efficient Canvas-based rendering
- 🎭 **Interactive Features**: Zoom, pan, tap interactions, tooltips
- 📊 **Complex Compositions**: Combine multiple chart types in one view
- 🌈 **Rich Styling**: Colors, gradients, patterns, and more
- 📐 **Flexible Layouts**: Horizontal, vertical, stacked, grouped

## 📦 Installation

### Option 1: JitPack (Recommended)

Add JitPack repository to your root `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.majid2851:charts:1.0.0")
}
```

### Option 2: Local Maven (For Testing)

```kotlin
// In library module
./gradlew publishToMavenLocal

// In your project
repositories {
    mavenLocal()
}
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

### Option 3: GitHub Packages

Add to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/majid2851/charts")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

## 🚀 Quick Start

### Line Chart

```kotlin
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.domain.model.DataPoint

@Composable
fun MyLineChart() {
    LineChart(
        data = LineChartData(
            lines = listOf(
                LineDataSet(
                    label = "Sales",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 100f),
                        DataPoint(x = 1f, y = 200f),
                        DataPoint(x = 2f, y = 150f),
                        DataPoint(x = 3f, y = 300f)
                    ),
                    lineColor = Color(0xFF8884d8),
                    isCurved = true
                )
            ),
            xAxisLabels = listOf("Jan", "Feb", "Mar", "Apr")
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Bar Chart

```kotlin
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.domain.model.BarDataSet

@Composable
fun MyBarChart() {
    BarChart(
        data = BarChartData(
            categories = listOf("Q1", "Q2", "Q3", "Q4"),
            datasets = listOf(
                BarDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 4000f),
                        DataPoint(x = 1f, y = 3000f),
                        DataPoint(x = 2f, y = 2000f),
                        DataPoint(x = 3f, y = 2780f)
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

### Pie Chart

```kotlin
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice

@Composable
fun MyPieChart() {
    PieChart(
        data = PieChartData(
            slices = listOf(
                PieSlice(name = "Group A", value = 400f, color = Color(0xFF0088FE)),
                PieSlice(name = "Group B", value = 300f, color = Color(0xFF00C49F)),
                PieSlice(name = "Group C", value = 300f, color = Color(0xFFFFBB28)),
                PieSlice(name = "Group D", value = 200f, color = Color(0xFFFF8042))
            ),
            config = PieChartConfig(
                showLabels = true,
                innerRadius = 0f // 0 for pie, > 0 for donut
            )
        ),
        modifier = Modifier.size(300.dp)
    )
}
```

### Composed Chart (Multiple Types)

```kotlin
import com.majid2851.charts.ui.components.composed.ComposedChart
import com.majid2851.charts.domain.model.ComposedChartData

@Composable
fun MyComposedChart() {
    ComposedChart(
        data = ComposedChartData(
            categories = listOf("Page A", "Page B", "Page C"),
            lineDataSets = listOf(
                LineDataSet(
                    dataKey = "line",
                    label = "Temperature",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 20f),
                        DataPoint(x = 1f, y = 25f),
                        DataPoint(x = 2f, y = 22f)
                    ),
                    lineColor = Color.Red
                )
            ),
            barDataSets = listOf(
                ComposedBarDataSet(
                    dataKey = "bar",
                    label = "Sales",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 100f),
                        DataPoint(x = 1f, y = 150f),
                        DataPoint(x = 2f, y = 120f)
                    ),
                    color = Color.Blue
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

## 📊 Chart Types

### 1. Line Chart
- Simple line chart
- Multi-series
- Curved lines
- Dashed lines
- Area fill
- Custom dots
- Reference lines
- Negative values support
- Connect nulls

### 2. Bar Chart
- Simple bar chart
- Stacked bars
- Grouped bars
- Mixed bars
- Positive & negative values
- Biaxial (dual Y-axis)
- Custom bar widths
- Rounded corners

### 3. Pie Chart
- Standard pie chart
- Donut chart
- Two-level pie
- Custom labels
- Label positioning
- Active slice highlighting

### 4. Area Chart
- Simple area
- Stacked area
- Percent area (100% stacked)
- Cardinal (smooth) area
- Connect nulls
- Fill by value
- Synchronized charts

### 5. Scatter Chart
- Simple scatter
- Bubble chart
- Joint line + scatter
- With grid cells
- With labels
- 3D effect scatter

### 6. Radar Chart
- Simple radar
- Multi-series radar
- Specified domain
- Custom shapes
- Fill and stroke

### 7. Composed Chart
- Line + Bar + Area
- Multiple Y-axes
- Banded chart
- Scatter + line of best fit
- Target price chart
- Vertical layout

### 8. Radial Bar Chart
- Concentric circles
- Custom angles
- Background fill
- Label positioning

### 9. TreeMap
- Hierarchical data
- Squarified layout
- Nested rectangles
- Custom colors per node
- Depth-based styling

## 🎨 Customization

### Global Configuration

```kotlin
val config = ChartConfig(
    showGrid = true,
    showAxes = true,
    showLegend = true,
    animationEnabled = true,
    animationDuration = 1000,
    backgroundColor = Color.White
)
```

### Axis Customization

```kotlin
LineChartData(
    lines = listOf(/* ... */),
    xAxisConfig = AxisConfig(
        showLabels = true,
        labelRotation = 45f,
        fontSize = 12.sp,
        color = Color.Black
    ),
    yAxisConfig = AxisConfig(
        showGridLines = true,
        gridLineColor = Color.LightGray,
        min = 0f,
        max = 100f
    )
)
```

### Grid Customization

```kotlin
import com.majid2851.charts.domain.model.CartesianGridPresets

LineChart(
    data = myData,
    gridPreset = CartesianGridPresets.DOTS,
    // Or custom:
    gridConfig = GridConfig(
        showVerticalLines = true,
        showHorizontalLines = true,
        strokeWidth = 1f,
        color = Color.Gray
    )
)
```

### Interactive Features

```kotlin
// Zoom and Pan
LineChart(
    data = myData,
    enableZoom = true,
    enablePan = true,
    minZoom = 0.5f,
    maxZoom = 5f
)

// Tap Interaction
var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }

LineChart(
    data = myData,
    onPointTap = { point ->
        selectedPoint = point
    }
)
```

## 📱 Responsive Design

Use `ResponsiveContainer` to make charts adapt to different screen sizes:

```kotlin
import com.majid2851.charts.ui.components.responsive.ResponsiveContainer

ResponsiveContainer(
    width = null, // null = fill parent
    height = 400.dp,
    minWidth = 200.dp,
    minHeight = 200.dp
) { containerWidth, containerHeight ->
    LineChart(
        data = myData,
        modifier = Modifier.size(containerWidth, containerHeight)
    )
}
```

## 🎯 Common Use Cases

### Dashboard Card

```kotlin
Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Sales Overview", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LineChart(
            data = salesData,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}
```

### Full-Screen Chart

```kotlin
Box(modifier = Modifier.fillMaxSize()) {
    LineChart(
        data = detailedData,
        enableZoom = true,
        enablePan = true,
        modifier = Modifier.fillMaxSize()
    )
}
```

### Side-by-Side Comparison

```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    LineChart(
        data = data1,
        modifier = Modifier
            .weight(1f)
            .height(200.dp)
    )
    LineChart(
        data = data2,
        modifier = Modifier
            .weight(1f)
            .height(200.dp)
    )
}
```

## 📚 API Documentation

### Core Data Models

#### ChartData (Base Interface)
```kotlin
interface ChartData {
    val title: String?
}
```

#### LineChartData
```kotlin
data class LineChartData(
    override val title: String? = null,
    val lines: List<LineDataSet>,
    val xAxisLabels: List<String> = emptyList(),
    val config: ChartConfig = ChartConfig()
) : ChartData
```

#### BarChartData
```kotlin
data class BarChartData(
    override val title: String? = null,
    val categories: List<String>,
    val datasets: List<BarDataSet>,
    val groupMode: BarGroupMode = BarGroupMode.GROUPED,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

#### PieChartData
```kotlin
data class PieChartData(
    override val title: String? = null,
    val slices: List<PieSlice>,
    val config: PieChartConfig = PieChartConfig()
) : ChartData
```

### Enums

#### ChartType
```kotlin
enum class ChartType {
    LINE, BAR, PIE, AREA, SCATTER, 
    RADAR, COMPOSED, RADIAL_BAR, TREEMAP
}
```

#### BarGroupMode
```kotlin
enum class BarGroupMode {
    GROUPED, STACKED, STACKED_PERCENT
}
```

## 🔧 Advanced Usage

### Custom Themes

```kotlin
import com.majid2851.charts.ui.theme.ChartsTheme

ChartsTheme(
    colors = ChartColors(
        primary = Color(0xFF6366F1),
        secondary = Color(0xFF8B5CF6),
        background = Color.White,
        surface = Color(0xFFF3F4F6),
        onPrimary = Color.White,
        onSurface = Color.Black
    )
) {
    LineChart(data = myData)
}
```

### Data Transformation

```kotlin
// Convert from your data model
val chartData = LineChartData(
    lines = listOf(
        LineDataSet(
            label = "Metric",
            dataPoints = myDataList.mapIndexed { index, value ->
                DataPoint(x = index.toFloat(), y = value.amount.toFloat())
            },
            lineColor = Color.Blue
        )
    ),
    xAxisLabels = myDataList.map { it.date }
)
```

### State Management with ViewModel

```kotlin
class ChartViewModel : ViewModel() {
    private val _chartData = MutableStateFlow<LineChartData?>(null)
    val chartData: StateFlow<LineChartData?> = _chartData.asStateFlow()
    
    fun loadData() {
        viewModelScope.launch {
            val data = repository.fetchChartData()
            _chartData.value = data.toLineChartData()
        }
    }
}

@Composable
fun ChartScreen(viewModel: ChartViewModel = viewModel()) {
    val chartData by viewModel.chartData.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    
    chartData?.let { data ->
        LineChart(
            data = data,
            modifier = Modifier.fillMaxSize()
        )
    }
}
```

## 🧪 Testing

```kotlin
@Test
fun testChartDataCreation() {
    val data = LineChartData(
        lines = listOf(
            LineDataSet(
                label = "Test",
                dataPoints = listOf(
                    DataPoint(0f, 10f),
                    DataPoint(1f, 20f)
                )
            )
        )
    )
    
    assertEquals(1, data.lines.size)
    assertEquals("Test", data.lines[0].label)
}
```

## 📖 Examples

Check out the demo app included in this repository for comprehensive examples of all chart types and features. Run the `:app` module to see:

- 50+ pre-built variants
- Interactive examples
- Customization demos
- Performance benchmarks
- Best practices

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

```
Copyright 2025 Majid

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## 🙏 Acknowledgments

- Inspired by [Recharts](https://recharts.org/) for the web
- Built with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Material Design 3 guidelines

## 📞 Support

- 📧 Email: majid2851@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/majid2851/charts/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/majid2851/charts/discussions)

## 🗺️ Roadmap

- [ ] Animations for data changes
- [ ] More interactive features
- [ ] Real-time data streaming support
- [ ] Export to image/PDF
- [ ] Accessibility improvements
- [ ] More chart types (Gantt, Sankey, etc.)

---

Made with ❤️ using Jetpack Compose


