# Chart Zoom & Pan Feature Guide

## Overview

The chart library now supports **zoom** and **pan** functionality for all charts, allowing users to interactively explore data by zooming in/out and panning across the chart.

## Features

### 🔍 Zoom Capabilities
- **Pinch-to-Zoom**: Use two fingers to zoom in/out on touch devices
- **Double-Tap Zoom**: Double-tap to zoom in, double-tap again to reset
- **Zoom Controls**: Optional UI buttons for manual zoom control
- **Configurable Zoom Range**: Set minimum and maximum zoom levels (default: 0.5x to 5x)

### 🖐️ Pan Capabilities
- **Drag to Pan**: Drag the chart to move around when zoomed in
- **Smooth Panning**: Natural scrolling behavior with momentum

### 🎮 Interactive Features
- **Point Selection**: Tap points to select them (works with zoom)
- **Zoom State Management**: Programmatic control over zoom level
- **Reset Functionality**: Quick reset to original view

## Quick Start

### Basic Zoom-Enabled Chart

```kotlin
LineChart(
    data = LineChartData(
        title = "Sales Data",
        lines = listOf(/* your data */),
        config = ChartConfig(
            enableZoom = true,        // Enable pinch-to-zoom
            enablePan = true,         // Enable panning
            showZoomControls = false  // Hide zoom buttons
        )
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
)
```

### Chart with Zoom Control Buttons

```kotlin
LineChart(
    data = LineChartData(
        title = "Revenue Data",
        lines = listOf(/* your data */),
        config = ChartConfig(
            enableZoom = true,
            enablePan = true,
            showZoomControls = true  // Show zoom buttons
        )
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
)
```

### Custom Zoom Range

```kotlin
LineChart(
    data = LineChartData(
        title = "Custom Zoom",
        lines = listOf(/* your data */),
        config = ChartConfig(
            enableZoom = true,
            enablePan = true,
            minZoom = 0.5f,   // Can zoom out to 50%
            maxZoom = 10f     // Can zoom in to 1000%
        )
    ),
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
)
```

## Advanced Usage

### Using Custom Zoom State

You can manage zoom state externally for more control:

```kotlin
@Composable
fun MyChart() {
    val zoomState = rememberZoomState(
        initialZoom = 1f,
        minZoom = 0.5f,
        maxZoom = 5f
    )
    
    Column {
        // Your custom controls
        Row {
            Button(onClick = { zoomState.zoomIn() }) {
                Text("Zoom In")
            }
            Button(onClick = { zoomState.zoomOut() }) {
                Text("Zoom Out")
            }
            Button(onClick = { zoomState.reset() }) {
                Text("Reset")
            }
        }
        
        // Chart with external zoom state
        LineChart(
            data = myChartData,
            zoomState = zoomState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        
        // Display current zoom level
        Text("Zoom: ${(zoomState.zoom * 100).toInt()}%")
    }
}
```

### Programmatic Zoom Control

```kotlin
val zoomState = rememberZoomState()

// Zoom in
zoomState.zoomIn()

// Zoom out
zoomState.zoomOut()

// Set specific zoom level
zoomState.updateZoom(2.5f)

// Zoom to a specific point
zoomState.updateZoom(2f, focusPoint = Offset(100f, 100f))

// Reset to default
zoomState.reset()

// Get current zoom level
val currentZoom = zoomState.zoom

// Get current pan offset
val currentPan = zoomState.panOffset
```

## Configuration Options

### ChartConfig Parameters

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `enableZoom` | Boolean | `false` | Enable/disable zoom functionality |
| `enablePan` | Boolean | `false` | Enable/disable panning |
| `showZoomControls` | Boolean | `false` | Show/hide zoom control buttons |
| `minZoom` | Float | `0.5f` | Minimum zoom level (0.5 = 50%) |
| `maxZoom` | Float | `5f` | Maximum zoom level (5 = 500%) |

### ZoomState Properties

| Property | Type | Description |
|----------|------|-------------|
| `zoom` | Float | Current zoom level (1.0 = 100%) |
| `panOffset` | Offset | Current pan offset in pixels |
| `minZoom` | Float | Minimum allowed zoom level |
| `maxZoom` | Float | Maximum allowed zoom level |

### ZoomState Methods

| Method | Parameters | Description |
|--------|------------|-------------|
| `updateZoom()` | `newZoom: Float, focusPoint: Offset` | Update zoom level |
| `updatePan()` | `delta: Offset` | Update pan offset |
| `zoomIn()` | `focusPoint: Offset` | Zoom in by 20% |
| `zoomOut()` | `focusPoint: Offset` | Zoom out by 20% |
| `reset()` | - | Reset to default state |

## Gesture Controls

### Touch Gestures

1. **Pinch-to-Zoom**
   - Place two fingers on the chart
   - Pinch together to zoom out
   - Spread apart to zoom in

2. **Pan/Drag**
   - Touch and drag to move around
   - Works best when zoomed in

3. **Double-Tap**
   - Double-tap to zoom in
   - Double-tap again to reset zoom

