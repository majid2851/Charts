package com.majid2851.charts.ui.components.radialbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Dimens
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * RadialBarChart - Concentric circular bars for hierarchical data visualization
 * Matches Recharts RadialBarChart functionality
 */
@Composable
fun RadialBarChart(
    data: RadialBarChartData,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {
        data.title?.let {
            Text(
                text = it,
                fontSize = FontSizes.titleLarge,
                modifier = Modifier.padding(bottom = Dimens.paddingSmall)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Chart area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                ChartCanvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val centerX = size.width * data.centerX
                    val centerY = size.height * data.centerY
                    val maxRadius = min(size.width, size.height) * 0.4f
                    
                    drawRadialBars(
                        bars = data.bars,
                        centerX = centerX,
                        centerY = centerY,
                        maxRadius = maxRadius,
                        barSize = data.barSize,
                        startAngle = data.startAngle,
                        endAngle = data.endAngle,
                        showBackground = data.showBackground,
                        backgroundOpacity = data.backgroundOpacity,
                        textMeasurer = textMeasurer
                    )
                }
            }

            // Legend
            if (data.config.showLegend) {
                RadialBarLegend(
                    bars = data.bars,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

/**
 * Draw radial bars
 */
private fun DrawScope.drawRadialBars(
    bars: List<RadialBarEntry>,
    centerX: Float,
    centerY: Float,
    maxRadius: Float,
    barSize: Float,
    startAngle: Float,
    endAngle: Float,
    showBackground: Boolean,
    backgroundOpacity: Float,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val radiusStep = maxRadius / bars.size
    
    bars.forEachIndexed { index, bar ->
        val outerRadius = maxRadius - (index * radiusStep)
        val innerRadius = outerRadius - barSize
        
        // Calculate sweep angle based on value
        val percentage = (bar.value / bar.maxValue).coerceIn(0f, 1f)
        val sweepAngle = (endAngle - startAngle) * percentage
        
        // Draw background arc
        if (showBackground) {
            drawArc(
                color = bar.fill.copy(alpha = backgroundOpacity),
                startAngle = startAngle - 90f, // -90 to start from top
                sweepAngle = endAngle - startAngle,
                useCenter = false,
                topLeft = Offset(centerX - outerRadius, centerY - outerRadius),
                size = Size(outerRadius * 2, outerRadius * 2),
                style = Stroke(width = barSize)
            )
        }
        
        // Draw actual value arc
        drawArc(
            color = bar.fill,
            startAngle = startAngle - 90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(centerX - outerRadius, centerY - outerRadius),
            size = Size(outerRadius * 2, outerRadius * 2),
            style = Stroke(width = barSize)
        )
        
        // Draw label
        if (bar.showLabel) {
            val labelText = bar.label ?: "${bar.value.toInt()}"
            val labelRadius = when (bar.labelPosition) {
                RadialLabelPosition.INSIDE_START -> innerRadius + barSize * 0.2f
                RadialLabelPosition.INSIDE_END -> innerRadius + barSize * 0.8f
                RadialLabelPosition.OUTSIDE -> outerRadius + 10f
            }
            
            // Position label at the start of the arc
            val labelAngle = Math.toRadians((startAngle + 10f).toDouble())
            val labelX = centerX + labelRadius * cos(labelAngle).toFloat()
            val labelY = centerY + labelRadius * sin(labelAngle).toFloat()
            
            val textResult = textMeasurer.measure(
                text = labelText,
                style = TextStyle(fontSize = 12.sp, color = Color.White)
            )
            
            drawText(
                textLayoutResult = textResult,
                topLeft = Offset(
                    labelX - textResult.size.width / 2,
                    labelY - textResult.size.height / 2
                )
            )
        }
    }
}

/**
 * Legend for radial bar chart
 */
@Composable
private fun RadialBarLegend(
    bars: List<RadialBarEntry>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        bars.forEach { bar ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(bar.fill)
                )
                Text(
                    text = bar.name,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RadialBarChartPreview() {
    val data = RadialBarChartData(
        title = "Age Distribution",
        bars = listOf(
            RadialBarEntry(
                name = "18-24",
                value = 31.47f,
                fill = Color(0xFF8884d8)
            ),
            RadialBarEntry(
                name = "25-29",
                value = 26.69f,
                fill = Color(0xFF83a6ed)
            ),
            RadialBarEntry(
                name = "30-34",
                value = 15.69f,
                fill = Color(0xFF8dd1e1)
            ),
            RadialBarEntry(
                name = "35-39",
                value = 8.22f,
                fill = Color(0xFF82ca9d)
            ),
            RadialBarEntry(
                name = "40-49",
                value = 8.63f,
                fill = Color(0xFFa4de6c)
            ),
            RadialBarEntry(
                name = "50+",
                value = 2.63f,
                fill = Color(0xFFd0ed57)
            ),
            RadialBarEntry(
                name = "unknown",
                value = 6.67f,
                fill = Color(0xFFffc658)
            )
        ),
        config = ChartConfig(
            showLegend = true,
            showGrid = false,
            showAxis = false
        ),
        centerX = 0.3f,
        barSize = 14f
    )

    RadialBarChart(
        data = data,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

