package com.majid2851.charts.ui.components.line.line_chart.components

/**
 * Maps a data value to screen X coordinate
 */
fun mapToScreenX(
    value: Float,
    minValue: Float,
    maxValue: Float,
    width: Float,
    padding: Float
): Float {
    if (maxValue == minValue) return padding + width / 2
    return padding + (value - minValue) / (maxValue - minValue) * (width - 2 * padding)
}

/**
 * Maps a data value to screen Y coordinate
 */
fun mapToScreenY(
    value: Float,
    minValue: Float,
    maxValue: Float,
    height: Float,
    padding: Float
): Float {
    if (maxValue == minValue) return padding + height / 2
    return height - padding - (value - minValue) / (maxValue - minValue) * (height - 2 * padding)
}









