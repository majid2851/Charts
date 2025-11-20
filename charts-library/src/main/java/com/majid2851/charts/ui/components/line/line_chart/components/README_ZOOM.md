# Zoom & Pan Components

This directory contains all components related to zoom and pan functionality for charts.

## 📁 Files

### Core Components

- **`ZoomState.kt`** - State management for zoom and pan
- **`ZoomGestures.kt`** - Gesture detection utilities
- **`ZoomControls.kt`** - UI controls for zoom
- **`ChartBounds.kt`** - Coordinate transformation with zoom support

## 🚀 Quick Usage

```kotlin
// Basic zoom-enabled chart
LineChart(
    data = LineChartData(
        title = "My Chart",
        lines = myData,
        config = ChartConfig(
            enableZoom = true,
            enablePan = true
        )
    )
)
```

## 📖 Documentation

- **Quick Start**: See `ZOOM_QUICK_START.md` in project root
- **Full Guide**: See `ZOOM_FEATURE_GUIDE.md` in project root
- **Migration**: See `ZOOM_MIGRATION_GUIDE.md` in project root

## 🔧 Components Overview

### ZoomState

Manages zoom level and pan offset.

```kotlin
val zoomState = rememberZoomState(
    initialZoom = 1f,
    minZoom = 0.5f,
    maxZoom = 5f
)

// Control zoom
zoomState.zoomIn()
zoomState.zoomOut()
zoomState.reset()
```

### ZoomGestures

Handles gesture detection.

```kotlin
pointerInput(Unit) {
    detectZoomAndPanGestures(
        zoomState = zoomState,
        zoomConfig = zoomConfig,
        onTap = { /* handle tap */ }
    )
}
```

### ZoomControls

Displays zoom control buttons.

```kotlin
ZoomControls(
    zoomState = zoomState,
    modifier = Modifier.align(Alignment.TopEnd)
)
```

### ChartBounds

Transforms coordinates based on zoom.

```kotlin
val transformedBounds = bounds.applyZoomAndPan(
    zoomState = zoomState,
    canvasWidth = size.width,
    canvasHeight = size.height,
    padding = padding
)
```

## 🎯 Features

- ✅ Pinch-to-zoom
- ✅ Drag-to-pan
- ✅ Double-tap zoom/reset
- ✅ Zoom control buttons
- ✅ Configurable zoom range
- ✅ Smooth gestures
- ✅ Works with all chart features

## 📝 Example

See `ZoomableLineChartExample.kt` for complete examples.

---

For more information, see the documentation files in the project root.

