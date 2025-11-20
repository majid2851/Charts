package com.majid2851.charts.ui.components.area.variants

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.AreaChartData
import com.majid2851.charts.domain.model.AreaDataSet
import com.majid2851.charts.domain.model.DataPoint
import com.majid2851.charts.ui.components.area.AreaChart

/**
 * Area Chart with Connect Nulls comparison
 * Matches Recharts AreaChartConnectNulls example
 */
@Composable
fun AreaChartConnectNulls(
    modifier: Modifier = Modifier
) {
    val data = listOf(
        DataPoint(0f, 4000f, "Page A"),
        DataPoint(1f, 3000f, "Page B"),
        DataPoint(2f, 2000f, "Page C"),
        null, // Missing data for Page D
        DataPoint(4f, 1890f, "Page E"),
        DataPoint(5f, 2390f, "Page F"),
        DataPoint(6f, 3490f, "Page G")
    )

    Column(modifier = modifier) {
        Text(
            text = "Without Connect Nulls",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        
        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "UV",
                        dataPoints = data,
                        lineColor = Color(0xFF8884d8),
                        fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
                        isCurved = true
                    )
                ),
                connectNulls = false
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "With Connect Nulls",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "UV",
                        dataPoints = data,
                        lineColor = Color(0xFF8884d8),
                        fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
                        isCurved = true
                    )
                ),
                connectNulls = true
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AreaChartConnectNullsPreview() {
    AreaChartConnectNulls(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

