# Drag to Show Vertical Line Feature

## 🎯 Overview

The chart now supports **continuous vertical line tracking** - users can drag their finger anywhere on the chart (not just on exact points) and the vertical line will automatically snap to the nearest data point on the X-axis.

## ✨ What Changed

### Before
- Had to tap **exactly** on a data point to show the vertical line
- Required precise targeting
- Difficult to explore data quickly

### After
- **Drag anywhere** on the chart to show the vertical line
- Automatically **snaps to nearest X-axis position**
- Works regardless of vertical (Y) position
- **Smooth tracking** as you move your finger
- Much easier to explore data

## 🎮 How It Works

### User Interaction

1. **Touch and Hold**: Touch anywhere on the chart
2. **Drag Horizontally**: Move your finger left/right
3. **Vertical Line Appears**: Automatically shows at nearest data point
4. **Continuous Updates**: Line follows your finger as you drag
5. **Release**: Line stays visible (or disappears, configurable)

### Technical Implementation

The feature uses two new functions:

#### 1. `findNearestPointByX()`
Finds the nearest data point based on **X-axis position only**, ignoring Y-axis distance.

```kotlin
fun findNearestPointByX(
    offset: Offset,
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    canvasSize: Size
): SelectedPoint?
```

**Key Features:**
- Only considers X-axis distance
- Ignores vertical (Y) position
- Returns the closest point on any line
- Works within chart boundaries

#### 2. `findAllPointsAtNearestX()`
Finds **all points** at the nearest X position across all lines.

```kotlin
fun findAllPointsAtNearestX(
    offset: Offset,
    lines: List<LineDataSet>,
    bounds: Bounds,
    padding: Float,
    canvasSize: Size
): List<SelectedPoint>
```

**Use Case:** Show tooltips for multiple lines at the same X coordinate.

## 📱 Gesture Support

### Drag Gesture
```kotlin
detectDragGestures(
    onDragStart = { offset ->
        // Show vertical line at nearest point
        val nearestPoint = findNearestPointByX(...)
        selectedPoint = nearestPoint
    },
    onDrag = { change, _ ->
        // Update vertical line as user drags
        val nearestPoint = findNearestPointByX(change.position, ...)
        selectedPoint = nearestPoint
    },
    onDragEnd = {
        // Keep or clear the selection
    }
)
```

### Tap Gesture (Still Works)
```kotlin
detectTapGestures(
    onTap = { offset ->
        // Quick selection with single tap
        val clickedPoint = findNearestPointByX(...)
        selectedPoint = clickedPoint
    }
)
```

## 🎨 Visual Behavior

### What You See

1. **Vertical Line**: Appears at the selected X position
2. **Highlighted Points**: All points at that X position are highlighted
3. **Smooth Movement**: Line smoothly follows your finger
4. **Snap to Grid**: Automatically aligns with data points

### Example Scenarios

#### Scenario 1: Single Line Chart
- Drag finger across chart
- Vertical line shows at nearest data point
- Selected point is highlighted

#### Scenario 2: Multi-Line Chart
- Drag finger across chart
- Vertical line shows at nearest X position
- **All points** at that X are highlighted (one per line)
- Can see multiple values at once

## 💡 Benefits

### For Users
✅ **Easier Exploration** - No need for precise tapping  
✅ **Faster Navigation** - Quickly scan through data  
✅ **Better UX** - More intuitive interaction  
✅ **Mobile-Friendly** - Works great on touch screens  
✅ **Accessible** - Easier for users with motor difficulties  

### For Developers
✅ **Automatic** - Works out of the box  
✅ **No Configuration** - Enabled by default with `isInteractive = true`  
✅ **Backward Compatible** - Doesn't break existing code  
✅ **Performant** - Efficient X-axis only calculation  

## 🔧 Configuration

The feature is automatically enabled when:

```kotlin
ChartConfig(
    isInteractive = true  // ✅ This enables the feature
)
```

### Optional: Clear Selection After Drag

To clear the selection when drag ends:

```kotlin
// In LineChart.kt, modify onDragEnd:
onDragEnd = {
    selectedPoint = null  // Clear selection
}
```

### Optional: Disable Drag (Keep Only Tap)

If you want to keep the old behavior (tap only):

```kotlin
// Remove the detectDragGestures block
// Keep only detectTapGestures
```

## 📊 Use Cases

### 1. Financial Charts
- Explore stock prices over time
- Quickly check values at specific dates
- Compare multiple stocks at same time point

