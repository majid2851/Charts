# LineChart Component - Variants & Features

Comprehensive LineChart implementation inspired by [Recharts](https://recharts.github.io/en-US/examples) with support for multiple chart types and configurations.

## 📊 Available LineChart Variants

### 1. Simple Line Chart
**Description:** Basic line chart with default settings  
**Use Case:** General-purpose data visualization, trend analysis  
**Features:**
- Single or multiple data series
- Default axes and grid
- Point markers
- Legend

```kotlin
LineChart(
    data = LineChartData(
        title = "Simple Line Chart",
        lines = listOf(
            LineDataSet(
                label = "Sales",
                dataPoints = listOf(
                    DataPoint(1f, 400f),
                    DataPoint(2f, 300f),
                    DataPoint(3f, 600f)
                ),
                lineColor = AppColors.Blue
            )
        )
    )
)
```

---

### 2. Tiny Line Chart
**Description:** Minimal sparkline-style chart  
**Use Case:** Dashboard widgets, inline metrics, space-constrained displays  
**Features:**
- No axes or grid
- No legend
- Minimal padding
- Compact size

```kotlin
LineChart(
    data = LineChartData(
        lines = listOf(lineDataSet),
        config = ChartConfig(
            showGrid = false,
            showAxis = false,
            showLegend = false,
            chartPadding = 8.dp
        )
    ),
    modifier = Modifier.height(100.dp)
)
```

---

### 3. Dashed Line Chart
**Description:** Lines with different stroke patterns  
**Use Case:** Distinguishing between actual/projected, target/current  
**Features:**
- Custom dash patterns
- Mix of solid and dashed lines
- Configurable dash intervals

```kotlin
LineDataSet(
    label = "Target",
    dataPoints = dataPoints,
    lineColor = AppColors.Blue,
    isDashed = true,
    dashPattern = floatArrayOf(10f, 5f)
)
```

---

### 4. Line Chart with Reference Lines
**Description:** Chart with horizontal/vertical threshold markers  
**Use Case:** Target comparison, threshold monitoring, SLA tracking  
**Features:**
- Horizontal reference lines
- Vertical reference lines
- Custom colors and styles
- Optional labels

```kotlin
LineChartData(
    lines = listOf(lineDataSet),
    referenceLines = listOf(
        ReferenceLine(
            value = 600f,
            label = "Target",
            color = AppColors.Red,
            isDashed = true
        )
    )
)
```

---

### 5. Line Chart Connect Nulls
**Description:** Handle missing data (nulls) in datasets  
**Use Case:** Data with gaps, incomplete time series  
**Features:**
- Option to connect through nulls
- Visual gaps for missing data
- Interpolation support

```kotlin
LineChartData(
    lines = listOf(
        LineDataSet(
            dataPoints = listOf(
                DataPoint(0f, 10f),
                null,  // Gap in data
                DataPoint(2f, 30f)
            )
        )
    ),
    connectNulls = false  // or true
)
```

---

### 6. Customized Dot Line Chart
**Description:** Different point shapes for each series  
**Use Case:** Multi-series differentiation, categorical data  
**Features:**
- Circle, Square, Triangle, Diamond, Cross, Star
- Custom point sizes
- Per-series configuration

```kotlin
LineDataSet(
    label = "Series A",
    dataPoints = dataPoints,
    customPointShape = PointShape.DIAMOND,
    pointRadius = 6f
)
```

**Available Shapes:**
- `PointShape.CIRCLE` - Default circular markers
- `PointShape.SQUARE` - Square markers
- `PointShape.TRIANGLE` - Triangular markers
- `PointShape.DIAMOND` - Diamond markers
- `PointShape.CROSS` - Cross markers
- `PointShape.STAR` - Star markers

---

### 7. Curved Line Chart
**Description:** Smooth bezier-curved lines  
**Use Case:** Elegant presentations, smooth trend visualization  
**Features:**
- Quadratic bezier curves
- Smooth transitions
- Compare curved vs straight

```kotlin
LineDataSet(
    label = "Curved",
    dataPoints = dataPoints,
    isCurved = true
)
```

---

### 8. Filled Area Line Chart
**Description:** Area under the line is filled with color  
**Use Case:** Emphasize magnitude, revenue/volume visualization  
**Features:**
- Transparent fill colors
- Customizable fill opacity
- Works with curved lines

```kotlin
LineDataSet(
    label = "Revenue",
    dataPoints = dataPoints,
    fillArea = true,
    fillColor = AppColors.Blue.copy(alpha = 0.3f)
)
```

---

### 9. Multi-Series Line Chart
**Description:** Multiple data series on the same chart  
**Use Case:** Comparative analysis, multiple metrics  
**Features:**
- Unlimited series support
- Individual colors per series
- Automatic legend generation
- Independent styling

```kotlin
LineChartData(
    lines = listOf(
        LineDataSet(label = "Product A", ...),
        LineDataSet(label = "Product B", ...),
        LineDataSet(label = "Product C", ...)
    )
)
```

---

### 10. Negative Values Line Chart
**Description:** Charts with positive and negative values  
**Use Case:** Profit/loss, variance analysis, temperature changes  
**Features:**
- Zero baseline reference
- Positive and negative values
- Reference line at zero

```kotlin
LineChartData(
    lines = listOf(
        LineDataSet(
            dataPoints = listOf(
                DataPoint(1f, -50f),
                DataPoint(2f, 10f),
                DataPoint(3f, 50f)
            )
        )
    ),
    referenceLines = listOf(
        ReferenceLine(value = 0f, label = "Break Even")
    )
)
```

---

## 🎨 Configuration Options

### LineChartData
```kotlin
data class LineChartData(
    val title: String? = null,
    val description: String? = null,
    val lines: List<LineDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val referenceLines: List<ReferenceLine> = emptyList(),
    val connectNulls: Boolean = false,
    val orientation: ChartOrientation = ChartOrientation.HORIZONTAL
)
```

### LineDataSet
```kotlin
data class LineDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val lineWidth: Float = 2f,
    val showPoints: Boolean = true,
    val pointRadius: Float = 4f,
    val isCurved: Boolean = false,
    val fillArea: Boolean = false,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val isDashed: Boolean = false,
    val dashPattern: FloatArray = floatArrayOf(10f, 5f),
    val useSecondaryYAxis: Boolean = false,
    val customPointShape: PointShape = PointShape.CIRCLE
)
```

### ChartConfig
```kotlin
data class ChartConfig(
    val showGrid: Boolean = true,
    val showAxis: Boolean = true,
    val showLegend: Boolean = true,
    val animationEnabled: Boolean = true,
    val animationDuration: Int = 300,
    val backgroundColor: Color = Color.Transparent,
    val chartPadding: Dp = 16.dp,
    val isInteractive: Boolean = true
)
```

---

## 📚 Usage Examples

### Basic Usage
```kotlin
@Composable
fun MyChart() {
    LineChart(
        data = LineChartData(
            title = "My Chart",
            lines = listOf(
                LineDataSet(
                    label = "Sales",
                    dataPoints = getSalesData()
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Advanced Configuration
```kotlin
@Composable
fun AdvancedChart() {
    LineChart(
        data = LineChartData(
            title = "Sales Analysis",
            lines = listOf(
                LineDataSet(
                    label = "Actual",
                    dataPoints = actualData,
                    lineColor = AppColors.Blue,
                    fillArea = true,
                    isCurved = true
                ),
                LineDataSet(
                    label = "Target",
                    dataPoints = targetData,
                    lineColor = AppColors.Red,
                    isDashed = true
                )
            ),
            referenceLines = listOf(
                ReferenceLine(
                    value = 1000f,
                    label = "Goal",
                    color = AppColors.Green
                )
            ),
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

---

## 🎯 Best Practices

1. **Choose the Right Variant**
   - Use **Tiny** for dashboards and widgets
   - Use **Dashed** for comparing actual vs projected
   - Use **Filled Area** to emphasize magnitude
   - Use **Multi-Series** for comparisons

2. **Colors**
   - Use distinct colors for different series
   - Maintain adequate contrast
   - Consider colorblind-friendly palettes

3. **Data Points**
   - Limit number of points for readability
   - Use curved lines for smoother appearance
   - Hide points on dense data for clarity

4. **Reference Lines**
   - Use for important thresholds
   - Add labels for context
   - Keep styling subtle

5. **Performance**
   - Use `remember` for data calculations
   - Avoid recreating data objects unnecessarily
   - Consider data aggregation for large datasets

---

## 🔗 Related Components

- **AreaChart** - Stacked area charts
- **BarChart** - Bar chart variations
- **ComposedChart** - Combining multiple chart types

---

## 📖 References

Inspired by [Recharts LineChart Examples](https://recharts.github.io/en-US/examples)


















