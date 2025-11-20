package com.majid2851.charts.ui.components.pie

import  androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.PieChartConfig
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieLabelPosition
import com.majid2851.charts.domain.model.PieSlice
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
 
@OptIn(ExperimentalTextApi::class)
@Composable
fun PieChart(
    data: PieChartData,
    modifier: Modifier = Modifier,
    onSliceClick: ((PieSlice, Int) -> Unit)? = null
) {
    var selectedSliceIndex by remember { mutableStateOf<Int?>(null) }
    val textMeasurer = rememberTextMeasurer()
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {
        // Title
        data.title?.let {
            Text(
                text = it,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        if (data.config.isInteractive) {
                            detectTapGestures { offset ->
                                val tappedIndex = findTappedSlice(
                                    offset = offset,
                                    slices = data.slices,
                                    config = data.config,
                                    canvasSize = Size(
                                        size.width.toFloat(),
                                        size.height.toFloat()
                                    )
                                )
                                selectedSliceIndex = tappedIndex
                                tappedIndex?.let { index ->
                                    onSliceClick?.invoke(data.slices[index], index)
                                }
                            }
                        }
                    }
            ) {
                val total = data.slices.sumOf { it.value.toDouble() }.toFloat()
                if (total <= 0) return@Canvas
                
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = minOf(size.width, size.height) / 2 * 0.8f
                
                val outerRadius = radius * data.config.outerRadius
                val innerRadius = radius * data.config.innerRadius
                
                var currentAngle = data.config.startAngle
                
                data.slices.forEachIndexed { index, slice ->
                    val sweepAngle = (slice.value / total) * (data.config.endAngle - data.config.startAngle)
                    
                    val isSelected = selectedSliceIndex == index
                    val offset = if (isSelected && data.config.activeSliceOffset > 0) {
                        val midAngle = currentAngle + sweepAngle / 2
                        val offsetDistance = data.config.activeSliceOffset
                        Offset(
                            x = offsetDistance * cos(Math.toRadians(midAngle.toDouble())).toFloat(),
                            y = offsetDistance * sin(Math.toRadians(midAngle.toDouble())).toFloat()
                        )
                    } else {
                        Offset.Zero
                    }
                    drawPieSlice(
                        centerX = centerX + offset.x,
                        centerY = centerY + offset.y,
                        innerRadius = innerRadius,
                        outerRadius = outerRadius,
                        startAngle = currentAngle,
                        sweepAngle = sweepAngle - data.config.paddingAngle,
                        color = slice.color,
                        cornerRadius = data.config.cornerRadius
                    )

                    if (data.config.showLabels && data.config.labelPosition != PieLabelPosition.NONE) {
                        drawSliceLabel(
                            textMeasurer = textMeasurer,
                            slice = slice,
                            centerX = centerX,
                            centerY = centerY,
                            innerRadius = innerRadius,
                            outerRadius = outerRadius,
                            startAngle = currentAngle,
                            sweepAngle = sweepAngle,
                            total = total,
                            config = data.config
                        )
                    }
                    
                    currentAngle += sweepAngle
                }
                
                // Draw center label for donut charts
                if (data.config.centerLabel != null && data.config.innerRadius > 0) {
                    drawCenterLabel(
                        textMeasurer = textMeasurer,
                        label = data.config.centerLabel,
                        centerX = centerX,
                        centerY = centerY,
                        config = data.config
                    )
                }
            }
        }
        
        // Legend
        if (data.config.showLegend) {
            Spacer(modifier = Modifier.height(16.dp))
            PieChartLegend(slices = data.slices)
        }
    }
}

private fun DrawScope.drawPieSlice(
    centerX: Float,
    centerY: Float,
    innerRadius: Float,
    outerRadius: Float,
    startAngle: Float,
    sweepAngle: Float,
    color: Color,
    cornerRadius: Float
) {
    val path = Path().apply {
        val startRad = Math.toRadians(startAngle.toDouble())
        val endRad = Math.toRadians((startAngle + sweepAngle).toDouble())
        
        if (innerRadius > 0) {
            // Donut slice
            val outerStartX = centerX + outerRadius * cos(startRad).toFloat()
            val outerStartY = centerY + outerRadius * sin(startRad).toFloat()
            
            moveTo(outerStartX, outerStartY)
            
            // Outer arc
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    left = centerX - outerRadius,
                    top = centerY - outerRadius,
                    right = centerX + outerRadius,
                    bottom = centerY + outerRadius
                ),
                startAngleDegrees = startAngle,
                sweepAngleDegrees = sweepAngle,
                forceMoveTo = false
            )
            
            // Line to inner arc
            val innerEndX = centerX + innerRadius * cos(endRad).toFloat()
            val innerEndY = centerY + innerRadius * sin(endRad).toFloat()
            lineTo(innerEndX, innerEndY)
            
            // Inner arc (reverse direction)
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    left = centerX - innerRadius,
                    top = centerY - innerRadius,
                    right = centerX + innerRadius,
                    bottom = centerY + innerRadius
                ),
                startAngleDegrees = startAngle + sweepAngle,
                sweepAngleDegrees = -sweepAngle,
                forceMoveTo = false
            )
            
            close()
        } else {
            // Full pie slice
            moveTo(centerX, centerY)
            
            val startX = centerX + outerRadius * cos(startRad).toFloat()
            val startY = centerY + outerRadius * sin(startRad).toFloat()
            lineTo(startX, startY)
            
            arcTo(
                rect = androidx.compose.ui.geometry.Rect(
                    left = centerX - outerRadius,
                    top = centerY - outerRadius,
                    right = centerX + outerRadius,
                    bottom = centerY + outerRadius
                ),
                startAngleDegrees = startAngle,
                sweepAngleDegrees = sweepAngle,
                forceMoveTo = false
            )
            
            close()
        }
    }
    
    drawPath(path, color)
    
    // Optional: draw border
    if (cornerRadius > 0) {
        drawPath(path, color = Color.White, style = Stroke(width = 2f))
    }
}

