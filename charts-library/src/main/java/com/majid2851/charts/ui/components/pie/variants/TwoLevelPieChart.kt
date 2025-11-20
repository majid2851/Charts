package com.majid2851.charts.ui.components.pie.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.PieChartConfig
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.domain.model.TwoLevelPieChartData
import kotlin.math.cos
import kotlin.math.sin

/**
 * Two-Level (Nested) Pie Chart
 * Shows two concentric pie charts - inner and outer
 */
@Composable
fun TwoLevelPieChart(
    data: TwoLevelPieChartData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp)
    ) {
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
            Canvas(modifier = Modifier.fillMaxSize()) {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val maxRadius = minOf(size.width, size.height) / 2 * 0.9f
                
                // Draw inner pie
                drawPieLevel(
                    slices = data.innerSlices,
                    centerX = centerX,
                    centerY = centerY,
                    maxRadius = maxRadius,
                    config = data.innerConfig
                )
                
                // Draw outer pie
                drawPieLevel(
                    slices = data.outerSlices,
                    centerX = centerX,
                    centerY = centerY,
                    maxRadius = maxRadius,
                    config = data.outerConfig
                )
            }
        }
    }
}

private fun DrawScope.drawPieLevel(
    slices: List<PieSlice>,
    centerX: Float,
    centerY: Float,
    maxRadius: Float,
    config: PieChartConfig
) {
    val total = slices.sumOf { it.value.toDouble() }.toFloat()
    if (total <= 0) return
    
    val outerRadius = maxRadius * config.outerRadius
    val innerRadius = maxRadius * config.innerRadius
    
    var currentAngle = config.startAngle
    
    slices.forEach { slice ->
        val sweepAngle = (slice.value / total) * 360f
        
        val path = Path().apply {
            val startRad = Math.toRadians(currentAngle.toDouble())
            val endRad = Math.toRadians((currentAngle + sweepAngle).toDouble())
            
            if (innerRadius > 0) {
                // Donut segment
                val outerStartX = centerX + outerRadius * cos(startRad).toFloat()
                val outerStartY = centerY + outerRadius * sin(startRad).toFloat()
                
                moveTo(outerStartX, outerStartY)
                
                arcTo(
                    rect = androidx.compose.ui.geometry.Rect(
                        left = centerX - outerRadius,
                        top = centerY - outerRadius,
                        right = centerX + outerRadius,
                        bottom = centerY + outerRadius
                    ),
                    startAngleDegrees = currentAngle,
                    sweepAngleDegrees = sweepAngle,
                    forceMoveTo = false
                )
                
                val innerEndX = centerX + innerRadius * cos(endRad).toFloat()
                val innerEndY = centerY + innerRadius * sin(endRad).toFloat()
                lineTo(innerEndX, innerEndY)
                
                arcTo(
                    rect = androidx.compose.ui.geometry.Rect(
                        left = centerX - innerRadius,
                        top = centerY - innerRadius,
                        right = centerX + innerRadius,
                        bottom = centerY + innerRadius
                    ),
                    startAngleDegrees = currentAngle + sweepAngle,
                    sweepAngleDegrees = -sweepAngle,
                    forceMoveTo = false
                )
                
                close()
            } else {
                // Full pie segment
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
                    startAngleDegrees = currentAngle,
                    sweepAngleDegrees = sweepAngle,
                    forceMoveTo = false
                )
                
                close()
            }
        }
        
        drawPath(path, slice.color)
        currentAngle += sweepAngle
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoLevelPieChartPreview() {
    TwoLevelPieChart(
        data = TwoLevelPieChartData(
            title = "Two Level Pie Chart",
            innerSlices = listOf(
                PieSlice("Group A", 400f, Color(0xFF8884d8)),
                PieSlice("Group B", 300f, Color(0xFF83a6ed)),
                PieSlice("Group C", 300f, Color(0xFF8dd1e1)),
                PieSlice("Group D", 200f, Color(0xFF82ca9d))
            ),
            outerSlices = listOf(
                PieSlice("A1", 100f, Color(0xFF8884d8)),
                PieSlice("A2", 300f, Color(0xFF83a6ed)),
                PieSlice("B1", 100f, Color(0xFF8dd1e1)),
                PieSlice("B2", 80f, Color(0xFF82ca9d)),
                PieSlice("B3", 40f, Color(0xFFa4de6c)),
                PieSlice("B4", 30f, Color(0xFFd0ed57)),
                PieSlice("B5", 50f, Color(0xFFffc658))
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}





