package com.majid2851.charts.ui.components.composed.variants

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.composed.ComposedChart

/**
 * Banded Chart
 * Shows a line chart with a range band area
 */
@Composable
fun BandedChart(
    modifier: Modifier = Modifier
) {
    val categories = listOf("Page A", "Page B", "Page C", "Page D", "Page E", "Page F")
    
    val data = ComposedChartData(
        title = "Banded Chart",
        categories = categories,
        // Range band (shown as area from min to max)
        areaDataSets = listOf(
            AreaDataSet(
                label = "Range",
                dataPoints = listOf(
                    DataPoint(0f, 0f),   // Min at Page A
                    DataPoint(1f, 175f), // Average of 50 and 300
                    DataPoint(2f, 286.5f), // Average of 150 and 423
                    null, // Page D has no range
                    DataPoint(4f, 522.5f), // Average of 367 and 678
                    DataPoint(5f, 563f)  // Average of 305 and 821
                ),
                lineColor = Color.Transparent,
                fillColor = Color(0xFFCCCCCC),
                fillOpacity = 1f
            )
        ),
        lineDataSets = listOf(
            LineDataSet(
                label = "b",
                dataPoints = listOf(
                    DataPoint(0f, 0f),
                    DataPoint(1f, 106f),
                    DataPoint(2f, 229f),
                    DataPoint(3f, 312f),
                    DataPoint(4f, 451f),
                    DataPoint(5f, 623f)
                ),
                lineColor = Color.Magenta,
                lineWidth = 2f,
                isCurved = true
            )
        ),
        config = ChartConfig(
            showGrid = true,
            showAxis = true,
            showLegend = true,
            cartesianGrid = CartesianGridConfig(
                horizontalLineStyle = GridLineStyle.DASHED,
                verticalLineStyle = GridLineStyle.DASHED
            )
        )
    )
    
    ComposedChart(
        data = data,
        modifier = modifier
    )
}

