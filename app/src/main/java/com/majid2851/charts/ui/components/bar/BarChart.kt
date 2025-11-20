package com.majid2851.charts.ui.components.bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.theme.AppColors
import com.majid2851.charts.ui.theme.Dimens
import com.majid2851.charts.ui.theme.Strings
import com.majid2851.charts.ui.theme.FontSizes
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
 
@Composable
fun BarChart(
    data: BarChartData,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {

        Canvas(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (data.bars.isEmpty() || data.bars.all { it.entries.isEmpty() }) {
                return@Canvas
            }

            val chartWidth = size.width
            val chartHeight = size.height
            
            // Calculate margins for axes
            val leftMargin = if (data.config.showAxis) 60f else 20f
            val rightMargin = 20f
            val topMargin = 20f
            val bottomMargin = if (data.config.showAxis) 40f else 20f
            
            val drawWidth = chartWidth - leftMargin - rightMargin
            val drawHeight = chartHeight - topMargin - bottomMargin
            
            // Get all values to calculate scale
            val allValues = data.bars.flatMap { bar -> bar.entries.map { it.value } }
            val maxValue = allValues.maxOrNull() ?: 0f
            val minValue = allValues.minOrNull() ?: 0f
            
            val yMax = if (maxValue > 0) maxValue * 1.1f else 0f
            val yMin = if (minValue < 0) minValue * 1.1f else 0f
            val yRange = yMax - yMin
            
            // Draw grid
            if (data.config.showGrid) {
                drawGrid(
                    config = data.config.cartesianGrid,
                    left = leftMargin,
                    top = topMargin,
                    width = drawWidth,
                    height = drawHeight,
                    yMax = yMax,
                    yMin = yMin
                )
            }
            
            // Draw axes
            if (data.config.showAxis) {
                drawAxes(
                    left = leftMargin,
                    top = topMargin,
                    width = drawWidth,
                    height = drawHeight,
                    yMax = yMax,
                    yMin = yMin,
                    labels = data.bars.firstOrNull()?.entries?.map { it.label } ?: emptyList(),
                    textMeasurer = textMeasurer
                )
            }
            
            // Draw bars
            val categories = data.bars.firstOrNull()?.entries?.size ?: 0
            if (categories > 0) {
                when (data.groupingMode) {
                    BarGroupingMode.GROUPED -> drawGroupedBars(
                        bars = data.bars,
                        left = leftMargin,
                        top = topMargin,
                        width = drawWidth,
                        height = drawHeight,
                        yMax = yMax,
                        yMin = yMin,
                        yRange = yRange
                    )
                    BarGroupingMode.STACKED -> drawStackedBars(
                        bars = data.bars,
                        left = leftMargin,
                        top = topMargin,
                        width = drawWidth,
                        height = drawHeight,
                        yMax = yMax,
                        yMin = yMin,
                        yRange = yRange
                    )
                }
            }
        }

        // Legend
        if (data.config.showLegend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.paddingSmall),
                horizontalArrangement = Arrangement.Center,
            ) {
                data.bars.forEach { bar ->
                    Row(
                        modifier=Modifier
                            .padding(Dimens.paddingSmall),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(Dimens.paddingSmall)
                                .size(12.dp)
                                .background(bar.barColor)
                        )
                        Text(
                            text = bar.label,
                            fontSize = FontSizes.bodySmall
                        )
                    }
                }
            }
        }
    }
}

/**
 * Draw grid lines
 */
private fun DrawScope.drawGrid(
    config: CartesianGridConfig,
    left: Float,
    top: Float,
    width: Float,
    height: Float,
    yMax: Float,
    yMin: Float
) {
    val horizontalLines = 5
    val pathEffect = PathEffect.dashPathEffect(config.horizontalDashPattern)
    
    // Horizontal grid lines
    if (config.showHorizontalLines) {
        for (i in 0..horizontalLines) {
            val y = top + (height * i / horizontalLines)
            drawLine(
                color = config.horizontalLineColor.copy(alpha = config.horizontalLineAlpha),
                start = Offset(left, y),
                end = Offset(left + width, y),
                strokeWidth = config.horizontalLineWidth,
                pathEffect = if (config.horizontalLineStyle == GridLineStyle.DASHED) pathEffect else null
            )
        }
    }
}

/**
 * Draw X and Y axes with labels
 */
