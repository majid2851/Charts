package com.majid2851.charts.ui.components.pie.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.PieChartConfig
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice
import com.majid2851.charts.ui.components.pie.PieChart

/**
 * Donut Chart - Pie chart with inner radius (hole in the middle)
 * Can display center label
 */
@Composable
fun DonutChart(
    data: PieChartData,
    modifier: Modifier = Modifier,
    centerLabel: String? = null,
    onSliceClick: ((PieSlice, Int) -> Unit)? = null
) {
    PieChart(
        data = data.copy(
            config = data.config.copy(
                innerRadius = 0.6f,
                outerRadius = 0.9f,
                centerLabel = centerLabel
            )
        ),
        modifier = modifier,
        onSliceClick = onSliceClick
    )
}

@Preview(showBackground = true)
@Composable
private fun DonutChartPreview() {
    DonutChart(
        data = PieChartData(
            title = "Donut Chart",
            slices = listOf(
                PieSlice("A", 400f, Color(0xFF0088FE)),
                PieSlice("B", 300f, Color(0xFF00C49F)),
                PieSlice("C", 300f, Color(0xFFFFBB28)),
                PieSlice("D", 200f, Color(0xFFFF8042))
            ),
            config = PieChartConfig(showLabels = true)
        ),
        centerLabel = "Total\n1200",
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}


