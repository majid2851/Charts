# Interactive Line Chart

This document explains how to use the interactive features of the LineChart component.

## Overview

The LineChart component now supports interactive point selection, allowing users to tap on data points to trigger callbacks and visual feedback.

## Features

### 1. Point Selection
- **Tap Detection**: Detects taps on data points within a 40-pixel radius
- **Nearest Point Selection**: Automatically selects the closest point to the tap location
- **Visual Feedback**: Selected points and their lines can change appearance

### 2. Visual Customization

Each `LineDataSet` includes an `interactionConfig` parameter of type `PointInteractionConfig`:

```kotlin
data class PointInteractionConfig(
    val enableInteraction: Boolean = true,
    val activePointRadius: Float = 8f,
    val activePointColor: Color? = null,
    val activeLineColor: Color? = null,
    val activeLineWidth: Float? = null,
    val showActivePointBorder: Boolean = true,
    val activePointBorderColor: Color = Color.White,
    val activePointBorderWidth: Float = 2f
)
```

### 3. Callback Support

The `LineChart` composable now accepts an optional `onPointSelected` callback:

```kotlin
onPointSelected: ((lineIndex: Int, pointIndex: Int, point: DataPoint?) -> Unit)? = null
```

## Usage Example

### Basic Interactive Chart

```kotlin
@Composable
fun MyInteractiveChart() {
    var selectedInfo by remember { mutableStateOf("Tap a point") }
    
    LineChart(
        data = LineChartData(
            title = "Sales Data",
            lines = listOf(
                LineDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 1000f, label = "Jan"),
                        DataPoint(x = 1f, y = 1500f, label = "Feb"),
                        DataPoint(x = 2f, y = 1200f, label = "Mar")
                    ),
                    lineColor = Color.Blue,
                    interactionConfig = PointInteractionConfig(
                        enableInteraction = true,
                        activePointRadius = 12f,
                        activePointColor = Color.Blue,
                        activeLineWidth = 4f
                    )
                )
            )
        ),
        onPointSelected = { lineIndex, pointIndex, dataPoint ->
            dataPoint?.let {
                selectedInfo = "Selected: ${it.label} - Value: ${it.y}"
            }
        }
    )
    
    Text(text = selectedInfo)
}
```

### Advanced Configuration

```kotlin
LineDataSet(
    label = "Product A",
    dataPoints = myDataPoints,
    lineColor = AppColors.Primary,
    lineWidth = 2f,
    pointRadius = 5f,
    interactionConfig = PointInteractionConfig(
        enableInteraction = true,
        
        // Active point appearance
        activePointRadius = 14f,
        activePointColor = AppColors.Primary,
        
        // Active line appearance
        activeLineColor = AppColors.Primary.copy(alpha = 1f),
        activeLineWidth = 5f,
        
        // Point border
        showActivePointBorder = true,
        activePointBorderColor = Color.White,
        activePointBorderWidth = 3f
    )
)
```

## Configuration Options

### PointInteractionConfig Parameters

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `enableInteraction` | Boolean | true | Enable/disable interaction for this dataset |
| `activePointRadius` | Float | 8f | Radius of point when selected |
| `activePointColor` | Color? | null | Color of point when selected (null = use lineColor) |
| `activeLineColor` | Color? | null | Color of line when any point is selected (null = use lineColor) |
| `activeLineWidth` | Float? | null | Width of line when any point is selected (null = use lineWidth) |
| `showActivePointBorder` | Boolean | true | Show border around active point |
| `activePointBorderColor` | Color | Color.White | Border color for active point |
| `activePointBorderWidth` | Float | 2f | Border width for active point |

## Callback Parameters

The `onPointSelected` callback provides three parameters:

1. **lineIndex** (Int): The index of the selected line in the `lines` list
2. **pointIndex** (Int): The index of the selected point in the `dataPoints` list
3. **point** (DataPoint?): The actual data point object (can be null)

## Implementation Details

### Touch Detection
- Uses `detectTapGestures` from Compose's pointer input
- Calculates distance from tap location to each point
- Selects the nearest point within 40-pixel radius
- Updates visual state immediately

### State Management
- Uses `remember` and `mutableStateOf` for selection state
- Recomposition triggers when selection changes
- All visual updates are automatic

### Type Safety
- Fixed Size/IntSize type mismatch using `.toSize()` extension
- Proper coordinate mapping from data space to screen space

## Best Practices

### 1. Provide Visual Feedback
Always configure `activePointRadius` larger than `pointRadius` to provide clear visual feedback:

```kotlin
interactionConfig = PointInteractionConfig(
    activePointRadius = lineDataSet.pointRadius * 2
)
```

### 2. Use Contrasting Colors
Make selected points stand out:

```kotlin
interactionConfig = PointInteractionConfig(
    activePointColor = Color.Red,
    activeLineColor = lineColor.copy(alpha = 1f)
)
```

### 3. Handle Null Points
Always check for null in the callback:

```kotlin
onPointSelected = { lineIndex, pointIndex, point ->
    point?.let { 
        // Handle selection
    }
}
```

### 4. Disable for Dense Data
For charts with many data points, consider disabling interaction:

```kotlin
interactionConfig = PointInteractionConfig(
    enableInteraction = false
)
```

## Examples

See `InteractiveLineChartExample.kt` for a complete working example demonstrating:
- Multiple interactive datasets
- Custom active point appearance
- Callback handling with info display
- Integration with other Compose components

## Limitations

1. **Touch Radius**: Fixed at 40 pixels - not currently configurable
2. **Single Selection**: Only one point can be selected at a time
3. **No Hover**: Touch/tap only - no hover state for desktop
4. **No Multitouch**: Single finger interaction only

## Future Enhancements

Potential improvements for future versions:
- Configurable touch radius
- Multi-point selection
- Hover support for desktop
- Gesture support (zoom, pan)
- Tooltip display
- Point dragging
- Selection callbacks for line segments
















