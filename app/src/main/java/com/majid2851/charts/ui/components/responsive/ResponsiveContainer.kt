package com.majid2851.charts.ui.components.responsive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Preview(showBackground = true, widthDp = 400, heightDp = 300)
@Composable
private fun ResponsiveContainerPreview() {
    ResponsiveContainer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) { width, height ->
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF8884d8).copy(alpha = 0.1f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Responsive Container",
                        fontSize = 20.sp,
                        color = Color(0xFF8884d8)
                    )
                    Text(
                        text = "Width: ${width.value.toInt()}dp",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Height: ${height.value.toInt()}dp",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Adapts to parent size automatically",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300, name = "Square Aspect")
@Composable
private fun ResponsiveContainerAspectPreview() {
    ResponsiveContainer(
        aspect = 1f,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) { width, height ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF82ca9d).copy(alpha = 0.2f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1:1 Aspect Ratio",
                    fontSize = 18.sp,
                    color = Color(0xFF82ca9d)
                )
                Text(
                    text = "${width.value.toInt()} x ${height.value.toInt()} dp",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200, name = "Simple Container")
@Composable
private fun SimpleResponsiveContainerPreview() {
    SimpleResponsiveContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFffc658).copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Simple Responsive\nContainer",
                fontSize = 16.sp,
                color = Color(0xFFffc658),
                textAlign = TextAlign.Center
            )
        }
    }
}

