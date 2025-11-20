package com.majid2851.charts.ui.components.treemap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.majid2851.charts.domain.model.*
import com.majid2851.charts.ui.components.base.ChartCanvas
import com.majid2851.charts.ui.theme.FontSizes
import com.majid2851.charts.ui.theme.Dimens
import kotlin.math.min

/**
 * TreeMapChart - Hierarchical rectangular visualization
 * Matches Recharts Treemap functionality
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun TreeMapChart(
    data: TreeMapData,
    modifier: Modifier = Modifier,
    customContent: ((TreeMapRect) -> Unit)? = null
) {
    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(data.config.backgroundColor)
            .padding(data.config.chartPadding)
    ) {
        data.title?.let {
            Text(
                text = it,
                fontSize = FontSizes.titleLarge,
                modifier = Modifier.padding(bottom = Dimens.paddingSmall)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            ChartCanvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val padding = 20f
                val chartWidth = size.width - padding * 2
                val chartHeight = size.height - padding * 2

                // Calculate layout
                val rectangles = calculateTreeMapLayout(
                    nodes = data.nodes,
                    x = padding,
                    y = padding,
                    width = chartWidth,
                    height = chartHeight,
                    colorPalette = data.colorPalette,
                    defaultColor = data.defaultFill
                )

                // Draw rectangles
                rectangles.forEach { rect ->
                    drawTreeMapRect(
                        rect = rect,
                        strokeColor = data.strokeColor,
                        strokeWidth = data.strokeWidth,
                        showLabels = data.showLabels,
                        labelTextSize = data.labelTextSize,
                        labelColor = data.labelColor,
                        textMeasurer = textMeasurer,
                        customContent = customContent
                    )
                }
            }
        }
    }
}
fun calculateTreeMapLayout(
    nodes: List<TreeMapNode>,
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    colorPalette: List<Color>,
    defaultColor: Color
): List<TreeMapRect> {
    val rectangles = mutableListOf<TreeMapRect>()
    
    nodes.forEachIndexed { index, node ->
        val color = node.customColor ?: colorPalette.getOrElse(index % colorPalette.size) { defaultColor }
        
        if (node.children.isNotEmpty()) {
            // Has children - layout recursively
            val childRects = layoutChildren(
                node = node,
                x = x,
                y = y,
                width = width,
                height = height,
                parentIndex = index,
                colorPalette = colorPalette,
                defaultColor = defaultColor
            )
            rectangles.addAll(childRects)
        } else {
            // Leaf node
            rectangles.add(
                TreeMapRect(
                    node = node,
                    x = x,
                    y = y,
                    width = width,
                    height = height,
                    depth = node.depth,
                    color = color,
                    index = index
                )
            )
        }
    }
    
    return rectangles
}

/**
 * Layout children of a node
 */
private fun layoutChildren(
    node: TreeMapNode,
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    parentIndex: Int,
    colorPalette: List<Color>,
    defaultColor: Color
): List<TreeMapRect> {
    val rectangles = mutableListOf<TreeMapRect>()
    val totalSize = node.getTotalSize()
    
    if (totalSize == 0f || node.children.isEmpty()) return rectangles
    
    // Add parent container
    val parentColor = node.customColor ?: colorPalette.getOrElse(parentIndex % colorPalette.size) { defaultColor }
    rectangles.add(
        TreeMapRect(
            node = node,
            x = x,
            y = y,
            width = width,
            height = height,
            depth = node.depth,
            color = parentColor,
            index = parentIndex,
            parentIndex = -1
        )
    )
    
    // Sort children by size (descending)
    val sortedChildren = node.children.sortedByDescending { it.getTotalSize() }
    
    // Use squarified layout
    var currentX = x
    var currentY = y
    var remainingWidth = width
    var remainingHeight = height
    
    sortedChildren.forEachIndexed { index, child ->
        val childSize = child.getTotalSize()
        val ratio = childSize / totalSize
        
        // Determine if we should lay out horizontally or vertically
        val useHorizontal = remainingWidth >= remainingHeight
        
        val childWidth: Float
        val childHeight: Float
        
        if (useHorizontal) {
            childWidth = remainingWidth * ratio
            childHeight = remainingHeight
            
            val childColor = child.customColor ?: 
                colorPalette.getOrElse((parentIndex * sortedChildren.size + index) % colorPalette.size) { defaultColor }
            
            if (child.children.isNotEmpty()) {
                // Recursively layout grandchildren
                val childRects = layoutChildren(
                    node = child.copy(depth = node.depth + 1),
                    x = currentX,
                    y = currentY,
                    width = childWidth,
                    height = childHeight,
                    parentIndex = (parentIndex * sortedChildren.size + index),
                    colorPalette = colorPalette,
                    defaultColor = defaultColor
                )
                rectangles.addAll(childRects)
            } else {
                rectangles.add(
                    TreeMapRect(
                        node = child.copy(depth = node.depth + 1),
                        x = currentX,
                        y = currentY,
                        width = childWidth,
                        height = childHeight,
                        depth = node.depth + 1,
                        color = childColor,
                        index = index,
                        parentIndex = parentIndex
                    )
                )
            }
            
            currentX += childWidth
            remainingWidth -= childWidth
        } else {
            childWidth = remainingWidth
            childHeight = remainingHeight * ratio
            
            val childColor = child.customColor ?: 
                colorPalette.getOrElse((parentIndex * sortedChildren.size + index) % colorPalette.size) { defaultColor }
            
            if (child.children.isNotEmpty()) {
                // Recursively layout grandchildren
                val childRects = layoutChildren(
                    node = child.copy(depth = node.depth + 1),
                    x = currentX,
                    y = currentY,
                    width = childWidth,
                    height = childHeight,
                    parentIndex = (parentIndex * sortedChildren.size + index),
                    colorPalette = colorPalette,
                    defaultColor = defaultColor
                )
                rectangles.addAll(childRects)
            } else {
                rectangles.add(
                    TreeMapRect(
                        node = child.copy(depth = node.depth + 1),
                        x = currentX,
                        y = currentY,
                        width = childWidth,
                        height = childHeight,
                        depth = node.depth + 1,
                        color = childColor,
                        index = index,
                        parentIndex = parentIndex
                    )
                )
            }
            
            currentY += childHeight
            remainingHeight -= childHeight
        }
    }
    
    return rectangles
}

