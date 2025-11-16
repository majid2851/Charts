### Interactive LineChart Guide

## Overview
The `InteractiveLineChart` component allows users to tap/click on data points to highlight them with custom colors and sizes. When a point is selected, both the point and its connected line segments change appearance.

## Features

### Point Interaction
✅ **Click Detection** - Tap any point to select it  
✅ **Visual Feedback** - Selected points become larger  
✅ **Color Change** - Points and lines change color when selected  
✅ **Border Highlight** - Optional white border around active points  
✅ **Callback Support** - Get notified when points are selected  
✅ **Multi-line Support** - Works with multiple data series  

## PointInteractionConfig Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `enableInteraction` | Boolean | true | Enable/disable point interaction |
| `activePointRadius` | Float | 8f | Size of selected point |
| `activePointColor` | Color? | null | Color when point is selected (null = use line color) |
| `activeLineColor` | Color? | null | Color of line when point is selected |
| `activeLineWidth` | Float? | null | Width of line when point is selected |
| `showActivePointBorder` | Boolean | true | Show white border around active point |
| `activePointBorderColor` | Color | White | Color of active point border |
| `activePointBorderWidth` | Float | 2f | Width of active point border |

## Basic Usage

### 1. Simple Interactive Chart

```kotlin
InteractiveLineChart(
    data = LineChartData(
        title = "Interactive Sales Data",
        lines = listOf(
            LineDataSet(
                label = "Sales",
                dataPoints = listOf(
                    DataPoint(0f, 100f),
                    DataPoint(1f, 200f),
                    DataPoint(2f, 150f),
                    DataPoint(3f, 300f),
                    DataPoint(4f, 250f)
                ),
                lineColor = Color.Blue,
                interactionConfig = PointInteractionConfig(
                    activePointRadius = 10f,
                    activePointColor = Color.Red,
                    activeLineColor = Color.Red
                )
            )
        )
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

### 2. With Selection Callback

```kotlin
var selectedInfo by remember { mutableStateOf("Tap a point") }

InteractiveLineChart(
    data = lineChartData,
    onPointSelected = { lineIndex, pointIndex, dataPoint ->
        selectedInfo = "Line: $lineIndex, Point: $pointIndex, Value: ${dataPoint?.y}"
    }
)

Text(text = selectedInfo)
```

### 3. Custom Active Styling

```kotlin
LineDataSet(
    label = "Revenue",
    dataPoints = myDataPoints,
    lineColor = Color(0xFF8884d8),
    lineWidth = 2.5f,
    interactionConfig = PointInteractionConfig(
        enableInteraction = true,
        activePointRadius = 12f,
        activePointColor = Color(0xFFFF6B6B),
        activeLineColor = Color(0xFF4ECDC4),
        activeLineWidth = 4f,
        showActivePointBorder = true,
        activePointBorderColor = Color.White,
        activePointBorderWidth = 3f
    )
)
```

### 4. Multiple Lines with Different Active Colors

```kotlin
InteractiveLineChart(
    data = LineChartData(
        lines = listOf(
            LineDataSet(
                label = "Product A",
                dataPoints = dataA,
                lineColor = Color.Blue,
                interactionConfig = PointInteractionConfig(
                    activePointColor = Color.Red,
                    activeLineColor = Color.Red
                )
            ),
            LineDataSet(
                label = "Product B",
                dataPoints = dataB,
                lineColor = Color.Green,
                interactionConfig = PointInteractionConfig(
                    activePointColor = Color.Yellow,
                    activeLineColor = Color.Yellow
                )
            )
        )
    )
)
```

## Visual States

### Normal State
- Point: Regular size (`pointRadius`)
- Point Color: Same as `lineColor`
- Line: Regular width (`lineWidth`)
- Line Color: Default `lineColor`

### Active State (When Clicked)
- Point: Larger size (`activePointRadius`)
- Point Color: Changes to `activePointColor` (or `lineColor` if null)
- Point Border: White border appears (if `showActivePointBorder` = true)
- Line: Thicker width (`activeLineWidth`)
- Line Color: Changes to `activeLineColor`

## Click Detection

The component uses a **proximity threshold of 50 pixels**:
- User taps anywhere on the chart
- System finds the nearest point within 50px radius
- If found, that point becomes active
- Previous selection is cleared

## Callback Parameters

```kotlin
onPointSelected: (lineIndex: Int, pointIndex: Int, dataPoint: DataPoint?) -> Unit
```

- `lineIndex`: Index of the line series (0-based)
- `pointIndex`: Index of the point within that line (0-based)
- `dataPoint`: The actual DataPoint object (contains x, y, label)

## Using with Theme Values

```kotlin
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.AppColors

