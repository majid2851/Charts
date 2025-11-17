package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.AreaChartData
import com.majid2851.charts.domain.model.AreaDataSet
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.area.AreaChart
import kotlin.math.max
import kotlin.math.min

/**
 * Area Chart with Fill By Value (gradient split at zero)
 * Matches Recharts AreaChartFillByValue example
 */
@Composable
fun AreaChartFillByValue(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, -1000f, "Page C"),
        DataPoint(3f, 500f, "Page D"),
        DataPoint(4f, -2000f, "Page E"),
        DataPoint(5f, -250f, "Page F"),
        DataPoint(6f, 3490f, "Page G")
    )

    // Calculate gradient offset (where positive meets negative)
    val dataMax = data.maxOfOrNull { it.y } ?: 0f
    val dataMin = data.minOfOrNull { it.y } ?: 0f
    val offset = if (dataMax <= 0f) {
        0f
    } else if (dataMin >= 0f) {
        1f
    } else {
        dataMax / (dataMax - dataMin)
    }

    // Create gradient that changes color at zero
    val gradientBrush = Brush.verticalGradient(
        0f to Color.Green.copy(alpha = 0.8f),
        offset to Color.Green.copy(alpha = 0.1f),
        offset to Color.Red.copy(alpha = 0.1f),
        1f to Color.Red.copy(alpha = 0.8f)
    )

    AreaChart(
        data = AreaChartData(
            title = "Area Chart Fill By Value",
            areas = listOf(
                AreaDataSet(
                    label = "UV",
                    dataPoints = data,
                    lineColor = Color.Black,
                    fillColor = Color.Transparent, // Will use brush instead
                    fillBrush = gradientBrush,
                    isCurved = true
                )
            )
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun AreaChartFillByValuePreview() {
    AreaChartFillByValue(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