/**
 * Draws label for a slice
 */
@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawSliceLabel(
    textMeasurer: androidx.compose.ui.text.TextMeasurer,
    slice: PieSlice,
    centerX: Float,
    centerY: Float,
    innerRadius: Float,
    outerRadius: Float,
    startAngle: Float,
    sweepAngle: Float,
    total: Float,
    config: PieChartConfig
) {
    val midAngle = startAngle + sweepAngle / 2
    val midRad = Math.toRadians(midAngle.toDouble())
    
    val labelText = if (config.showPercentage) {
        "${((slice.value / total) * 100).toInt()}%"
    } else {
        slice.label
    }
    
    val textLayoutResult = textMeasurer.measure(
        text = labelText,
        style = TextStyle(
            color = config.labelColor,
            fontSize = config.labelTextSize.sp
        )
    )
    
    when (config.labelPosition) {
        PieLabelPosition.INSIDE -> {
            val labelRadius = innerRadius + (outerRadius - innerRadius) / 2
            val x = centerX + labelRadius * cos(midRad).toFloat() - textLayoutResult.size.width / 2
            val y = centerY + labelRadius * sin(midRad).toFloat() - textLayoutResult.size.height / 2
            
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(x, y)
            )
        }
        PieLabelPosition.OUTSIDE -> {
            val labelRadius = outerRadius + config.labelLineLength
            val x = centerX + labelRadius * cos(midRad).toFloat()
            val y = centerY + labelRadius * sin(midRad).toFloat()
            
            // Draw line from slice to label
            if (config.showLabelLine) {
                val lineStartX = centerX + outerRadius * cos(midRad).toFloat()
                val lineStartY = centerY + outerRadius * sin(midRad).toFloat()
                
                drawLine(
                    color = slice.color,
                    start = Offset(lineStartX, lineStartY),
                    end = Offset(x, y),
                    strokeWidth = 1f
                )
            }
            
            val textX = if (cos(midRad) >= 0) x else x - textLayoutResult.size.width
            val textY = y - textLayoutResult.size.height / 2
            
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(textX, textY)
            )
        }
        else -> {}
    }
}

/**
 * Draws center label for donut charts
 */
@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawCenterLabel(
    textMeasurer: androidx.compose.ui.text.TextMeasurer,
    label: String,
    centerX: Float,
    centerY: Float,
    config: PieChartConfig
) {
    val textLayoutResult = textMeasurer.measure(
        text = label,
        style = TextStyle(
            color = config.centerLabelColor,
            fontSize = config.centerLabelTextSize.sp,
            fontWeight = FontWeight.Bold
        )
    )
    
    drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(
            x = centerX - textLayoutResult.size.width / 2,
            y = centerY - textLayoutResult.size.height / 2
        )
    )
}

/**
 * Finds which slice was tapped
 */
private fun findTappedSlice(
    offset: Offset,
    slices: List<PieSlice>,
    config: PieChartConfig,
    canvasSize: Size
): Int? {
    val total = slices.sumOf { it.value.toDouble() }.toFloat()
    if (total <= 0) return null
    
    val centerX = canvasSize.width / 2
    val centerY = canvasSize.height / 2
    val radius = minOf(canvasSize.width, canvasSize.height) / 2 * 0.8f
    
    val outerRadius = radius * config.outerRadius
    val innerRadius = radius * config.innerRadius
    
    // Calculate distance from center
    val dx = offset.x - centerX
    val dy = offset.y - centerY
    val distance = kotlin.math.sqrt(dx * dx + dy * dy)
    
    // Check if tap is within the pie ring
    if (distance < innerRadius || distance > outerRadius) return null
    
    // Calculate angle of tap
    var tapAngle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
    if (tapAngle < 0) tapAngle += 360
    
    // Adjust for start angle
    tapAngle = (tapAngle - config.startAngle + 360) % 360
    
    // Find which slice
    var currentAngle = 0f
    slices.forEachIndexed { index, slice ->
        val sweepAngle = (slice.value / total) * (config.endAngle - config.startAngle)
        if (tapAngle >= currentAngle && tapAngle < currentAngle + sweepAngle) {
            return index
        }
        currentAngle += sweepAngle
    }
    
    return null
}

/**
 * Legend for pie chart
 */
@Composable
private fun PieChartLegend(slices: List<PieSlice>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        slices.forEach { slice ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(slice.color)
                )
                Text(
                    text = "${slice.label}: ${slice.value}",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PieChartPreview() {
    PieChart(
        data = PieChartData(
            title = "Sample Pie Chart",
            slices = listOf(
                PieSlice("Group A", 400f, Color(0xFF0088FE)),
                PieSlice("Group B", 300f, Color(0xFF00C49F)),
                PieSlice("Group C", 300f, Color(0xFFFFBB28)),
                PieSlice("Group D", 200f, Color(0xFFFF8042))
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