LineDataSet(
    label = "Sales",
    dataPoints = myData,
    lineColor = AppColors.RechartsBlue,
    lineWidth = Dimens.lineWidthEnhanced,
    interactionConfig = PointInteractionConfig(
        activePointRadius = Dimens.pointRadiusInteractive,
        activePointColor = AppColors.Red,
        activeLineColor = AppColors.Red,
        activeLineWidth = Dimens.lineWidthActive,
        activePointBorderWidth = Dimens.pointBorderWidth
    )
)
```

## Theme Dimension Values

Available in `Dimens.kt`:

```kotlin
val pointRadiusDefault: Float = 4f
val pointRadiusActive: Float = 8f
val pointRadiusInteractive: Float = 10f
val pointBorderWidth: Float = 2f
val lineWidthDefault: Float = 2f
val lineWidthEnhanced: Float = 2.5f
val lineWidthActive: Float = 3.5f
```

## Best Practices

### 1. Color Contrast
Choose active colors that contrast well with the base colors:
```kotlin
lineColor = Color.Blue
activePointColor = Color.Red  // Good contrast
```

### 2. Size Differences
Make active points significantly larger for clear feedback:
```kotlin
pointRadius = 4f
activePointRadius = 10f  // 2.5x larger
```

### 3. Border for Emphasis
Use borders for better visibility:
```kotlin
showActivePointBorder = true
activePointBorderColor = Color.White  // Stands out on any background
```

### 4. Consistent Styling
Keep interaction styling consistent across multiple lines:
```kotlin
val commonInteraction = PointInteractionConfig(
    activePointRadius = 10f,
    showActivePointBorder = true
)

LineDataSet(label = "A", ..., interactionConfig = commonInteraction)
LineDataSet(label = "B", ..., interactionConfig = commonInteraction)
```

## Examples

### Example 1: Recharts-Style Active Point
```kotlin
PointInteractionConfig(
    activePointRadius = 8f,
    activePointColor = null,  // Keep original color
    activeLineColor = null,   // Keep original color
    showActivePointBorder = true,
    activePointBorderColor = Color.White,
    activePointBorderWidth = 2f
)
```

### Example 2: High Contrast Highlight
```kotlin
PointInteractionConfig(
    activePointRadius = 12f,
    activePointColor = Color(0xFFFF6B6B),  // Bright red
    activeLineColor = Color(0xFF4ECDC4),    // Cyan
    activeLineWidth = 4f,
    showActivePointBorder = true
)
```

### Example 3: Subtle Highlight
```kotlin
PointInteractionConfig(
    activePointRadius = 6f,
    activePointColor = Color.Black,
    activeLineColor = Color.Black.copy(alpha = 0.5f),
    activeLineWidth = 3f,
    showActivePointBorder = false
)
```

## Integration

Works seamlessly with all existing features:
- ✅ CartesianGrid
- ✅ Reference Lines
- ✅ Curved Lines
- ✅ Filled Areas
- ✅ Dashed Lines
- ✅ Multiple Series
- ✅ Custom Point Shapes (Circle only for now)

## Performance

- ✅ Efficient click detection using distance calculation
- ✅ Minimal re-composition (only selected point changes)
- ✅ Threshold-based to prevent unnecessary updates
- ✅ Works smoothly with large datasets

## Complete Example

See `InteractiveLineChartExample.kt` for a full working example with:
- Multiple data series
- Custom active colors
- Selection callback
- Info display card
- Theme integration