private fun DrawScope.drawAxes(
    left: Float,
    top: Float,
    width: Float,
    height: Float,
    yMax: Float,
    yMin: Float,
    labels: List<String>,
    textMeasurer: androidx.compose.ui.text.TextMeasurer
) {
    val axisColor = Color.Gray
    
    // Y-axis
    drawLine(
        color = axisColor,
        start = Offset(left, top),
        end = Offset(left, top + height),
        strokeWidth = 2f
    )
    
    // X-axis
    drawLine(
        color = axisColor,
        start = Offset(left, top + height),
        end = Offset(left + width, top + height),
        strokeWidth = 2f
    )
    
    // Y-axis labels
    val yLabels = 5
    for (i in 0..yLabels) {
        val value = yMax - (yMax - yMin) * i / yLabels
        val y = top + (height * i / yLabels)
        
        val text = when {
            abs(value) >= 1000 -> String.format("%.1fk", value / 1000)
            abs(value) >= 100 -> String.format("%.0f", value)
            else -> String.format("%.1f", value)
        }
        
        val textLayoutResult = textMeasurer.measure(
            text = text,
            style = TextStyle(fontSize = 10.sp, color = Color.Gray)
        )
        
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(left - textLayoutResult.size.width - 10f, y - textLayoutResult.size.height / 2)
        )
    }
    
    // X-axis labels
    if (labels.isNotEmpty()) {
        val categoryWidth = width / labels.size
        labels.forEachIndexed { index, label ->
            val x = left + categoryWidth * index + categoryWidth / 2
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(fontSize = 10.sp, color = Color.Gray)
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(x - textLayoutResult.size.width / 2, top + height + 10f)
            )
        }
    }
}

/**
 * Draw grouped bars (side by side)
 */
private fun DrawScope.drawGroupedBars(
    bars: List<BarDataSet>,
    left: Float,
    top: Float,
    width: Float,
    height: Float,
    yMax: Float,
    yMin: Float,
    yRange: Float
) {
    val categories = bars.firstOrNull()?.entries?.size ?: return
    val categoryWidth = width / categories
    val barWidth = (categoryWidth * 0.8f) / bars.size
    val groupPadding = categoryWidth * 0.1f
    
    val zeroY = top + height * (yMax / yRange)
    
    bars.forEachIndexed { barIndex, barSet ->
        barSet.entries.forEachIndexed { categoryIndex, entry ->
            val x = left + categoryWidth * categoryIndex + groupPadding + barWidth * barIndex
            val barHeight = (entry.value / yRange) * height
            val y = if (entry.value >= 0) {
                zeroY - barHeight
            } else {
                zeroY
            }
            
            drawRect(
                color = barSet.barColor,
                topLeft = Offset(x, y),
                size = Size(barWidth, abs(barHeight))
            )
        }
    }
}

/**
 * Draw stacked bars (on top of each other)
 */
private fun DrawScope.drawStackedBars(
    bars: List<BarDataSet>,
    left: Float,
    top: Float,
    width: Float,
    height: Float,
    yMax: Float,
    yMin: Float,
    yRange: Float
) {
    val categories = bars.firstOrNull()?.entries?.size ?: return
    val categoryWidth = width / categories
    val barWidth = categoryWidth * 0.6f
    val padding = categoryWidth * 0.2f
    
    val zeroY = top + height * (yMax / yRange)
    
    for (categoryIndex in 0 until categories) {
        var positiveStackHeight = 0f
        var negativeStackHeight = 0f
        
        bars.forEach { barSet ->
            if (categoryIndex < barSet.entries.size) {
                val entry = barSet.entries[categoryIndex]
                val x = left + categoryWidth * categoryIndex + padding
                val barHeight = (entry.value / yRange) * height
                
                if (entry.value >= 0) {
                    val y = zeroY - positiveStackHeight - barHeight
                    drawRect(
                        color = barSet.barColor,
                        topLeft = Offset(x, y),
                        size = Size(barWidth, barHeight)
                    )
                    positiveStackHeight += barHeight
                } else {
                    val y = zeroY + negativeStackHeight
                    drawRect(
                        color = barSet.barColor,
                        topLeft = Offset(x, y),
                        size = Size(barWidth, abs(barHeight))
                    )
                    negativeStackHeight += abs(barHeight)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    BarChart(
        data = BarChartData(
            title = Strings.BAR_CHART_TITLE,
            bars = listOf(
                BarDataSet(
                    label = "Series 1",
                    entries = listOf(
                        LabeledEntry("Q1", 100f),
                        LabeledEntry("Q2", 150f),
                        LabeledEntry("Q3", 120f),
                        LabeledEntry("Q4", 180f)
                    ),
                    barColor = AppColors.Blue
                ),
                BarDataSet(
                    label = "Series 2",
                    entries = listOf(
                        LabeledEntry("Q1", 80f),
                        LabeledEntry("Q2", 130f),
                        LabeledEntry("Q3", 90f),
                        LabeledEntry("Q4", 160f)
                    ),
                    barColor = Color(0xFF82ca9d)
                )
            ),
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