4. **Single Tap**
   - Tap on data points to select them
   - Works independently of zoom

### Zoom Control Buttons

When `showZoomControls = true`, you get:

- **+ Button**: Zoom in by 20%
- **− Button**: Zoom out by 20%
- **⟲ Button**: Reset zoom and pan
- **Zoom %**: Current zoom level display

## Best Practices

### 1. Choose Appropriate Zoom Range

```kotlin
// For detailed data exploration
ChartConfig(
    minZoom = 1f,    // Don't allow zoom out
    maxZoom = 10f    // Allow deep zoom in
)

// For overview + detail
ChartConfig(
    minZoom = 0.5f,  // Allow zoom out for overview
    maxZoom = 5f     // Moderate zoom in
)
```

### 2. Enable Pan with Zoom

Always enable pan when zoom is enabled:

```kotlin
ChartConfig(
    enableZoom = true,
    enablePan = true  // Essential for navigation when zoomed
)
```

### 3. Use Zoom Controls for Precision

For desktop or when precise control is needed:

```kotlin
ChartConfig(
    enableZoom = true,
    enablePan = true,
    showZoomControls = true  // Easier for mouse users
)
```

### 4. Combine with Point Selection

Zoom works seamlessly with point selection:

```kotlin
LineChart(
    data = chartData.copy(
        config = ChartConfig(
            enableZoom = true,
            enablePan = true,
            isInteractive = true  // Enable point selection
        )
    ),
    onPointSelected = { lineIndex, pointIndex, point ->
        // Handle point selection
        println("Selected: $point")
    }
)
```

## Examples

### Example 1: Financial Chart with Zoom

```kotlin
@Composable
fun FinancialChart() {
    LineChart(
        data = LineChartData(
            title = "Stock Price History",
            lines = listOf(
                LineDataSet(
                    label = "AAPL",
                    dataPoints = stockData,
                    lineColor = Color.Blue
                )
            ),
            config = ChartConfig(
                enableZoom = true,
                enablePan = true,
                showZoomControls = true,
                minZoom = 0.5f,
                maxZoom = 10f,
                showGrid = true,
                showAxis = true
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

### Example 2: Multi-Line Chart with Shared Zoom

```kotlin
@Composable
fun MultiLineChartWithSharedZoom() {
    val sharedZoomState = rememberZoomState()
    
    Column {
        LineChart(
            data = chartData1,
            zoomState = sharedZoomState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        LineChart(
            data = chartData2,
            zoomState = sharedZoomState,  // Same zoom state
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}
```

### Example 3: Chart with Custom Zoom UI

```kotlin
@Composable
fun ChartWithCustomZoomUI() {
    val zoomState = rememberZoomState()
    
    Column {
        // Custom zoom controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { zoomState.zoomOut() }) {
                Icon(Icons.Default.ZoomOut, "Zoom Out")
            }
            
            Slider(
                value = zoomState.zoom,
                onValueChange = { zoomState.updateZoom(it) },
                valueRange = 0.5f..5f,
                modifier = Modifier.weight(1f)
            )
            
            IconButton(onClick = { zoomState.zoomIn() }) {
                Icon(Icons.Default.ZoomIn, "Zoom In")
            }
            
            IconButton(onClick = { zoomState.reset() }) {
                Icon(Icons.Default.Refresh, "Reset")
            }
        }
        
        Text("Zoom: ${(zoomState.zoom * 100).toInt()}%")
        
        LineChart(
            data = chartData,
            zoomState = zoomState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}
```

## Performance Considerations

1. **Large Datasets**: Zoom and pan work efficiently with large datasets
2. **Smooth Rendering**: All transformations are calculated in real-time
3. **Memory Efficient**: Only visible data points are rendered
4. **Gesture Handling**: Optimized for smooth touch interactions

## Troubleshooting

### Zoom Not Working

- Ensure `enableZoom = true` in ChartConfig
- Check that the chart has sufficient size
- Verify touch events aren't being intercepted

### Pan Not Working

- Ensure `enablePan = true` in ChartConfig
- Pan only works when zoomed in
- Check for gesture conflicts with parent scrollable containers

### Zoom Controls Not Visible

- Set `showZoomControls = true` in ChartConfig
- Ensure chart has sufficient padding
- Check that controls aren't hidden behind other UI elements

## Future Enhancements

- [ ] Zoom to specific data range
- [ ] Animated zoom transitions
- [ ] Mouse wheel zoom support
- [ ] Keyboard shortcuts for zoom/pan
- [ ] Zoom history (undo/redo)
- [ ] Minimap overview

## API Reference

See the complete API documentation in the source files:
- `ZoomState.kt` - Zoom state management
- `ZoomGestures.kt` - Gesture detection
- `ZoomControls.kt` - UI controls
- `ChartBounds.kt` - Coordinate transformations

---

**Note**: Zoom and pan functionality is available for all chart types in the library. The examples above use LineChart, but the same principles apply to other chart types.

