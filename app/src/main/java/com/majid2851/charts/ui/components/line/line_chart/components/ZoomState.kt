package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

/**
 * State holder for chart zoom and pan functionality
 */
class ZoomState(
    initialZoom: Float = 1f,
    val minZoom: Float = 0.5f,
    val maxZoom: Float = 5f
) {
    var zoom by mutableStateOf(initialZoom)
        private set
    
    var panOffset by mutableStateOf(Offset.Zero)
        private set
    
    /**
     * Updates the zoom level, constrained between minZoom and maxZoom
     */
    fun updateZoom(newZoom: Float, focusPoint: Offset = Offset.Zero) {
        val oldZoom = zoom
        zoom = newZoom.coerceIn(minZoom, maxZoom)
        
        // Adjust pan offset to zoom towards the focus point
        if (focusPoint != Offset.Zero) {
            val zoomChange = zoom / oldZoom
            panOffset = Offset(
                x = focusPoint.x + (panOffset.x - focusPoint.x) * zoomChange,
                y = focusPoint.y + (panOffset.y - focusPoint.y) * zoomChange
            )
        }
    }
    
    /**
     * Updates the pan offset
     */
    fun updatePan(delta: Offset) {
        panOffset += delta
    }
    
    /**
     * Resets zoom and pan to initial state
     */
    fun reset() {
        zoom = 1f
        panOffset = Offset.Zero
    }
    
    /**
     * Zooms in by a fixed amount
     */
    fun zoomIn(focusPoint: Offset = Offset.Zero) {
        updateZoom(zoom * 1.2f, focusPoint)
    }
    
    /**
     * Zooms out by a fixed amount
     */
    fun zoomOut(focusPoint: Offset = Offset.Zero) {
        updateZoom(zoom / 1.2f, focusPoint)
    }
}

/**
 * Creates and remembers a ZoomState
 */
@Composable
fun rememberZoomState(
    initialZoom: Float = 1f,
    minZoom: Float = 0.5f,
    maxZoom: Float = 5f
): ZoomState {
    return remember {
        ZoomState(
            initialZoom = initialZoom,
            minZoom = minZoom,
            maxZoom = maxZoom
        )
    }
}

/**
 * Configuration for zoom and pan behavior
 */
data class ZoomConfig(
    val enabled: Boolean = true,
    val enablePinchZoom: Boolean = true,
    val enablePan: Boolean = true,
    val enableDoubleTapZoom: Boolean = true,
    val showZoomControls: Boolean = false,
    val minZoom: Float = 0.5f,
    val maxZoom: Float = 5f
)

