# How to Change X-Axis Font Size

## Problem
The X-axis labels are too small and changing the font size doesn't seem to work.

## Solution

You need to configure the `xAxisConfig` when creating your `LineChartData`.

### ✅ Correct Way

```kotlin
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = ChartConfig(
            showAxis = true
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 32f  // ✅ Change this value (default is 16f)
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 32f  // Also increase Y-axis if needed
        )
    )
)
```

### 📏 Recommended Font Sizes

| Use Case | Font Size |
|----------|-----------|
| Small/Compact | 12f - 16f |
| Normal (Default) | 16f - 20f |
| Large/Readable | 24f - 32f |
| Extra Large | 36f - 48f |

### 🎯 Complete Example

```kotlin
@Composable
fun MyChartWithLargeLabels() {
    LineChart(
        data = LineChartData(
            title = "Sales Data",
            lines = listOf(
                LineDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(0f, 2400f, "Jan"),
                        DataPoint(1f, 1398f, "Feb"),
                        DataPoint(2f, 9800f, "Mar"),
                        // ... more data
                    ),
                    lineColor = Color.Blue
                )
            ),
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true
            ),
            // ✅ Configure X-axis
            xAxisConfig = AxisConfig(
                showLabels = true,
                labelTextSize = 28f,  // Large font
                axisColor = Color.Black
            ),
            // ✅ Configure Y-axis
            yAxisConfig = AxisConfig(
                showLabels = true,
                labelTextSize = 28f,  // Large font
                labelCount = 5,
                axisColor = Color.Black
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

### 🔧 All AxisConfig Options

```kotlin
AxisConfig(
    showLabels = true,           // Show/hide labels
    showGridLines = true,        // Show/hide grid lines
    labelCount = 5,              // Number of labels (Y-axis)
    axisColor = Color.Gray,      // Axis line color
    gridColor = Color.LightGray, // Grid line color
    labelTextSize = 32f          // ✅ FONT SIZE (in pixels)
)
```

### ❌ Common Mistakes

**Mistake 1: Not setting xAxisConfig**
```kotlin
// ❌ Wrong - uses default 16f
LineChart(
    data = LineChartData(
        lines = myData
        // Missing xAxisConfig!
    )
)
```

**Mistake 2: Setting in wrong place**
```kotlin
// ❌ Wrong - labelTextSize is not in ChartConfig
LineChart(
    data = LineChartData(
        lines = myData,
        config = ChartConfig(
            labelTextSize = 32f  // ❌ This doesn't exist
        )
    )
)
```

**Mistake 3: Using sp instead of f**
```kotlin
// ❌ Wrong - use Float (f), not sp
xAxisConfig = AxisConfig(
    labelTextSize = 32.sp  // ❌ Wrong
)

// ✅ Correct
xAxisConfig = AxisConfig(
    labelTextSize = 32f  // ✅ Correct
)
```

### 📱 Responsive Font Sizes

For different screen sizes:

```kotlin
@Composable
fun ResponsiveChart() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    
    // Adjust font size based on screen width
    val fontSize = when {
        screenWidth < 360 -> 18f  // Small phones
        screenWidth < 600 -> 24f  // Normal phones
        screenWidth < 840 -> 32f  // Large phones/small tablets
        else -> 40f               // Tablets
    }
    
    LineChart(
        data = LineChartData(
            lines = myData,
            xAxisConfig = AxisConfig(
                labelTextSize = fontSize
            ),
            yAxisConfig = AxisConfig(
                labelTextSize = fontSize
            )
        )
    )
}
```

### 🎨 Custom Styling

```kotlin
LineChart(
    data = LineChartData(
        title = "Styled Chart",
        lines = myData,
        xAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 30f,        // Large font
            axisColor = Color(0xFF2196F3),  // Blue axis
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            labelTextSize = 30f,        // Large font
            labelCount = 6,             // More labels
            axisColor = Color(0xFF2196F3),  // Blue axis
        )
    )
)
```

## Quick Fix

If your labels are too small, just add this to your chart:

```kotlin
xAxisConfig = AxisConfig(labelTextSize = 28f),
yAxisConfig = AxisConfig(labelTextSize = 28f)
```

That's it! 🎉