/**
 * Draw a treemap rectangle
 */
@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawTreeMapRect(
    rect: TreeMapRect,
    strokeColor: Color,
    strokeWidth: Float,
    showLabels: Boolean,
    labelTextSize: Float,
    labelColor: Color,
    textMeasurer: androidx.compose.ui.text.TextMeasurer,
    customContent: ((TreeMapRect) -> Unit)?
) {
    if (customContent != null) {
        // Custom rendering handled by caller
        return
    }
    
    // Draw rectangle
    val adjustedStrokeWidth = strokeWidth / (rect.depth + 0.1f)
    
    drawRect(
        color = rect.color,
        topLeft = Offset(rect.x, rect.y),
        size = Size(rect.width, rect.height)
    )
    
    // Draw border
    drawRect(
        color = strokeColor,
        topLeft = Offset(rect.x, rect.y),
        size = Size(rect.width, rect.height),
        style = Stroke(width = adjustedStrokeWidth)
    )
    
    // Draw label for depth 1 nodes
    if (showLabels && rect.depth == 1 && rect.width > 40f && rect.height > 20f) {
        val textResult = textMeasurer.measure(
            text = rect.node.name,
            style = TextStyle(fontSize = labelTextSize.sp, color = labelColor)
        )
        
        val textX = rect.x + rect.width / 2 - textResult.size.width / 2
        val textY = rect.y + rect.height / 2 - textResult.size.height / 2
        
        // Only draw if text fits
        if (textResult.size.width < rect.width - 8 && textResult.size.height < rect.height) {
            drawText(
                textLayoutResult = textResult,
                topLeft = Offset(textX, textY)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TreeMapChartPreview() {
    val data = TreeMapData(
        title = "Simple TreeMap",
        nodes = listOf(
            TreeMapNode(
                name = "axis",
                children = listOf(
                    TreeMapNode("Axes", size = 1302f),
                    TreeMapNode("Axis", size = 24593f),
                    TreeMapNode("AxisGridLine", size = 652f),
                    TreeMapNode("AxisLabel", size = 636f),
                    TreeMapNode("CartesianAxes", size = 6703f)
                )
            ),
            TreeMapNode(
                name = "controls",
                children = listOf(
                    TreeMapNode("AnchorControl", size = 2138f),
                    TreeMapNode("ClickControl", size = 3824f),
                    TreeMapNode("Control", size = 1353f),
                    TreeMapNode("ControlList", size = 4665f)
                )
            ),
            TreeMapNode(
                name = "data",
                children = listOf(
                    TreeMapNode("Data", size = 20544f),
                    TreeMapNode("DataList", size = 19788f),
                    TreeMapNode("DataSprite", size = 10349f)
                )
            )
        ),
        strokeColor = Color.White,
        defaultFill = Color(0xFF8884d8)
    )

    TreeMapChart(
        data = data,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

