# ComposedChart Component

## Overview

The ComposedChart component combines multiple chart types (Line, Bar, Area, and Scatter) into a single, unified visualization. This powerful component enables complex data storytelling by layering different representations of data.

## Features

- 🎨 **Multiple Chart Types**: Combine Line, Bar, Area, and Scatter in one chart
- 📊 **Shared Coordinate System**: All chart types use the same axes and bounds
- 🎯 **Flexible Data Structure**: Support for multiple data sets per chart type
- 📈 **Professional Styling**: Customizable colors, sizes, and styles
- 🔧 **Highly Configurable**: Extensive configuration options for every aspect
- 📱 **Responsive**: Adapts to different screen sizes

## Quick Start

```kotlin
@Composable
fun MyScreen() {
    ComposedChart(
        data = ComposedChartData(
            title = "Sales Overview",
            categories = listOf("Q1", "Q2", "Q3", "Q4"),
            lineDataSets = listOf(
                LineDataSet(
                    label = "Target",
                    dataPoints = listOf(
                        DataPoint(0f, 1000f),
                        DataPoint(1f, 1200f),
                        DataPoint(2f, 1400f),
                        DataPoint(3f, 1600f)
                    ),
                    lineColor = Color.Blue
                )
            ),
            barDataSets = listOf(
                ComposedBarDataSet(
                    dataKey = "actual",
                    label = "Actual",
                    dataPoints = listOf(
                        DataPoint(0f, 900f),
                        DataPoint(1f, 1300f),
                        DataPoint(2f, 1350f),
                        DataPoint(3f, 1700f)
                    ),
                    color = Color.Green
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## Variants

### 1. LineBarAreaComposedChart
The most comprehensive variant combining all four chart types.

**Use Case:** Complex dashboards requiring multiple data perspectives

```kotlin
LineBarAreaComposedChart(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

### 2. SameDataComposedChart
Shows the same data as both bars and lines.

**Use Case:** Comparing discrete vs continuous representations

```kotlin
SameDataComposedChart(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

### 3. VerticalComposedChart
Horizontal bars with vertical layout.

**Use Case:** Category comparisons with long labels

```kotlin
VerticalComposedChart(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

### 4. ComposedChartWithAxisLabels
Enhanced chart with custom axis labels.

**Use Case:** Professional reports and presentations

```kotlin
ComposedChartWithAxisLabels(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

### 5. ScatterAndLineOfBestFit
Scatter points with trend lines.

**Use Case:** Statistical analysis and regression

```kotlin
ScatterAndLineOfBestFit(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

### 6. BandedChart
Line chart with confidence bands.

**Use Case:** Showing data with uncertainty ranges

```kotlin
BandedChart(
    modifier = Modifier.fillMaxWidth().height(400.dp)
)
```

## Data Structure

### ComposedChartData
```kotlin
data class ComposedChartData(
    val title: String? = null,
    val categories: List<String>,
    val lineDataSets: List<LineDataSet> = emptyList(),
    val barDataSets: List<ComposedBarDataSet> = emptyList(),
    val areaDataSets: List<AreaDataSet> = emptyList(),
    val scatterDataSets: List<ScatterDataSet> = emptyList(),
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig()
)
```

### Data Sets

#### LineDataSet
```kotlin
LineDataSet(
    label: String,
    dataPoints: List<DataPoint?>,
    lineColor: Color = Color.Blue,
    lineWidth: Float = 2f,
    showPoints: Boolean = true
)
```

#### ComposedBarDataSet
```kotlin
ComposedBarDataSet(
    dataKey: String,
    label: String,
    dataPoints: List<DataPoint?>,
    color: Color = Color.Blue,
    barSize: Float = 20f
)
```

#### AreaDataSet
```kotlin
AreaDataSet(
    label: String,
    dataPoints: List<DataPoint?>,
    lineColor: Color,
    fillColor: Color,
    fillOpacity: Float = 0.3f
)
```

#### ScatterDataSet
```kotlin
ScatterDataSet(
    label: String,
    dataPoints: List<ScatterPoint>,
    pointColor: Color,
    pointSize: Float,
    pointShape: PointShape = PointShape.CIRCLE
)
```

## Configuration

### Chart Config
```kotlin
ChartConfig(
    showGrid: Boolean = true,
    showAxis: Boolean = true,
    showLegend: Boolean = true,
    backgroundColor: Color = Color.White,
    chartPadding: Dp = 16.dp
)
```

### Axis Config
```kotlin
AxisConfig(
    showLabels: Boolean = true,
    axisLabel: String? = null,
    labelPosition: LabelPosition = LabelPosition.OUTSIDE,
    axisColor: Color = Color.Black,
    labelTextSize: Float = 12f,
    labelCount: Int = 5
)
```

## Advanced Examples

### Multi-Dataset Chart
```kotlin
ComposedChart(
    data = ComposedChartData(
        categories = listOf("Jan", "Feb", "Mar", "Apr"),
        
        // Multiple areas
        areaDataSets = listOf(
            AreaDataSet(
                label = "Upper Bound",
                dataPoints = listOf(/*...*/),
                fillColor = Color.Gray.copy(alpha = 0.1f)
            ),
            AreaDataSet(
                label = "Lower Bound",
                dataPoints = listOf(/*...*/),
                fillColor = Color.Gray.copy(alpha = 0.1f)
            )
        ),
        
        // Multiple bars
        barDataSets = listOf(
            ComposedBarDataSet(
                dataKey = "sales",
                label = "Sales",
                dataPoints = listOf(/*...*/),
                color = Color.Blue
            ),
            ComposedBarDataSet(
                dataKey = "costs",
                label = "Costs",
                dataPoints = listOf(/*...*/),
                color = Color.Red
            )
        ),
        
        // Multiple lines
        lineDataSets = listOf(
            LineDataSet(
                label = "Trend",
                dataPoints = listOf(/*...*/),
                lineColor = Color.Green
            ),
            LineDataSet(
                label = "Forecast",
                dataPoints = listOf(/*...*/),
                lineColor = Color.Orange,
                isDashed = true
            )
        )
    )
)
```

### Custom Styling
```kotlin
ComposedChart(
    data = ComposedChartData(
        categories = categories,
        lineDataSets = listOf(/*...*/),
        config = ChartConfig(
            showGrid = true,
            cartesianGrid = CartesianGridConfig(
                showGrid = true,
                isDashed = true,
                gridColor = Color.LightGray,
                dashPattern = floatArrayOf(5f, 5f)
            ),
            backgroundColor = Color(0xFFF5F5F5)
        ),
        xAxisConfig = AxisConfig(
            axisLabel = "Time Period",
            labelPosition = LabelPosition.INSIDE_BOTTOM,
            axisColor = Color.DarkGray
        ),
        yAxisConfig = AxisConfig(
            axisLabel = "Values",
            labelPosition = LabelPosition.INSIDE_LEFT,
            axisColor = Color.DarkGray
        )
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(16.dp)
)
```

## Drawing Order

Charts are rendered in layers (bottom to top):
1. Grid (background)
2. Area charts (filled regions)
3. Bar charts (discrete values)
4. Line charts (continuous data)
5. Scatter charts (individual points)
6. Axes and labels (foreground)

This ensures proper visual hierarchy and readability.

## Tips & Best Practices

### 1. Color Harmony
Use complementary colors for different chart types:
```kotlin
val areaColor = Color(0xFF8884d8).copy(alpha = 0.3f)
val barColor = Color(0xFF413ea0)
val lineColor = Color(0xFFff7300)
val scatterColor = Color.Red
```

### 2. Data Alignment
Ensure all data points use consistent indices:
```kotlin
val categories = listOf("A", "B", "C", "D")
// All data sets should have 4 points corresponding to these categories
```

### 3. Visual Clarity
Don't overcrowd the chart:
```kotlin
// Good: 2-4 data sets per type
areaDataSets = listOf(area1, area2)
barDataSets = listOf(bar1)
lineDataSets = listOf(line1, line2)

// Avoid: Too many overlapping elements
```

### 4. Null Handling
Use null for missing data:
```kotlin
dataPoints = listOf(
    DataPoint(0f, 100f),
    null,  // Missing data
    DataPoint(2f, 150f)
)
```

### 5. Performance
For large datasets, consider:
- Limiting the number of data points displayed
- Using data aggregation for overview charts
- Implementing pagination or date range filtering

## Common Use Cases

### Financial Dashboard
```kotlin
// Revenue, Costs, Profit margin, and Key events
ComposedChart(
    data = ComposedChartData(
        categories = months,
        barDataSets = listOf(revenueBar, costsBar),
        lineDataSets = listOf(profitLine),
        scatterDataSets = listOf(keyEvents)
    )
)
```

### Sales Analytics
```kotlin
// Actual sales vs target with confidence bands
ComposedChart(
    data = ComposedChartData(
        categories = quarters,
        areaDataSets = listOf(confidenceBand),
        barDataSets = listOf(actualSales),
        lineDataSets = listOf(target)
    )
)
```

### Scientific Data
```kotlin
// Experimental data with trend line and reference range
ComposedChart(
    data = ComposedChartData(
        categories = timePoints,
        areaDataSets = listOf(referenceRange),
        scatterDataSets = listOf(measurements),
        lineDataSets = listOf(trendLine)
    )
)
```

## Testing

Run the examples screen to see all variants:
```kotlin
ComposedChartExamplesScreen()
```

## API Reference

See [COMPOSEDCHART_IMPLEMENTATION_GUIDE.md](../../../../../../../../../COMPOSEDCHART_IMPLEMENTATION_GUIDE.md) for detailed API documentation.

## Related Components

- **LineChart**: For simple line charts
- **BarChart**: For simple bar charts
- **AreaChart**: For simple area charts
- **ScatterChart**: For simple scatter charts

## Support

For issues or questions:
1. Check the implementation guide
2. Review the examples screen
3. Examine the variant implementations
4. Refer to Recharts documentation for concepts

## License

Part of the Charts library for Jetpack Compose.

