# 📚 Compose Charts Library - API Reference

Complete API documentation for all chart components, data models, and utilities.

## Table of Contents

1. [Data Models](#data-models)
2. [Chart Components](#chart-components)
3. [Configuration Classes](#configuration-classes)
4. [Enums](#enums)
5. [Utilities](#utilities)

---

## Data Models

### Base Interface

#### ChartData
```kotlin
interface ChartData {
    val title: String?
}
```

All chart data models extend this interface.

---

### Line Chart

#### LineChartData
```kotlin
data class LineChartData(
    override val title: String? = null,
    val lines: List<LineDataSet>,
    val xAxisLabels: List<String> = emptyList(),
    val config: ChartConfig = ChartConfig(),
    val enableZoom: Boolean = false,
    val enablePan: Boolean = false,
    val showCrosshair: Boolean = false
) : ChartData
```

**Parameters:**
- `title`: Optional chart title
- `lines`: List of line datasets to display
- `xAxisLabels`: Labels for X-axis points
- `config`: Global chart configuration
- `enableZoom`: Enable pinch-to-zoom gesture
- `enablePan`: Enable pan gesture
- `showCrosshair`: Show crosshair on tap

#### LineDataSet
```kotlin
data class LineDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val lineWidth: Float = 2f,
    val isCurved: Boolean = false,
    val isDashed: Boolean = false,
    val showPoints: Boolean = true,
    val pointRadius: Float = 4f,
    val fillArea: Boolean = false,
    val fillColor: Color? = null,
    val connectNulls: Boolean = false
)
```

**Parameters:**
- `label`: Dataset identifier/name
- `dataPoints`: List of data points (can contain nulls for gaps)
- `lineColor`: Color of the line
- `lineWidth`: Width of the line in pixels
- `isCurved`: Use curved (Bézier) lines
- `isDashed`: Draw dashed line
- `showPoints`: Show point markers
- `pointRadius`: Radius of point markers
- `fillArea`: Fill area under the line
- `fillColor`: Color for area fill (defaults to lineColor with alpha)
- `connectNulls`: Connect through null values

---

### Bar Chart

#### BarChartData
```kotlin
data class BarChartData(
    override val title: String? = null,
    val categories: List<String>,
    val datasets: List<BarDataSet>,
    val groupMode: BarGroupMode = BarGroupMode.GROUPED,
    val barWidth: Float = 0.6f,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

**Parameters:**
- `title`: Optional chart title
- `categories`: Category labels (X-axis)
- `datasets`: List of bar datasets
- `groupMode`: How to display multiple datasets (GROUPED, STACKED, STACKED_PERCENT)
- `barWidth`: Width of bars (0.0 to 1.0, relative to category width)
- `config`: Global chart configuration

#### BarDataSet
```kotlin
data class BarDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val color: Color = Color.Blue,
    val borderRadius: Float = 0f
)
```

**Parameters:**
- `label`: Dataset identifier/name
- `dataPoints`: List of data points
- `color`: Bar color
- `borderRadius`: Rounded corner radius for bars

---

### Pie Chart

#### PieChartData
```kotlin
data class PieChartData(
    override val title: String? = null,
    val slices: List<PieSlice>,
    val config: PieChartConfig = PieChartConfig()
) : ChartData
```

**Parameters:**
- `title`: Optional chart title
- `slices`: List of pie slices
- `config`: Pie-specific configuration

#### PieSlice
```kotlin
data class PieSlice(
    val name: String,
    val value: Float,
    val color: Color,
    val label: String? = null
)
```

**Parameters:**
- `name`: Slice identifier
- `value`: Numeric value
- `color`: Slice color
- `label`: Optional custom label (defaults to name)

#### PieChartConfig
```kotlin
data class PieChartConfig(
    val showLabels: Boolean = true,
    val labelPosition: LabelPosition = LabelPosition.OUTSIDE,
    val innerRadius: Float = 0f,
    val outerRadius: Float = 0.8f,
    val startAngle: Float = 0f,
    val showLegend: Boolean = true,
    val showPercentages: Boolean = false
)
```

**Parameters:**
- `showLabels`: Show slice labels
- `labelPosition`: Where to position labels (INSIDE, OUTSIDE)
- `innerRadius`: Inner radius (0 = pie, >0 = donut)
- `outerRadius`: Outer radius (0.0 to 1.0, relative to container)
- `startAngle`: Starting angle in degrees
- `showLegend`: Show legend
- `showPercentages`: Show percentage values

---

### Area Chart

#### AreaChartData
```kotlin
data class AreaChartData(
    override val title: String? = null,
    val areas: List<AreaDataSet>,
    val xAxisLabels: List<String> = emptyList(),
    val stackMode: AreaStackMode = AreaStackMode.NONE,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

**Parameters:**
- `title`: Optional chart title
- `areas`: List of area datasets
- `xAxisLabels`: Labels for X-axis
- `stackMode`: Stacking mode (NONE, STACKED, STACKED_PERCENT)
- `config`: Global chart configuration

#### AreaDataSet
```kotlin
data class AreaDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val lineWidth: Float = 2f,
    val isCurved: Boolean = true,
    val connectNulls: Boolean = false
)
```

---

### Scatter Chart

#### ScatterChartData
```kotlin
data class ScatterChartData(
    override val title: String? = null,
    val datasets: List<ScatterDataSet>,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

#### ScatterDataSet
```kotlin
data class ScatterDataSet(
    val label: String,
    val dataPoints: List<DataPoint>,
    val color: Color = Color.Blue,
    val pointSize: Float = 8f,
    val shape: PointShape = PointShape.CIRCLE
)
```

**PointShape enum:**
- `CIRCLE`
- `SQUARE`
- `TRIANGLE`
- `DIAMOND`
- `CROSS`
- `PLUS`

---

### Radar Chart

#### RadarChartData
```kotlin
data class RadarChartData(
    override val title: String? = null,
    val datasets: List<RadarDataSet>,
    val labels: List<String>,
    val maxValue: Float? = null,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

#### RadarDataSet
```kotlin
data class RadarDataSet(
    val label: String,
    val values: List<Float>,
    val strokeColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val strokeWidth: Float = 2f
)
```

---

### Composed Chart

#### ComposedChartData
```kotlin
data class ComposedChartData(
    override val title: String? = null,
    val categories: List<String>,
    val lineDataSets: List<LineDataSet> = emptyList(),
    val barDataSets: List<ComposedBarDataSet> = emptyList(),
    val areaDataSets: List<AreaDataSet> = emptyList(),
    val scatterDataSets: List<ScatterDataSet> = emptyList(),
    val config: ChartConfig = ChartConfig()
) : ChartData
```

**Note:** Combines multiple chart types in one view.

---

### Radial Bar Chart

#### RadialBarChartData
```kotlin
data class RadialBarChartData(
    override val title: String? = null,
    val bars: List<RadialBarEntry>,
    val config: ChartConfig = ChartConfig(),
    val centerX: Float = 0.5f,
    val centerY: Float = 0.5f,
    val innerRadius: Float = 0.2f,
    val outerRadius: Float = 0.9f,
    val barSize: Float = 10f,
    val startAngle: Float = 90f,
    val endAngle: Float = -270f,
    val maxValue: Float? = null,
    val labelPosition: RadialLabelPosition = RadialLabelPosition.INSIDE_START
) : ChartData
```

#### RadialBarEntry
```kotlin
data class RadialBarEntry(
    val name: String,
    val value: Float,
    val fill: Color,
    val backgroundFill: Color? = null
)
```

---

### TreeMap

#### TreeMapData
```kotlin
data class TreeMapData(
    override val title: String? = null,
    val nodes: List<TreeMapNode>,
    val colorPalette: List<Color> = defaultTreeMapColors,
    val defaultFill: Color = Color.LightGray,
    val strokeColor: Color = Color.White,
    val strokeWidth: Float = 2f,
    val aspectRatio: Float = 1f,
    val showLabels: Boolean = true,
    val labelTextSize: Float = 14f,
    val labelColor: Color = Color.White,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

#### TreeMapNode
```kotlin
data class TreeMapNode(
    val name: String,
    val size: Float = 0f,
    val children: List<TreeMapNode> = emptyList(),
    val customColor: Color? = null,
    val depth: Int = 0
)
```

---

## Chart Components

### LineChart

```kotlin
@Composable
fun LineChart(
    data: LineChartData,
    modifier: Modifier = Modifier
)
```

**Usage:**
```kotlin
LineChart(
    data = LineChartData(
        lines = listOf(/* ... */),
        xAxisLabels = listOf("Jan", "Feb", "Mar")
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
)
```

---

### BarChart

```kotlin
@Composable
fun BarChart(
    data: BarChartData,
    modifier: Modifier = Modifier
)
```

---

### PieChart

```kotlin
@Composable
fun PieChart(
    data: PieChartData,
    modifier: Modifier = Modifier
)
```

---

### AreaChart

```kotlin
@Composable
fun AreaChart(
    data: AreaChartData,
    modifier: Modifier = Modifier
)
```

---

### ScatterChart

```kotlin
@Composable
fun ScatterChart(
    data: ScatterChartData,
    modifier: Modifier = Modifier
)
```

---

### RadarChart

```kotlin
@Composable
fun RadarChart(
    data: RadarChartData,
    modifier: Modifier = Modifier
)
```

---

### ComposedChart

```kotlin
@Composable
fun ComposedChart(
    data: ComposedChartData,
    modifier: Modifier = Modifier
)
```

---

### RadialBarChart

```kotlin
@Composable
fun RadialBarChart(
    data: RadialBarChartData,
    modifier: Modifier = Modifier
)
```

---

### TreeMapChart

```kotlin
@Composable
fun TreeMapChart(
    data: TreeMapData,
    modifier: Modifier = Modifier
)
```

---

### ResponsiveContainer

```kotlin
@Composable
fun ResponsiveContainer(
    width: Dp? = null,
    height: Dp? = null,
    minWidth: Dp = 0.dp,
    minHeight: Dp = 0.dp,
    maxWidth: Dp = Dp.Infinity,
    maxHeight: Dp = Dp.Infinity,
    aspectRatio: Float? = null,
    modifier: Modifier = Modifier,
    content: @Composable (containerWidth: Dp, containerHeight: Dp) -> Unit
)
```

**Usage:**
```kotlin
ResponsiveContainer(
    width = null, // fill parent
    height = 400.dp
) { w, h ->
    LineChart(
        data = myData,
        modifier = Modifier.size(w, h)
    )
}
```

---

## Configuration Classes

### ChartConfig

```kotlin
data class ChartConfig(
    val showGrid: Boolean = true,
    val showAxes: Boolean = true,
    val showLegend: Boolean = true,
    val backgroundColor: Color = Color.Transparent,
    val padding: PaddingValues = PaddingValues(16.dp),
    val animationEnabled: Boolean = false,
    val animationDuration: Int = 1000
)
```

---

## Enums

### ChartType

```kotlin
enum class ChartType {
    LINE,
    BAR,
    PIE,
    AREA,
    SCATTER,
    RADAR,
    COMPOSED,
    RADIAL_BAR,
    TREEMAP
}
```

### BarGroupMode

```kotlin
enum class BarGroupMode {
    GROUPED,
    STACKED,
    STACKED_PERCENT
}
```

### AreaStackMode

```kotlin
enum class AreaStackMode {
    NONE,
    STACKED,
    STACKED_PERCENT
}
```

### LabelPosition

```kotlin
enum class LabelPosition {
    INSIDE,
    OUTSIDE,
    INSIDE_START,
    INSIDE_END
}
```

### PointShape

```kotlin
enum class PointShape {
    CIRCLE,
    SQUARE,
    TRIANGLE,
    DIAMOND,
    CROSS,
    PLUS
}
```

---

## Utilities

### DataPoint

```kotlin
data class DataPoint(
    val x: Float,
    val y: Float,
    val label: String? = null
)
```

Basic data point used across all chart types.

### CartesianGridPresets

```kotlin
object CartesianGridPresets {
    val LINES: GridConfig
    val DOTS: GridConfig
    val DASHED: GridConfig
    val NONE: GridConfig
}
```

Pre-configured grid styles.

---

## Common Patterns

### Loading State

```kotlin
@Composable
fun ChartWithLoading(viewModel: ChartViewModel) {
    val state by viewModel.state.collectAsState()
    
    when (state) {
        is Loading -> CircularProgressIndicator()
        is Success -> LineChart(data = state.data)
        is Error -> Text("Error loading chart")
    }
}
```

### Conditional Rendering

```kotlin
@Composable
fun ConditionalChart(showChart: Boolean) {
    AnimatedVisibility(visible = showChart) {
        LineChart(data = myData)
    }
}
```

### Dynamic Data Updates

```kotlin
var chartData by remember { mutableStateOf(initialData) }

LaunchedEffect(key1 = refreshTrigger) {
    chartData = fetchNewData()
}

LineChart(data = chartData)
```

---

For more examples, see the demo app or visit the [GitHub repository](https://github.com/majid2851/charts).


