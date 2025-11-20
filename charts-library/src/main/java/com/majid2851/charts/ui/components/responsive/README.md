# Responsive Container

## Overview

The **ResponsiveContainer** component makes charts automatically adapt to their parent container size, matching Recharts' responsive behavior. It handles dynamic resizing, aspect ratio maintenance, and constraint-based layouts.

## Features

- 📐 **Automatic Sizing**: Charts fill parent container by default
- 🎯 **Aspect Ratio**: Maintain specific width/height ratios
- 📏 **Constraints**: Min/max width and height support
- ⚡ **Performance**: Efficient resizing with optional debouncing
- 🔄 **Reactive**: Responds to window/container size changes
- 🎨 **Flexible**: Works with all chart types

## Quick Start

### Basic Usage

```kotlin
// Responsive container (ignores size parameters with _, _)
ResponsiveContainer { _, _ ->
    AreaChart(
        data = data,
        modifier = Modifier.fillMaxSize()
    )
}

// Or use size parameters
ResponsiveContainer { width, height ->
    AreaChart(
        data = data,
        modifier = Modifier.size(width, height)
    )
}
```

### With Fixed Height

```kotlin
// Container with fixed height (width = 100% of parent)
ResponsiveContainer(modifier = Modifier.height(300.dp)) { _, _ ->
    ComposedChart(
        data = data,
        modifier = Modifier.fillMaxSize()
    )
}
```

### With Aspect Ratio

```kotlin
// Maintain 16:9 aspect ratio
ResponsiveContainer(
    aspect = 16f / 9f,
    modifier = Modifier.fillMaxWidth()
) { width, height ->
    PieChart(
        data = data,
        modifier = Modifier.size(width, height)
    )
}
```

## API Reference

### ResponsiveContainer Parameters

```kotlin
@Composable
fun ResponsiveContainer(
    width: Dp? = null,              // Fixed width (null = 100% of parent)
    height: Dp? = null,             // Fixed height (null = 100% of parent)
    minWidth: Dp = 0.dp,            // Minimum width constraint
    minHeight: Dp = 0.dp,           // Minimum height constraint
    maxWidth: Dp = Dp.Infinity,     // Maximum width constraint
    maxHeight: Dp = Dp.Infinity,    // Maximum height constraint
    aspect: Float? = null,          // Aspect ratio (width/height)
    debounce: Int = 0,              // Debounce time in ms
    modifier: Modifier = Modifier,
    content: @Composable (width: Dp, height: Dp) -> Unit
)
```

### Usage Tips

The `ResponsiveContainer` always provides width and height parameters. If you don't need them, use `{ _, _ ->` to ignore them:

```kotlin
// Ignore size parameters
ResponsiveContainer { _, _ ->
    AreaChart(data = data, modifier = Modifier.fillMaxSize())
}

// Use size parameters
ResponsiveContainer { width, height ->
    Text("Container is ${width}x${height}")
    AreaChart(data = data, modifier = Modifier.size(width, height))
}
```

## Examples

### Example 1: Responsive Area Chart

Matches Recharts Area ResponsiveContainer example:

```kotlin
@Composable
fun ResponsiveAreaChartExample() {
    ResponsiveContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "uv",
                        dataPoints = listOf(
                            DataPoint(0f, 4000f, "Page A"),
                            DataPoint(1f, 3000f, "Page B"),
                            DataPoint(2f, 2000f, "Page C"),
                            DataPoint(3f, 2780f, "Page D"),
                            DataPoint(4f, 1890f, "Page E"),
                            DataPoint(5f, 2390f, "Page F"),
                            DataPoint(6f, 3490f, "Page G")
                        ),
                        strokeColor = Color(0xFF8884d8),
                        fillColor = Color(0xFF8884d8)
                    )
                ),
                config = ChartConfig(
                    showGrid = true,
                    showAxis = true
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
```

### Example 2: Responsive Composed Chart

Combines Bar, Line, and Area in responsive container:

```kotlin
@Composable
fun ResponsiveComposedChartExample() {
    ResponsiveContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        ComposedChart(
            data = ComposedChartData(
                lineDataSets = listOf(/* line data */),
                barDataSets = listOf(/* bar data */),
                areaDataSets = listOf(/* area data */),
                config = ChartConfig(
                    showGrid = true,
                    showAxis = true,
                    showLegend = true
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
```

### Example 3: Responsive Pie Chart

```kotlin
@Composable
fun ResponsivePieChartExample() {
    ResponsiveContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        PieChart(
            data = PieChartData(
                entries = listOf(
                    PieEntry("Group A", 400f, Color(0xFF0088FE)),
                    PieEntry("Group B", 300f, Color(0xFF00C49F)),
                    PieEntry("Group C", 300f, Color(0xFFFFBB28)),
                    PieEntry("Group D", 200f, Color(0xFFFF8042))
                ),
                showLabels = true
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
```

### Example 4: With Size Callback

Get notified when container size changes:

```kotlin
@Composable
fun ResponsiveWithCallback() {
    var containerSize by remember { mutableStateOf("") }
    
    ResponsiveContainerWithSize(
        modifier = Modifier.fillMaxSize(),
        onSizeChanged = { width, height ->
            containerSize = "Width: $width, Height: $height"
        }
    ) { width, height ->
        Column {
            Text("Container: $containerSize")
            LineChart(
                data = data,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
```

### Example 5: Aspect Ratio Maintenance

```kotlin
@Composable
fun AspectRatioChart() {
    // Maintains 4:3 aspect ratio
    ResponsiveContainer(
        aspect = 4f / 3f,
        modifier = Modifier.fillMaxWidth()
    ) { width, height ->
        BarChart(
            data = data,
            modifier = Modifier.size(width, height)
        )
    }
}
```

