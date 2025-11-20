package com.majid2851.charts.ui.components.line.line_chart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Zoom control buttons for manual zoom in/out
 */
@Composable
fun ZoomControls(
    zoomState: ZoomState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White.copy(alpha = 0.9f),
    iconColor: Color = Color.Black
) {
    Column(
        modifier = modifier
            .background(backgroundColor.copy(alpha = 0.7f), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Zoom In Button
        ZoomButton(
            text = "+",
            onClick = { zoomState.zoomIn() },
            enabled = zoomState.zoom < zoomState.maxZoom,
            iconColor = iconColor
        )
        
        // Current Zoom Level
        Text(
            text = "${(zoomState.zoom * 100).toInt()}%",
            fontSize = 10.sp,
            color = iconColor
        )
        
        // Zoom Out Button
        ZoomButton(
            text = "−",
            onClick = { zoomState.zoomOut() },
            enabled = zoomState.zoom > zoomState.minZoom,
            iconColor = iconColor
        )
        
        // Reset Button
        ZoomButton(
            text = "⟲",
            onClick = { zoomState.reset() },
            enabled = zoomState.zoom != 1f || zoomState.panOffset != androidx.compose.ui.geometry.Offset.Zero,
            iconColor = iconColor
        )
    }
}

@Composable
private fun ZoomButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(
                if (enabled) Color.White else Color.Gray.copy(alpha = 0.3f),
                shape = CircleShape
            )
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = if (enabled) iconColor else iconColor.copy(alpha = 0.3f)
        )
    }
}

