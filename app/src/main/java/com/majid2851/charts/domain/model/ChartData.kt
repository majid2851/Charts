package com.majid2851.charts.domain.model

interface ChartData {
    val title: String?
    val description: String?
}

data class DataPoint(
    val x: Float,
    val y: Float,
    val label: String? = null
)

data class LabeledEntry(
    val label: String,
    val value: Float,
    val color: Long? = null
)

