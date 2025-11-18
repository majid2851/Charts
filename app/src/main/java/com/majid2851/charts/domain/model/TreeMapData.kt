package com.majid2851.charts.domain.model

import androidx.compose.ui.graphics.Color

/**
 * Data model for TreeMap - hierarchical rectangular visualization
 */
data class TreeMapData(
    override val title: String? = null,
    override val description: String? = null,
    val nodes: List<TreeMapNode>,
    val dataKey: String = "size", // Key to use for sizing rectangles
    val config: ChartConfig = ChartConfig(),
    val aspectRatio: Float = 4f / 3f,
    val strokeColor: Color = Color.White,
    val strokeWidth: Float = 2f,
    val defaultFill: Color = Color(0xFF8884d8),
    val colorPalette: List<Color> = listOf(
        Color(0xFF8889DD),
        Color(0xFF9597E4),
        Color(0xFF8DC77B),
        Color(0xFFA5D297),
        Color(0xFFE2CF45),
        Color(0xFFF8C12D)
    ),
    val showLabels: Boolean = true,
    val labelTextSize: Float = 14f,
    val labelColor: Color = Color.White
) : ChartData

/**
 * TreeMap node - can have children for hierarchical data
 */
data class TreeMapNode(
    val name: String,
    val size: Float = 0f,
    val children: List<TreeMapNode> = emptyList(),
    val customColor: Color? = null,
    val depth: Int = 0
) {
    /**
     * Get total size including all children
     */
    fun getTotalSize(): Float {
        return if (children.isEmpty()) {
            size
        } else {
            children.sumOf { it.getTotalSize().toDouble() }.toFloat()
        }
    }

    /**
     * Check if this is a leaf node
     */
    fun isLeaf(): Boolean = children.isEmpty()

    /**
     * Flatten tree to list with depth information
     */
    fun flatten(currentDepth: Int = 0): List<TreeMapNode> {
        val result = mutableListOf<TreeMapNode>()
        val nodeWithDepth = copy(depth = currentDepth)
        result.add(nodeWithDepth)
        
        children.forEach { child ->
            result.addAll(child.flatten(currentDepth + 1))
        }
        
        return result
    }
}

/**
 * Rectangle position and size for treemap layout
 */
data class TreeMapRect(
    val node: TreeMapNode,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val depth: Int,
    val color: Color,
    val index: Int = 0,
    val parentIndex: Int = -1
)

