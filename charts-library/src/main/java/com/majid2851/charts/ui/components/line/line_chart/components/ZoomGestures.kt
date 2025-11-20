package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import kotlinx.coroutines.coroutineScope

/**
 * Detects zoom and pan gestures with tap support
 */
suspend fun PointerInputScope.detectZoomAndPanGestures(
    zoomState: ZoomState,
    zoomConfig: ZoomConfig,
    onTap: ((Offset) -> Unit)? = null,
    onDoubleTap: ((Offset) -> Unit)? = null
) {
    coroutineScope {
        // Handle pinch-to-zoom and pan
        if (zoomConfig.enablePinchZoom || zoomConfig.enablePan) {
            detectTransformGestures { centroid, pan, zoom, _ ->
                if (zoomConfig.enablePinchZoom && zoom != 1f) {
                    zoomState.updateZoom(zoomState.zoom * zoom, centroid)
                }
                if (zoomConfig.enablePan) {
                    zoomState.updatePan(pan)
                }
            }
        }
    }
}

/**
 * Detects tap gestures separately (for point selection)
 */
suspend fun PointerInputScope.detectChartTapGestures(
    onTap: ((Offset) -> Unit)? = null,
    onDoubleTap: ((Offset) -> Unit)? = null,
    onLongPress: ((Offset) -> Unit)? = null
) {
    detectTapGestures(
        onTap = onTap,
        onDoubleTap = onDoubleTap,
        onLongPress = onLongPress
    )
}

/**
 * Detects drag gestures for panning
 */
suspend fun PointerInputScope.detectPanGestures(
    zoomState: ZoomState,
    enabled: Boolean = true
) {
    if (!enabled) return
    
    detectDragGestures { change, dragAmount ->
        change.consume()
        zoomState.updatePan(dragAmount)
    }
}