### 2. Analytics Dashboards
- Scan through metrics
- Compare different KPIs
- Identify trends and patterns

### 3. Scientific Data
- Examine experimental results
- Compare multiple data series
- Identify correlations

### 4. Sales Reports
- Review sales over time
- Compare different products
- Analyze seasonal trends

## 🎯 Examples

### Basic Usage

```kotlin
LineChart(
    data = LineChartData(
        title = "Sales Data",
        lines = salesData,
        config = ChartConfig(
            isInteractive = true  // ✅ Enables drag-to-show
        )
    ),
    onPointSelected = { lineIndex, pointIndex, point ->
        // Called continuously as user drags
        println("Selected: ${point?.label} = ${point?.y}")
    }
)
```

### With Tooltip

```kotlin
var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }

Column {
    LineChart(
        data = chartData,
        onPointSelected = { _, _, point ->
            selectedPoint = point
        }
    )
    
    // Show tooltip
    selectedPoint?.let { point ->
        Card {
            Text("${point.label}: ${point.y}")
        }
    }
}
```

### Multi-Line Comparison

```kotlin
var selectedPoints by remember { mutableStateOf<List<DataPoint>>(emptyList()) }

Column {
    LineChart(
        data = multiLineChartData,
        onPointSelected = { lineIndex, pointIndex, point ->
            // Collect all points at this X position
            selectedPoints = findAllPointsAtSameX(point)
        }
    )
    
    // Show all values
    selectedPoints.forEach { point ->
        Text("${point.label}: ${point.y}")
    }
}
```

## 🔄 Compatibility

### Works With
✅ Zoom and Pan  
✅ Point Selection  
✅ Multiple Lines  
✅ Grid Lines  
✅ Axis Labels  
✅ Reference Lines  
✅ Legends  

### Gesture Priority
1. **Zoom Gestures** (if enabled) - Two-finger pinch
2. **Pan Gestures** (if enabled) - Single-finger drag when zoomed
3. **Drag-to-Show** - Single-finger drag (when not zoomed or pan disabled)
4. **Tap Gestures** - Quick tap

## 🐛 Troubleshooting

### Issue: Drag not working

**Solution:**
```kotlin
config = ChartConfig(
    isInteractive = true  // ✅ Make sure this is true
)
```

### Issue: Conflicts with pan gesture

**Solution:**
The drag-to-show works independently. When zoom/pan is enabled:
- Two-finger pinch = Zoom
- One-finger drag (when zoomed) = Pan
- One-finger drag (not zoomed) = Show vertical line

### Issue: Vertical line not showing

**Check:**
1. Is `isInteractive = true`?
2. Is `showAxis = true` or custom drawing enabled?
3. Are there data points in the chart?

### Issue: Selection jumps around

**Cause:** Multiple points at similar X positions

**Solution:** This is expected behavior - it snaps to the nearest point

## 📈 Performance

- **Efficient**: Only calculates X-axis distance
- **Fast**: O(n) complexity where n = number of points
- **Smooth**: No lag during drag
- **Optimized**: Works well with large datasets

## 🎓 Best Practices

1. **Enable for Data-Rich Charts**
   - Charts with many data points benefit most
   - Makes exploration much easier

2. **Combine with Tooltips**
   - Show data values as user drags
   - Enhance user understanding

3. **Consider Multi-Line Scenarios**
   - Use `findAllPointsAtNearestX()` for multiple values
   - Show comparison tooltips

4. **Mobile-First Design**
   - This feature is perfect for touch screens
   - Much better UX than precise tapping

5. **Keep Selection Visible**
   - Don't clear selection immediately on drag end
   - Let users see the last selected point

## 🚀 Future Enhancements

Potential improvements:

- [ ] Configurable snap threshold
- [ ] Smooth animation between points
- [ ] Haptic feedback on point selection
- [ ] Customizable vertical line style
- [ ] Show X-axis value label
- [ ] Magnetic snapping to points

## 📝 Summary

The drag-to-show vertical line feature makes your charts **much more interactive and user-friendly**. Users can now:

- 👆 **Drag anywhere** to explore data
- 🎯 **Automatically snap** to nearest points
- 📊 **See values** without precise tapping
- 🚀 **Navigate quickly** through data

**It just works!** No configuration needed - enabled automatically with `isInteractive = true`.

---

**Implementation Date**: November 16, 2025  
**Status**: ✅ Complete  
**Files Modified**: `ChartInteraction.kt`, `LineChart.kt`  
**Backward Compatible**: ✅ Yes

