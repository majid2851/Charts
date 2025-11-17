# Area Chart Component

## Overview
A comprehensive Area Chart implementation for Android Compose, matching the functionality of Recharts Area Charts.

## Quick Start

### Basic Usage
```kotlin
@Composable
fun MyAreaChart() {
    val data = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, 2000f, "Page C"),
        DataPoint(3f, 2780f, "Page D")
    )

    AreaChart(
        data = AreaChartData(
            title = "Simple Area Chart",
            areas = listOf(
                AreaDataSet(
                    label = "UV",
                    dataPoints = data,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.6f)
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
area/
├── AreaChart.kt                    # Main chart component
├── README.md                       # This file
└── variants/
    ├── SimpleAreaChart.kt          # Basic area chart
    ├── StackedAreaChart.kt         # Stacked areas
    ├── PercentAreaChart.kt         # 100% stacked (percentage)
    ├── AreaChartConnectNulls.kt    # Handle missing data
    ├── TinyAreaChart.kt            # Minimal sparkline
    ├── AreaChartFillByValue.kt     # Gradient by value
    ├── CardinalAreaChart.kt        # Curve comparison
    └── SynchronizedAreaChart.kt    # Synchronized charts
```

## Variants

### 1. Simple Area Chart
- Single data series
- Smooth curves
- Basic area fill

### 2. Stacked Area Chart
- Multiple series stacked
- Cumulative values
- Different colors per series

### 3. Percent Area Chart
- 100% stacked
- Shows percentage distribution
- Normalized values

### 4. Area Chart Connect Nulls
- Handles missing data
- Side-by-side comparison
- Gap vs interpolation

### 5. Tiny Area Chart
- Minimal visualization
- No axes or grid
- Perfect for dashboards

### 6. Area Chart Fill By Value
- Dynamic gradient
- Positive/negative coloring
- Profit/loss visualization

### 7. Cardinal Area Chart
- Curve type comparison
- Linear vs smooth
- Interpolation demonstration

### 8. Synchronized Area Charts
- Multiple linked charts
- Shared interaction state
- Coordinated selection

## Features

### Stacking Modes
```kotlin
enum class AreaStackingMode {
    NONE,      // Regular overlapping areas
    STACKED,   // Cumulative stacking
    PERCENTAGE // Normalized to 100%
}
```

### Interactive Selection
```kotlin
AreaChart(
    data = areaChartData,
    onPointSelected = { areaIndex, pointIndex, point ->
        // Handle selection
    }
)
```

### Gradient Fills
```kotlin
val gradient = Brush.verticalGradient(
    colors = listOf(
        Color.Blue.copy(alpha = 0.8f),
        Color.Blue.copy(alpha = 0.2f)
    )
)

AreaDataSet(
    label = "Data",
    dataPoints = data,
    fillBrush = gradient
)
```

### Null Handling
```kotlin
AreaChartData(
    areas = areas,
    connectNulls = true  // Connect across missing data
)
```

## Configuration Options

### AreaChartData
- `title`: Chart title
- `areas`: List of area series
- `config`: General chart configuration
- `xAxisConfig`: X-axis settings
- `yAxisConfig`: Y-axis settings
- `stackingMode`: How to stack areas
- `connectNulls`: Handle missing data

### AreaDataSet
- `label`: Series name
- `dataPoints`: Data array
- `lineColor`: Line color
- `fillColor`: Fill color
- `fillBrush`: Gradient (optional)
- `lineWidth`: Line thickness
- `isCurved`: Smooth vs linear
- `fillOpacity`: Fill transparency
- `stackId`: Stacking group

### ChartConfig
- `showGrid`: Display grid
- `showAxis`: Display axes
- `showLegend`: Display legend
- `backgroundColor`: Chart background
- `chartPadding`: Padding around chart
- `isInteractive`: Enable touch interaction
- `cartesianGrid`: Grid configuration

## Examples

### Multi-Series Chart
```kotlin
AreaChart(
    data = AreaChartData(
        areas = listOf(
            AreaDataSet(
                label = "Series 1",
                dataPoints = data1,
                lineColor = Color.Blue,
                fillColor = Color.Blue.copy(alpha = 0.5f)
            ),
            AreaDataSet(
                label = "Series 2",
                dataPoints = data2,
                lineColor = Color.Red,
                fillColor = Color.Red.copy(alpha = 0.5f)
            )
        )
    )
)
```

### Stacked Chart
```kotlin
AreaChart(
    data = AreaChartData(
        areas = listOf(
            AreaDataSet(label = "A", dataPoints = data1, stackId = "1"),
            AreaDataSet(label = "B", dataPoints = data2, stackId = "1"),
            AreaDataSet(label = "C", dataPoints = data3, stackId = "1")
        ),
        stackingMode = AreaStackingMode.STACKED
    )
)
```

### Percentage Chart
```kotlin
AreaChart(
    data = AreaChartData(
        areas = listOf(
            AreaDataSet(label = "A", dataPoints = data1, stackId = "1"),
            AreaDataSet(label = "B", dataPoints = data2, stackId = "1")
        ),
        stackingMode = AreaStackingMode.PERCENTAGE
    )
)
```

## See Also
- `AREACHART_IMPLEMENTATION_GUIDE.md` - Comprehensive guide
- `AreaChartExamplesScreen.kt` - Live examples
- `AreaChartData.kt` - Data models

## Testing
Each variant includes a `@Preview` for visual testing:
```kotlin
@Preview(showBackground = true)
@Composable
private fun SimpleAreaChartPreview() {
    SimpleAreaChart(modifier = Modifier.fillMaxWidth().height(400.dp))
}
```

## Performance Tips
1. Limit to 100-200 points per series for smooth performance
2. Use `isCurved = false` for very large datasets
3. Disable grid for sparkline charts
4. Consider data sampling for real-time updates

## Common Use Cases
- Time series visualization
- Trend analysis
- Proportional comparison (percentage mode)
- Multi-metric dashboards
- Financial charts (profit/loss)
- Analytics dashboards

## Related Components
- `LineChart` - For line-only visualization
- `BarChart` - For discrete comparisons
- `ScatterChart` - For correlation analysis

---

**Last Updated:** November 17, 2025  
**Version:** 1.0.0

