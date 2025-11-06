package com.majid2851.charts.ui.components.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope

interface ChartComponent {
    @Composable
    fun Draw(modifier: Modifier)
}

@Composable
fun ChartCanvas(
    modifier: Modifier = Modifier,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        onDraw()
    }
}

object ChartUtils {
    
    fun calculateMaxValue(values: List<Float>): Float {
        return values.maxOrNull() ?: 0f
    }

    fun calculateMinValue(values: List<Float>): Float {
        return values.minOrNull() ?: 0f
    }

    fun normalizeValue(
        value: Float,
        minValue: Float,
        maxValue: Float,
        targetMin: Float = 0f,
        targetMax: Float = 1f
    ): Float {
        if (maxValue == minValue) return targetMin
        return ((value - minValue) / (maxValue - minValue)) * (targetMax - targetMin) + targetMin
    }
}

