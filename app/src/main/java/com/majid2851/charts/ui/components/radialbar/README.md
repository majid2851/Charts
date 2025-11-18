# RadialBarChart Component

## Overview

The RadialBarChart component displays data as concentric circular bars, perfect for showing hierarchical or categorical data with percentage values. Each ring represents a different category, making it easy to compare multiple data points at once.

## Features

- 🎯 **Concentric Rings**: Multiple data series displayed as nested circles
- 📊 **Percentage Visualization**: Automatically calculates percentages from values
- 🎨 **Customizable Colors**: Each bar can have its own color
- 🏷️ **Flexible Labels**: Position labels inside or outside bars
- 📱 **Responsive Layout**: Adapts to container size
- 🎯 **Legend Support**: Optional legend for data identification

## Quick Start

```kotlin
@Composable
fun MyScreen() {
    RadialBarChart(
        data = RadialBarChartData(
            title = "Age Distribution",
            bars = listOf(
                RadialBarEntry(
                    name = "18-24",
                    value = 31.47f,
                    fill = Color(0xFF8884d8)
                ),
                RadialBarEntry(
                    name = "25-29",
                    value = 26.69f,
                    fill = Color(0xFF83a6ed)
                )
            ),
            config = ChartConfig(showLegend = true)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## Data Structure

### RadialBarChartData
```kotlin
data class RadialBarChartData(
    val title: String? = null,
    val bars: List<RadialBarEntry>,
    val config: ChartConfig = ChartConfig(),
    val centerX: Float = 0.3f,      // Relative position (0-1)
    val centerY: Float = 0.5f,      // Relative position (0-1)
    val barSize: Float = 14f,       // Thickness of each bar
    val startAngle: Float = 0f,     // Starting angle in degrees
    val endAngle: Float = 360f,     // Ending angle in degrees
    val showBackground: Boolean = true,
    val backgroundOpacity: Float = 0.3f
)
```

### RadialBarEntry
```kotlin
data class RadialBarEntry(
    val name: String,               // Category name
    val value: Float,               // Actual value
    val maxValue: Float = 100f,     // Maximum value for percentage
    val fill: Color,                // Bar color
    val label: String? = null,      // Optional custom label
    val showLabel: Boolean = true,
    val labelPosition: RadialLabelPosition = INSIDE_START
)
```

## Configuration Options

### Center Position
Control where the radial chart is centered:
```kotlin
RadialBarChartData(
    centerX = 0.3f,  // 30% from left
    centerY = 0.5f,  // 50% from top (centered vertically)
    // ...
)
```

### Bar Appearance
```kotlin
RadialBarChartData(
    barSize = 14f,           // Thickness of bars
    showBackground = true,    // Show background rings
    backgroundOpacity = 0.3f, // Background transparency
    // ...
)
```

### Angular Range
```kotlin
RadialBarChartData(
    startAngle = 0f,    // Start at top
    endAngle = 360f,    // Full circle
    // or endAngle = 180f for semi-circle
    // ...
)
```

### Label Positions
```kotlin
RadialBarEntry(
    name = "Category",
    value = 50f,
    labelPosition = RadialLabelPosition.INSIDE_START  // or INSIDE_END, OUTSIDE
)
```

## Common Use Cases

### Age Demographics
```kotlin
RadialBarChart(
    data = RadialBarChartData(
        title = "Population by Age Group",
        bars = listOf(
            RadialBarEntry("18-24", 31.47f, fill = Color(0xFF8884d8)),
            RadialBarEntry("25-29", 26.69f, fill = Color(0xFF83a6ed)),
            RadialBarEntry("30-34", 15.69f, fill = Color(0xFF8dd1e1)),
            RadialBarEntry("35-39", 8.22f, fill = Color(0xFF82ca9d))
        )
    )
)
```

### Survey Results
```kotlin
RadialBarChart(
    data = RadialBarChartData(
        title = "Customer Satisfaction",
        bars = listOf(
            RadialBarEntry("Very Satisfied", 45f, fill = Color.Green),
            RadialBarEntry("Satisfied", 30f, fill = Color(0xFF82ca9d)),
            RadialBarEntry("Neutral", 15f, fill = Color.Yellow),
            RadialBarEntry("Dissatisfied", 10f, fill = Color.Red)
        )
    )
)
```

### Category Distribution
```kotlin
RadialBarChart(
    data = RadialBarChartData(
        title = "Product Categories",
        bars = listOf(
            RadialBarEntry("Electronics", 35f, fill = Color(0xFF8884d8)),
            RadialBarEntry("Clothing", 25f, fill = Color(0xFF83a6ed)),
            RadialBarEntry("Food", 20f, fill = Color(0xFF8dd1e1)),
            RadialBarEntry("Other", 20f, fill = Color(0xFF82ca9d))
        ),
        centerX = 0.3f,
        barSize = 16f
    )
)
```

## Tips & Best Practices

### 1. Color Selection
Use a color gradient for related categories:
```kotlin
val colors = listOf(
    Color(0xFF8884d8),
    Color(0xFF83a6ed),
    Color(0xFF8dd1e1),
    Color(0xFF82ca9d),
    Color(0xFFa4de6c),
    Color(0xFFd0ed57),
    Color(0xFFffc658)
)
```

### 2. Limit Number of Bars
For readability, keep the number of bars between 4-8.

### 3. Percentage Values
Values are automatically converted to percentages based on `maxValue`:
```kotlin
RadialBarEntry(
    name = "Category",
    value = 75f,      // Actual value
    maxValue = 100f   // Will show as 75%
)
```

### 4. Layout Considerations
- Leave space for the legend by setting `centerX` to 0.3 or 0.4
- For full-width charts without legend, use `centerX = 0.5f`

### 5. Custom Labels
Provide custom labels for better context:
```kotlin
RadialBarEntry(
    name = "Group A",
    value = 85f,
    label = "85%",  // Custom formatted label
    showLabel = true
)
```

## Comparison with Recharts

This component matches the Recharts RadialBarChart API:
- ✅ Concentric circular bars
- ✅ Customizable center position
- ✅ Background rings
- ✅ Label positioning
- ✅ Legend support
- ✅ Custom colors per bar

## Related Components

- **PieChart**: For showing parts of a whole
- **DonutChart**: For hierarchical data with center focus
- **BarChart**: For rectangular bar comparisons

## Support

For issues or questions:
1. Check the implementation in `RadialBarChart.kt`
2. Review the preview example in the same file
3. See `ChartDemoScreen.kt` for live examples

