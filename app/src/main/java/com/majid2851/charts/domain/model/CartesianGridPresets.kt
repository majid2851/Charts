package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

object CartesianGridPresets {
    
    fun defaultGrid() = CartesianGridConfig()
    
    fun solidGrid(
        color: Color = Color.LightGray,
        alpha: Float = 0.3f
    ) = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.SOLID,
        verticalLineStyle = GridLineStyle.SOLID,
        horizontalLineColor = color,
        verticalLineColor = color,
        horizontalLineAlpha = alpha,
        verticalLineAlpha = alpha
    )
    
    fun dashedGrid(
        color: Color = Color.LightGray,
        alpha: Float = 0.3f,
        dashPattern: FloatArray = floatArrayOf(10f, 10f)
    ) = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        verticalLineStyle = GridLineStyle.DASHED,
        horizontalLineColor = color,
        verticalLineColor = color,
        horizontalLineAlpha = alpha,
        verticalLineAlpha = alpha,
        horizontalDashPattern = dashPattern,
        verticalDashPattern = dashPattern
    )
    
    fun dottedGrid(
        color: Color = Color.LightGray,
        alpha: Float = 0.3f
    ) = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DOTTED,
        verticalLineStyle = GridLineStyle.DOTTED,
        horizontalLineColor = color,
        verticalLineColor = color,
        horizontalLineAlpha = alpha,
        verticalLineAlpha = alpha
    )
    
    fun horizontalOnlyGrid(
        color: Color = Color.LightGray,
        alpha: Float = 0.3f,
        style: GridLineStyle = GridLineStyle.DASHED
    ) = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = false,
        horizontalLineStyle = style,
        horizontalLineColor = color,
        horizontalLineAlpha = alpha
    )
    
    fun verticalOnlyGrid(
        color: Color = Color.LightGray,
        alpha: Float = 0.3f,
        style: GridLineStyle = GridLineStyle.DASHED
    ) = CartesianGridConfig(
        showHorizontalLines = false,
        showVerticalLines = true,
        verticalLineStyle = style,
        verticalLineColor = color,
        verticalLineAlpha = alpha
    )
    
    fun customStrokeDashGrid(
        horizontalPattern: FloatArray = floatArrayOf(3f, 3f),
        verticalPattern: FloatArray = floatArrayOf(3f, 3f),
        color: Color = Color.LightGray
    ) = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        verticalLineStyle = GridLineStyle.DASHED,
        horizontalLineColor = color,
        verticalLineColor = color,
        horizontalDashPattern = horizontalPattern,
        verticalDashPattern = verticalPattern
    )
    
    fun rechartsStyleGrid() = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        verticalLineStyle = GridLineStyle.DASHED,
        horizontalLineColor = Color(0xFFCCCCCC),
        verticalLineColor = Color(0xFFCCCCCC),
        horizontalLineAlpha = 0.5f,
        verticalLineAlpha = 0.5f,
        horizontalDashPattern = floatArrayOf(3f, 3f),
        verticalDashPattern = floatArrayOf(3f, 3f)
    )
}









