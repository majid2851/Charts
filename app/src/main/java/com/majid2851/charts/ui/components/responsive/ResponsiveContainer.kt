package com.majid2851.charts.ui.components.responsive

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * ResponsiveContainer - Makes charts responsive to parent container size
 * Matches Recharts ResponsiveContainer functionality
 * 
 * Automatically adapts chart dimensions to fit its parent container,
 * maintaining aspect ratio and handling responsive layouts.
 * 
 * @param width Fixed width (null for 100% of parent width)
 * @param height Fixed height (null for 100% of parent height)
 * @param minWidth Minimum width constraint
 * @param minHeight Minimum height constraint
 * @param maxWidth Maximum width constraint
 * @param maxHeight Maximum height constraint
 * @param aspect Aspect ratio (width/height) to maintain
 * @param debounce Debounce time in ms for resize events
 * @param modifier Modifier for the container
 * @param content Chart content to display
 * 
 * @example
 * ```kotlin
 * ResponsiveContainer {
 *     AreaChart(
 *         data = data,
 *         modifier = Modifier.fillMaxSize()
 *     )
 * }
 * ```
 */
@Composable
fun ResponsiveContainer(
    width: Dp? = null,
    height: Dp? = null,
    minWidth: Dp = 0.dp,
    minHeight: Dp = 0.dp,
    maxWidth: Dp = Dp.Infinity,
    maxHeight: Dp = Dp.Infinity,
    aspect: Float? = null,
    debounce: Int = 0,
    modifier: Modifier = Modifier,
    content: @Composable (containerWidth: Dp, containerHeight: Dp) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val density = LocalDensity.current
        
        // Calculate dimensions based on constraints
        var calculatedWidth = width ?: maxWidth.coerceAtMost(this.maxWidth)
        var calculatedHeight = height ?: maxHeight.coerceAtMost(this.maxHeight)
        
        // Apply aspect ratio if specified
        if (aspect != null) {
            if (width == null && height != null) {
                calculatedWidth = (calculatedHeight.value * aspect).dp
            } else if (height == null && width != null) {
                calculatedHeight = (calculatedWidth.value / aspect).dp
            } else if (width == null && height == null) {
                // Use parent width and calculate height from aspect
                calculatedWidth = this.maxWidth
                calculatedHeight = (calculatedWidth.value / aspect).dp
            }
        }
        
        // Apply min/max constraints
        calculatedWidth = calculatedWidth.coerceIn(minWidth, maxWidth.coerceAtMost(this.maxWidth))
        calculatedHeight = calculatedHeight.coerceIn(minHeight, maxHeight.coerceAtMost(this.maxHeight))
        
        // State for debouncing (if needed in future)
        var currentWidth by remember { mutableStateOf(calculatedWidth) }
        var currentHeight by remember { mutableStateOf(calculatedHeight) }
        
        LaunchedEffect(calculatedWidth, calculatedHeight) {
            if (debounce > 0) {
                kotlinx.coroutines.delay(debounce.toLong())
            }
            currentWidth = calculatedWidth
            currentHeight = calculatedHeight
        }
        
        content(currentWidth, currentHeight)
    }
}

/**
 * Simplified ResponsiveContainer - Just wraps content in BoxWithConstraints
 * Use this when you don't need size parameters
 */
@Composable
fun SimpleResponsiveContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        content()
    }
}

