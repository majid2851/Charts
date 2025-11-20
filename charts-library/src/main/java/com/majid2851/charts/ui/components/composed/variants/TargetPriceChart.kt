package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart

/**
 * Target Price Chart
 * Shows actual price, target price, and confidence bands
 * Simplified version of the Recharts Target Price Chart example
 */
@Composable
fun TargetPriceChart(
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        "Jan 2023", "Mar 2023", "May 2023", "Jul 2023", 
        "Sep 2023", "Nov 2023", "Jan 2024", "Mar 2024"
    )
    
    val data = ComposedChartData(
        title = "Target Price Chart",
        categories = categories,
        
        // Low-High range (confidence band)
        areaDataSets = listOf(
            AreaDataSet(
                label = "Price Range",
                dataPoints = listOf(
                    DataPoint(0f, 350f),
                    DataPoint(1f, 375f),
                    DataPoint(2f, 400f),
                    DataPoint(3f, 425f),
                    DataPoint(4f, 450f),
                    DataPoint(5f, 475f),
                    DataPoint(6f, 500f),
                    DataPoint(7f, 525f)
                ),
                lineColor = Color(0xFFFFA500).copy(alpha = 0.3f),
                fillColor = Color(0xFFFFA500).copy(alpha = 0.12f)
            )
        ),
        
        // Actual price line
        lineDataSets = listOf(
            LineDataSet(
                label = "Actual Price",
                dataPoints = listOf(
                    DataPoint(0f, 317f),
                    DataPoint(1f, 340f),
                    DataPoint(2f, 375f),
                    DataPoint(3f, 396f),
                    DataPoint(4f, 421f),
                    DataPoint(5f, 435f),
                    DataPoint(6f, 456f),
                    DataPoint(7f, 478f)
                ),
                lineColor = Color(0xFF483D8B), // DarkSlateBlue
                lineWidth = 2f
            ),
            
            // Target price line
            LineDataSet(
                label = "Target Price",
                dataPoints = listOf(
                    DataPoint(0f, 396f),
                    DataPoint(1f, 405f),
                    DataPoint(2f, 429f),
                    DataPoint(3f, 460f),
                    DataPoint(4f, 480f),
                    DataPoint(5f, 489f),
                    DataPoint(6f, 510f),
                    DataPoint(7f, 524f)
                ),
                lineColor = Color(0xFFFF8C00), // DarkOrange
                lineWidth = 2f
            ),
            
            // Low bound line
            LineDataSet(
                label = "Low",
                dataPoints = listOf(
                    DataPoint(0f, 298f),
                    DataPoint(1f, 350f),
                    DataPoint(2f, 370f),
                    DataPoint(3f, 420f),
                    DataPoint(4f, 425f),
                    DataPoint(5f, 432f),
                    DataPoint(6f, 440f),
                    DataPoint(7f, 485f)
                ),
                lineColor = Color.Transparent,
                lineWidth = 0f,
                showPoints = false
            )
        ),
        
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            cartesianGrid = CartesianGridConfig(
                showVerticalLines = true,
                verticalLineColor = Color.LightGray,
                horizontalLineColor = Color.LightGray
            )
        ),
        xAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Date"
        ),
        yAxisConfig = AxisConfig(
            showLabels = true,
            axisLabel = "Price ($)",
            labelPosition = LabelPosition.OUTSIDE_RIGHT
        )
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

