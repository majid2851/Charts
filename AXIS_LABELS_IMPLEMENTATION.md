# Axis Labels Implementation

## Problem
The X-axis and Y-axis were not showing labels (numbers/text) even though `showLabels = true` was configured. The axes lines were drawn, but no labels were visible.

## Root Cause
The axis label drawing functionality was **not implemented**. While the `AxisConfig` had properties like `showLabels`, `labelCount`, and `labelTextSize`, there were no functions to actually draw the text on the canvas.

## Solution Implemented

### 1. Added Required Imports
```kotlin
import android.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
```

### 2. Created Y-Axis Label Drawing Function
```kotlin
private fun DrawScope.drawYAxisLabels(
    bounds: Bounds,
    labelCount: Int,
    labelColor: Color,
    labelTextSize: Float,
    padding: Float
)
```

**Features:**
- Draws Y-axis labels on the left side of the chart
- Formats large numbers (≥1000) as "k" notation (e.g., "2.5k")
- Right-aligns text for better appearance
- Evenly spaces labels based on `labelCount`

### 3. Created X-Axis Label Drawing Function
```kotlin
private fun DrawScope.drawXAxisLabels(
    dataPoints: List<DataPoint?>,
    bounds: Bounds,
    labelColor: Color,
    labelTextSize: Float,
    padding: Float
)
```

**Features:**
- Draws X-axis labels below the chart
- Uses the `label` property from `DataPoint` (e.g., "Page A", "Page B")
- Center-aligns text under each data point
- Only draws labels for points that have a label defined

### 4. Integrated into Main Drawing Logic
Updated the chart drawing code to call these functions when `showAxis` and `showLabels` are enabled:

```kotlin
if (data.config.showAxis) {
    drawAxes(bounds, data.xAxisConfig.axisColor, padding)
    
    if (data.yAxisConfig.showLabels) {
        drawYAxisLabels(...)
    }
    
    if (data.xAxisConfig.showLabels && data.lines.isNotEmpty()) {
        drawXAxisLabels(...)
    }
}
```

## How It Works

### Y-Axis Labels
1. Calculates evenly spaced values between `bounds.minY` and `bounds.maxY`
2. Maps each value to screen coordinates
3. Formats the number (with "k" suffix for thousands)
4. Draws text to the left of the Y-axis line

### X-Axis Labels
1. Iterates through data points in the first line dataset
2. For each point with a label, calculates screen X position
3. Draws text centered below the X-axis line

## Label Formatting

### Y-Axis Number Formatting
- **< 1000**: Shows as integer (e.g., "250")
- **≥ 1000**: Shows with "k" notation (e.g., "2.5k")

### X-Axis Label Display
- Uses the `label` property from `DataPoint`
- Examples: "Page A", "Jan", "Monday", etc.

## Configuration Options

All label appearance can be controlled via `AxisConfig`:

```kotlin
AxisConfig(
    showLabels = true,           // Toggle labels on/off
    labelCount = 5,              // Number of Y-axis labels
    axisColor = Color.Gray,      // Color of labels and axis
    labelTextSize = 12f          // Font size of labels
)
```

## Positioning

- **Y-Axis Labels**: Drawn at `padding - 10f` (10px left of Y-axis)
- **X-Axis Labels**: Drawn at `size.height - padding + labelTextSize + 10f` (below X-axis)

The 40f default padding provides sufficient space for labels.

## Existing Charts

All existing line charts will automatically display labels because:
1. `showLabels = true` is the default in `AxisConfig`
2. All chart examples already have proper `AxisConfig` configured
3. Data points include the `label` property

## Examples

### Simple Configuration
```kotlin
LineChartData(
    xAxisConfig = AxisConfig(
        showLabels = true,
        labelCount = 7,
        axisColor = Color.Gray,
        labelTextSize = 12f
    ),
    yAxisConfig = AxisConfig(
        showLabels = true,
        labelCount = 5,
        axisColor = Color.Gray,
        labelTextSize = 12f
    )
)
```

### With Custom Styling
```kotlin
AxisConfig(
    showLabels = true,
    labelCount = 10,
    axisColor = AppColors.Primary,
    labelTextSize = 14f
)
```

## Result

✅ Y-axis now shows numeric values (e.g., "0", "2.5k", "5k", "7.5k", "10k")  
✅ X-axis now shows data point labels (e.g., "Page A", "Page B", etc.)  
✅ Labels respect the `showLabels` configuration  
✅ Label styling follows the `AxisConfig` settings  
✅ Works with all existing line chart examples  







