# Zoom & Pan - Quick Start Guide

## 🚀 Enable Zoom in 3 Steps

### Step 1: Update ChartConfig

```kotlin
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = ChartConfig(
            enableZoom = true,  // ✅ Enable zoom
            enablePan = true    // ✅ Enable pan
        )
    )
)
```

### Step 2: Add Zoom Controls (Optional)

```kotlin
config = ChartConfig(
    enableZoom = true,
    enablePan = true,
    showZoomControls = true  // ✅ Show zoom buttons
)
```

### Step 3: Customize Zoom Range (Optional)

```kotlin
config = ChartConfig(
    enableZoom = true,
    enablePan = true,
    minZoom = 0.5f,  // Minimum 50%
    maxZoom = 10f    // Maximum 1000%
)
```

## 📱 Gesture Controls

| Gesture | Action |
|---------|--------|
| **Pinch** | Zoom in/out |
| **Drag** | Pan around (when zoomed) |
| **Double-Tap** | Zoom in / Reset |
| **Tap** | Select point |

## 🎮 Zoom Control Buttons

When `showZoomControls = true`:

- **+** : Zoom in 20%
- **−** : Zoom out 20%
- **⟲** : Reset view
- **%** : Current zoom level

## 💡 Common Use Cases

### 1. Basic Zoom (Most Common)

```kotlin
LineChart(
    data = LineChartData(
        title = "Sales Data",
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
```

### 2. With Zoom Buttons

```kotlin
config = ChartConfig(
    enableZoom = true,
    enablePan = true,
    showZoomControls = true
)
```

### 3. Custom Zoom Control

```kotlin
val zoomState = rememberZoomState()

Column {
    Button(onClick = { zoomState.zoomIn() }) {
        Text("Zoom In")
    }
    
    LineChart(
        data = myData,
        zoomState = zoomState
    )
}
```

## ⚙️ Configuration Options

```kotlin
ChartConfig(
    enableZoom = true,          // Enable zoom
    enablePan = true,           // Enable pan
    showZoomControls = false,   // Show buttons
    minZoom = 0.5f,            // Min zoom (50%)
    maxZoom = 5f               // Max zoom (500%)
)
```

## 🔧 Programmatic Control

```kotlin
val zoomState = rememberZoomState()

// Zoom in
zoomState.zoomIn()

// Zoom out
zoomState.zoomOut()

// Reset
zoomState.reset()

// Set specific zoom
zoomState.updateZoom(2.5f)

// Get current zoom
val currentZoom = zoomState.zoom
```

## ✅ Best Practices

1. **Always enable pan with zoom**
   ```kotlin
   enableZoom = true
   enablePan = true  // ✅ Essential
   ```

2. **Use zoom controls for desktop**
   ```kotlin
   showZoomControls = true  // Better for mouse users
   ```

3. **Set appropriate zoom range**
   ```kotlin
   minZoom = 0.5f  // Overview
   maxZoom = 5f    // Detail
   ```

## 🎯 Complete Example

```kotlin
@Composable
fun MyZoomableChart() {
    LineChart(
        data = LineChartData(
            title = "Revenue 2024",
            lines = listOf(
                LineDataSet(
                    label = "Sales",
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
                enableZoom = true,
                enablePan = true,
                showZoomControls = true,
                minZoom = 0.5f,
                maxZoom = 5f,
                showGrid = true,
                showAxis = true,
                showLegend = true
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## 🐛 Troubleshooting

| Problem | Solution |
|---------|----------|
| Zoom not working | Set `enableZoom = true` |
| Can't pan | Set `enablePan = true` |
| No zoom buttons | Set `showZoomControls = true` |
| Zoom too sensitive | Adjust `minZoom` and `maxZoom` |

## 📚 More Information

See `ZOOM_FEATURE_GUIDE.md` for detailed documentation.

---

**That's it!** Your charts now support zoom and pan! 🎉

