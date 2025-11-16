# Zoom Feature - Migration Guide

## 📋 Overview

This guide helps you add zoom and pan functionality to your existing charts.

## ✅ Backward Compatibility

**Good news!** The zoom feature is **100% backward compatible**. Your existing charts will continue to work without any changes.

- Zoom is **disabled by default**
- No breaking changes to existing APIs
- All existing features work as before

## 🔄 Migration Steps

### Step 1: Update Your ChartConfig

**Before:**
```kotlin
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true
        )
    )
)
```

**After:**
```kotlin
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            enableZoom = true,  // ✅ Add this
            enablePan = true    // ✅ Add this
        )
    )
)
```

### Step 2: Optional - Add Zoom Controls

```kotlin
config = ChartConfig(
    // ... existing config
    enableZoom = true,
    enablePan = true,
    showZoomControls = true  // ✅ Optional: Show buttons
)
```

### Step 3: Optional - Customize Zoom Range

```kotlin
config = ChartConfig(
    // ... existing config
    enableZoom = true,
    enablePan = true,
    minZoom = 0.5f,  // ✅ Optional: Min zoom level
    maxZoom = 5f     // ✅ Optional: Max zoom level
)
```

## 📝 Migration Examples

### Example 1: Simple Chart

**Before:**
```kotlin
@Composable
fun SalesChart() {
    LineChart(
        data = LineChartData(
            title = "Sales 2024",
            lines = salesData
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

**After (with zoom):**
```kotlin
@Composable
fun SalesChart() {
    LineChart(
        data = LineChartData(
            title = "Sales 2024",
            lines = salesData,
            config = ChartConfig(
                enableZoom = true,
                enablePan = true
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Example 2: Chart with Custom Config

**Before:**
```kotlin
@Composable
fun CustomChart() {
    LineChart(
        data = LineChartData(
            title = "Revenue",
            lines = revenueData,
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true,
                backgroundColor = Color.White,
                chartPadding = 16.dp
            )
        )
    )
}
```

**After (with zoom):**
```kotlin
@Composable
fun CustomChart() {
    LineChart(
        data = LineChartData(
            title = "Revenue",
            lines = revenueData,
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true,
                backgroundColor = Color.White,
                chartPadding = 16.dp,
                // ✅ Add zoom configuration
                enableZoom = true,
                enablePan = true,
                showZoomControls = true
            )
        )
    )
}
```

### Example 3: Interactive Chart

**Before:**
```kotlin
@Composable
fun InteractiveChart() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    
    LineChart(
        data = LineChartData(
            title = "Interactive Chart",
            lines = chartData,
            config = ChartConfig(
                isInteractive = true
            )
        ),
        onPointSelected = { _, _, point ->
            selectedPoint = point
        }
    )
}
```

**After (with zoom):**
```kotlin
@Composable
fun InteractiveChart() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    
    LineChart(
        data = LineChartData(
            title = "Interactive Chart",
            lines = chartData,
            config = ChartConfig(
                isInteractive = true,
                // ✅ Add zoom - works with point selection!
                enableZoom = true,
                enablePan = true
            )
        ),
        onPointSelected = { _, _, point ->
            selectedPoint = point
        }
    )
}
```

## 🎯 Common Migration Patterns

### Pattern 1: Enable Zoom for All Charts

If you want to enable zoom for all charts in your app, create a helper function:

```kotlin
fun defaultChartConfig(
    enableZoom: Boolean = true
): ChartConfig {
    return ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        enableZoom = enableZoom,
        enablePan = enableZoom,
        showZoomControls = false
    )
}

// Usage
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = defaultChartConfig()
    )
)
```

### Pattern 2: Conditional Zoom

Enable zoom only for certain charts:

```kotlin
@Composable
fun SmartChart(
    data: LineChartData,
    enableZoom: Boolean = false
) {
    LineChart(
        data = data.copy(
            config = data.config.copy(
                enableZoom = enableZoom,
                enablePan = enableZoom
            )
        )
    )
}
```

### Pattern 3: Shared Zoom State

Share zoom state between multiple charts:

```kotlin
@Composable
fun DashboardWithSharedZoom() {
    val sharedZoomState = rememberZoomState()
    
    Column {
        LineChart(
            data = chartData1,
            zoomState = sharedZoomState
        )
        
        LineChart(
            data = chartData2,
            zoomState = sharedZoomState
        )
    }
}
```

## 🔍 Configuration Changes

### New ChartConfig Parameters

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `enableZoom` | Boolean | `false` | Enable zoom functionality |
| `enablePan` | Boolean | `false` | Enable pan functionality |
| `showZoomControls` | Boolean | `false` | Show zoom control buttons |
| `minZoom` | Float | `0.5f` | Minimum zoom level |
| `maxZoom` | Float | `5f` | Maximum zoom level |

### New LineChart Parameter

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `zoomState` | ZoomState? | `null` | Optional external zoom state |

## ⚠️ Important Notes

### 1. Pan Requires Zoom

For best user experience, always enable pan when zoom is enabled:

```kotlin
// ✅ Good
config = ChartConfig(
    enableZoom = true,
    enablePan = true
)

// ❌ Not recommended
config = ChartConfig(
    enableZoom = true,
    enablePan = false  // Users can't navigate when zoomed
)
```

### 2. Gesture Conflicts

If your chart is inside a scrollable container, you may need to handle gesture conflicts:

```kotlin
ScrollableColumn {
    LineChart(
        data = chartData,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)  // Fixed height recommended
    )
}
```

### 3. Performance

Zoom works efficiently with large datasets. No special optimization needed.

## 🧪 Testing Your Migration

After adding zoom, test these interactions:

1. **Pinch Gesture**
   - [ ] Pinch to zoom in
   - [ ] Pinch to zoom out
   - [ ] Zoom centers on pinch point

2. **Pan Gesture**
   - [ ] Drag to pan when zoomed
   - [ ] Pan is smooth

3. **Double-Tap**
   - [ ] Double-tap zooms in
   - [ ] Double-tap again resets

4. **Zoom Controls** (if enabled)
   - [ ] + button zooms in
   - [ ] − button zooms out
   - [ ] ⟲ button resets
   - [ ] Zoom percentage displays correctly

5. **Existing Features**
   - [ ] Point selection still works
   - [ ] Grid renders correctly
   - [ ] Axes and labels display properly
   - [ ] Legend shows correctly

## 🐛 Troubleshooting

### Issue: Zoom not working

**Solution:**
```kotlin
// Make sure both are enabled
config = ChartConfig(
    enableZoom = true,  // ✅
    enablePan = true    // ✅
)
```

### Issue: Can't pan

**Solution:**
```kotlin
// Pan only works when zoomed in
// Try zooming in first, then pan
```

### Issue: Zoom controls not visible

**Solution:**
```kotlin
config = ChartConfig(
    enableZoom = true,
    enablePan = true,
    showZoomControls = true  // ✅ Make sure this is true
)
```

### Issue: Zoom too sensitive

**Solution:**
```kotlin
config = ChartConfig(
    enableZoom = true,
    enablePan = true,
    minZoom = 0.8f,  // Reduce range
    maxZoom = 3f     // Reduce range
)
```

## 📊 Migration Checklist

Use this checklist for each chart you migrate:

- [ ] Add `enableZoom = true` to ChartConfig
- [ ] Add `enablePan = true` to ChartConfig
- [ ] Test pinch-to-zoom gesture
- [ ] Test drag-to-pan gesture
- [ ] Test double-tap zoom
- [ ] Verify point selection still works (if used)
- [ ] Check grid and axes render correctly
- [ ] Verify legends display properly
- [ ] Test on actual device (not just emulator)
- [ ] Consider adding zoom controls for desktop users

## 🎓 Learning Path

1. **Start Simple**: Add zoom to one chart first
2. **Test Thoroughly**: Try all gestures
3. **Customize**: Adjust zoom range and controls
4. **Expand**: Add zoom to other charts
5. **Advanced**: Explore custom zoom state management

## 📚 Additional Resources

- **Quick Start**: `ZOOM_QUICK_START.md`
- **Full Guide**: `ZOOM_FEATURE_GUIDE.md`
- **Examples**: `ZoomableLineChartExample.kt`
- **Summary**: `ZOOM_IMPLEMENTATION_SUMMARY.md`

## 💡 Best Practices

1. **Enable zoom for data-rich charts**
   - Charts with many data points benefit most

2. **Use zoom controls for desktop**
   - Better for mouse/trackpad users

3. **Set appropriate zoom limits**
   - Prevent excessive zoom in/out

4. **Test on real devices**
   - Gestures feel different on actual hardware

5. **Consider user expectations**
   - Financial/scientific charts often expect zoom

## 🎉 You're Done!

Your charts now support zoom and pan. Users can:
- 📱 Pinch to zoom in/out
- 👆 Drag to pan around
- 👆👆 Double-tap to zoom/reset
- 🎮 Use buttons (if enabled)

**Happy zooming!** 🚀

---

**Need Help?** Check the documentation files or review the example implementations.

