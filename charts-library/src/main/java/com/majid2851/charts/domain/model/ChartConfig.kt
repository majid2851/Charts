package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ChartConfig(
    val showGrid: Boolean = true,
    val showAxis: Boolean = true,
    val showLegend: Boolean = true,
    val animationEnabled: Boolean = true,
    val animationDuration: Int = 300,
    val backgroundColor: Color = Color.Transparent,
    val chartPadding: Dp = 16.dp,
    val isInteractive: Boolean = true,
    val cartesianGrid: CartesianGridConfig = CartesianGridConfig(),
    val enableZoom: Boolean = false,
    val enablePan: Boolean = false,
    val showZoomControls: Boolean = false,
    val minZoom: Float = 0.5f,
    val maxZoom: Float = 5f
)

data class AxisConfig(
    val showLabels: Boolean = true,
    val showGridLines: Boolean = true,
    val labelCount: Int = 5,
    val axisColor: Color = Color.Gray,
    val gridColor: Color = Color.LightGray,
    val labelTextSize: Float = 16f,
    val axisLabel: String? = null,
    val labelPosition: LabelPosition = LabelPosition.OUTSIDE
)

data class CartesianGridConfig(
    val showHorizontalLines: Boolean = true,
    val showVerticalLines: Boolean = true,
    val horizontalLineStyle: GridLineStyle = GridLineStyle.DASHED,
    val verticalLineStyle: GridLineStyle = GridLineStyle.DASHED,
    val horizontalLineColor: Color = Color.LightGray,
    val verticalLineColor: Color = Color.LightGray,
    val horizontalLineWidth: Float = 1f,
    val verticalLineWidth: Float = 1f,
    val horizontalLineAlpha: Float = 0.3f,
    val verticalLineAlpha: Float = 0.3f,
    val horizontalDashPattern: FloatArray = floatArrayOf(10f, 10f),
    val verticalDashPattern: FloatArray = floatArrayOf(10f, 10f),
    val horizontalLineCount: Int = 5,
    val verticalLineCount: Int = 5
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartesianGridConfig

        if (showHorizontalLines != other.showHorizontalLines) return false
        if (showVerticalLines != other.showVerticalLines) return false
        if (horizontalLineStyle != other.horizontalLineStyle) return false
        if (verticalLineStyle != other.verticalLineStyle) return false
        if (horizontalLineColor != other.horizontalLineColor) return false
        if (verticalLineColor != other.verticalLineColor) return false
        if (horizontalLineWidth != other.horizontalLineWidth) return false
        if (verticalLineWidth != other.verticalLineWidth) return false
        if (horizontalLineAlpha != other.horizontalLineAlpha) return false
        if (verticalLineAlpha != other.verticalLineAlpha) return false
        if (!horizontalDashPattern.contentEquals(other.horizontalDashPattern)) return false
        if (!verticalDashPattern.contentEquals(other.verticalDashPattern)) return false
        if (horizontalLineCount != other.horizontalLineCount) return false
        if (verticalLineCount != other.verticalLineCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = showHorizontalLines.hashCode()
        result = 31 * result + showVerticalLines.hashCode()
        result = 31 * result + horizontalLineStyle.hashCode()
        result = 31 * result + verticalLineStyle.hashCode()
        result = 31 * result + horizontalLineColor.hashCode()
        result = 31 * result + verticalLineColor.hashCode()
        result = 31 * result + horizontalLineWidth.hashCode()
        result = 31 * result + verticalLineWidth.hashCode()
        result = 31 * result + horizontalLineAlpha.hashCode()
        result = 31 * result + verticalLineAlpha.hashCode()
        result = 31 * result + horizontalDashPattern.contentHashCode()
        result = 31 * result + verticalDashPattern.contentHashCode()
        result = 31 * result + horizontalLineCount
        result = 31 * result + verticalLineCount
        return result
    }
}

enum class GridLineStyle {
    SOLID,
    DASHED,
    DOTTED
}

data class LegendConfig(
    val position: LegendPosition = LegendPosition.BOTTOM,
    val orientation: LegendOrientation = LegendOrientation.HORIZONTAL,
    val textColor: Color = Color.Black,
    val textSize: Float = 12f
)

enum class LegendPosition {
    TOP, BOTTOM, LEFT, RIGHT
}

enum class LegendOrientation {
    HORIZONTAL, VERTICAL
}

