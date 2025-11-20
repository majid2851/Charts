# Scatter Chart Component

## Overview
Comprehensive Scatter Chart implementation for Android Compose with support for bubble charts, 3D visualization, and connected scatter plots.

## Quick Start

```kotlin
@Composable
fun MyScatterChart() {
    val data = listOf(
        ScatterPoint(100f, 200f, 200f),
        ScatterPoint(120f, 100f, 260f),
        ScatterPoint(170f, 300f, 400f)
    )

    ScatterChart(
        data = ScatterChartData(
            title = "My Scatter Chart",
            scatterSets = listOf(
                ScatterDataSet(
                    label = "Data Points",
                    dataPoints = data,
                    pointColor = Color(0xFF8884d8)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## File Structure

```
scatter/
├── ScatterChart.kt              # Main component
├── README.md                    # This file
└── variants/
    ├── SimpleScatterChart.kt          # Basic scatter
    ├── ThreeDimScatterChart.kt        # 3D with Z-axis
    ├── JointLineScatterChart.kt       # Connected points
    ├── BubbleChart.kt                 # Bubble visualization
    ├── ScatterChartWithLabels.kt      # With labels
    └── ScatterChartWithCells.kt       # Individual colors
```

## Variants

### 1. Simple Scatter Chart
- Single data series
- Basic circular points
- Interactive selection

### 2. Three-Dimensional Scatter Chart
- Multiple series
- Z-axis for point size
- Different shapes (star, triangle)

### 3. Joint Line Scatter Chart
- Points connected with lines
- Multiple series
- Custom shapes (cross, diamond)

### 4. Bubble Chart
- Varying bubble sizes
- Z-axis control
- Compact visualization

### 5. Scatter Chart With Labels
- Text labels on points
- Auto-positioning
- Z-axis support

### 6. Scatter Chart With Cells
- Individual point colors
- Custom color arrays
- Visual distinction

## Point Shapes

```kotlin
enum class PointShape {
    CIRCLE,    // ●
    SQUARE,    // ■
    TRIANGLE,  // ▲
    DIAMOND,   // ◆
    CROSS,     // ✕
    STAR       // ★
}
```

## Features

### Z-Axis (Bubble Sizing)
```kotlin
ScatterChartData(
    scatterSets = listOf(/*...*/),
    zAxisConfig = ZAxisConfig(
        range = Pair(20f, 200f) // Min/max size in pixels
    )
)
```

### Line Connections
```kotlin
ScatterDataSet(
    showLine = true,
    lineColor = Color.Blue,
    lineWidth = 2f
)
```

### Point Labels
```kotlin
ScatterDataSet(
    showLabels = true,
    dataPoints = listOf(
        ScatterPoint(100f, 200f, label = "Point A")
    )
)
```

### Custom Colors
```kotlin
// Per-dataset colors
ScatterDataSet(
    customColors = listOf(Color.Red, Color.Green, Color.Blue)
)

// Per-point colors
ScatterPoint(100f, 200f, color = Color.Red)
```

### Interactive Selection
```kotlin
ScatterChart(
    data = scatterData,
    onPointSelected = { setIndex, pointIndex, point ->
        // Handle selection
    }
)
```

## Configuration

### ScatterChartData
- `title`: Chart title
- `scatterSets`: List of scatter series
- `config`: General chart config
- `xAxisConfig`: X-axis settings
- `yAxisConfig`: Y-axis settings
- `zAxisConfig`: Z-axis for bubbles

### ScatterDataSet
- `label`: Series name
- `dataPoints`: Data array
- `pointColor`: Default point color
- `pointShape`: Shape type
- `pointSize`: Default size
- `showLine`: Connect points
- `showLabels`: Display labels
- `customColors`: Color array

### ScatterPoint
- `x`: X coordinate
- `y`: Y coordinate
- `z`: Z value (bubble size)
- `label`: Text label
- `color`: Individual color

## Examples

### Multi-Series Chart
```kotlin
ScatterChart(
    data = ScatterChartData(
        scatterSets = listOf(
            ScatterDataSet(
                label = "Series 1",
                dataPoints = data1,
                pointColor = Color.Blue,
                pointShape = PointShape.CIRCLE
            ),
            ScatterDataSet(
                label = "Series 2",
                dataPoints = data2,
                pointColor = Color.Red,
                pointShape = PointShape.TRIANGLE
            )
        )
    )
)
```

### Bubble Chart
```kotlin
val data = listOf(
    ScatterPoint(100f, 200f, z = 50f),
    ScatterPoint(120f, 150f, z = 200f),
    ScatterPoint(140f, 300f, z = 400f)
)

ScatterChart(
    data = ScatterChartData(
        scatterSets = listOf(
            ScatterDataSet(
                label = "Bubbles",
                dataPoints = data
            )
        ),
        zAxisConfig = ZAxisConfig(
            range = Pair(20f, 100f)
        )
    )
)
```

### Connected Scatter
```kotlin
ScatterDataSet(
    label = "Trend",
    dataPoints = data,
    pointShape = PointShape.DIAMOND,
    showLine = true,
    lineColor = Color.Blue
)
```

## Common Use Cases
- Correlation analysis
- Bubble charts (3-variable visualization)
- Trend analysis with connections
- Multi-dimensional data visualization
- Category comparison
- Scientific data plotting

## Performance Tips
1. Limit to 500 points per series
2. Use simpler shapes for large datasets
3. Disable labels for dense plots
4. Consider data sampling for real-time updates

## Related Components
- `LineChart` - For continuous trends
- `BarChart` - For categorical comparison
- `AreaChart` - For filled regions

---

**Last Updated:** November 17, 2025  
**Version:** 1.0.0

