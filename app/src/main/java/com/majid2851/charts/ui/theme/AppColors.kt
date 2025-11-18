package com.majid2851.charts.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650a4)
    val PurpleGrey40 = Color(0xFF625b71)
    val Pink40 = Color(0xFF7D5260)

    val Primary = Color(0xFF2196F3)
    val Secondary = Color(0xFFF44336)
    val Tertiary = Color(0xFF4CAF50)
    val Quaternary = Color(0xFFFFEB3B)
    
    val Blue = Color.Blue
    val Red = Color.Red
    val Green = Color.Green
    val Yellow = Color.Yellow
    val Gray = Color.Gray
    val LightGray = Color.LightGray
    val Black = Color.Black
    val White = Color.White
    val Transparent = Color.Transparent
    
    val GridLine = Color.LightGray
    val GridColorDefault = Color(0xFFAAA6A6)
    val AxisLine = Color.Gray
    val AxisColorDefault = Color(0xFF666666)
    val PlaceholderText = Color.Gray
    
    val RechartsBlue = Color(0xFF8884d8)
    val RechartsGreen = Color(0xFF82ca9d)
    
    fun Color.withAlpha(alpha: Float): Color = this.copy(alpha = alpha)
}