### Example 6: Constraints

```kotlin
@Composable
fun ConstrainedChart() {
    ResponsiveContainer(
        minWidth = 300.dp,
        minHeight = 200.dp,
        maxWidth = 1200.dp,
        maxHeight = 600.dp,
        modifier = Modifier.fillMaxSize()
    ) { width, height ->
        RadarChart(
            data = data,
            modifier = Modifier.size(width, height)
        )
    }
}
```

## Layout Patterns

### Dashboard Grid

```kotlin
@Composable
fun DashboardGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(modifier = Modifier.height(300.dp)) {
                ResponsiveContainer {
                    LineChart(data = lineData)
                }
            }
        }
        item {
            Card(modifier = Modifier.height(300.dp)) {
                ResponsiveContainer {
                    BarChart(data = barData)
                }
            }
        }
        item {
            Card(modifier = Modifier.height(300.dp)) {
                ResponsiveContainer {
                    PieChart(data = pieData)
                }
            }
        }
        item {
            Card(modifier = Modifier.height(300.dp)) {
                ResponsiveContainer {
                    AreaChart(data = areaData)
                }
            }
        }
    }
}
```

### Scrollable Charts

```kotlin
@Composable
fun ScrollableCharts() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ResponsiveContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                LineChart(data = salesData)
            }
        }
        item {
            ResponsiveContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                ComposedChart(data = analyticsData)
            }
        }
        item {
            ResponsiveContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                BarChart(data = comparisonData)
            }
        }
    }
}
```

## Responsive Behavior

### Width Adaptation

- **`width = null`**: Chart fills 100% of parent width
- **`width = 500.dp`**: Chart has fixed 500dp width
- **`width = null, aspect = 16/9`**: Width = parent width, height calculated

### Height Adaptation

- **`height = null`**: Chart fills 100% of parent height
- **`height = 300.dp`**: Chart has fixed 300dp height
- **`height = null, aspect = 4/3`**: Height calculated from width

### Parent Constraints

ResponsiveContainer respects parent container constraints:

```kotlin
// Parent defines max size
Box(modifier = Modifier.size(800.dp, 600.dp)) {
    ResponsiveContainer {
        // Chart fills 800dp x 600dp
        LineChart(data = data)
    }
}
```

## Performance Optimization

### Debouncing

Prevent excessive recomposition during rapid resizing:

```kotlin
ResponsiveContainer(
    debounce = 100, // Wait 100ms after resize
    modifier = Modifier.fillMaxSize()
) {
    // Expensive chart rendering
    ComposedChart(data = largeDataset)
}
```

### Conditional Rendering

Render different chart types based on size:

```kotlin
ResponsiveContainerWithSize { width, height ->
    if (width < 600.dp) {
        // Simplified chart for mobile
        SimplifiedLineChart(data)
    } else {
        // Full-featured chart for desktop
        DetailedLineChart(data)
    }
}
```

## Comparison with Recharts

This implementation matches Recharts ResponsiveContainer:

| Feature | Recharts | This Implementation |
|---------|----------|---------------------|
| Automatic sizing | ✅ | ✅ |
| Width/Height props | ✅ | ✅ |
| Aspect ratio | ✅ | ✅ |
| Min/Max constraints | ✅ | ✅ |
| Debounce | ✅ | ✅ |
| Size callback | ✅ | ✅ |
| Percentage sizing | ✅ | ✅ (via null values) |

## Best Practices

### 1. Use with Modifier.fillMaxSize()

```kotlin
// Good: Chart fills container
ResponsiveContainer {
    LineChart(data = data, modifier = Modifier.fillMaxSize())
}

// Less optimal: Fixed size inside responsive container
ResponsiveContainer {
    LineChart(data = data, modifier = Modifier.size(300.dp))
}
```

### 2. Set Height for Vertical Lists

```kotlin
// Good: Define height for scrollable content
LazyColumn {
    item {
        ResponsiveContainer(modifier = Modifier.height(300.dp)) {
            BarChart(data = data)
        }
    }
}
```

### 3. Aspect Ratio for Cards

```kotlin
// Good: Maintain consistent chart proportions
Card {
    ResponsiveContainer(aspect = 16f / 9f) {
        AreaChart(data = data)
    }
}
```

## Related Components

- **AreaChart**: Area chart component
- **BarChart**: Bar chart component
- **LineChart**: Line chart component
- **PieChart**: Pie chart component
- **ComposedChart**: Combined chart types
- **All other chart types**: Compatible with ResponsiveContainer

## Troubleshooting

### Chart doesn't fill container

**Problem**: Chart appears small or doesn't resize

**Solution**: Ensure chart uses `Modifier.fillMaxSize()`

```kotlin
ResponsiveContainer {
    LineChart(
        data = data,
        modifier = Modifier.fillMaxSize() // Required!
    )
}
```

### Chart flickers during resize

**Problem**: Excessive recomposition

**Solution**: Add debounce

```kotlin
ResponsiveContainer(debounce = 100) {
    ComposedChart(data = data)
}
```

### Aspect ratio not working

**Problem**: Container ignores aspect ratio

**Solution**: Set either width or height to null

```kotlin
// Correct
ResponsiveContainer(
    height = 300.dp,
    aspect = 16f / 9f // Width calculated automatically
) { /* ... */ }
```

## Examples in Variants

See these files for complete examples:
- `ResponsiveAreaChart.kt` - Area chart example
- `ResponsiveComposedChart.kt` - Composed chart example
- `ResponsivePieChart.kt` - Pie chart example


