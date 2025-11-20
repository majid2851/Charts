# TreeMap Component

## Overview

The TreeMap component displays hierarchical data as nested rectangles, where the size of each rectangle represents a quantitative value. This visualization is excellent for showing part-to-whole relationships and hierarchical structures.

## Features

- 📊 **Hierarchical Data**: Support for multi-level nested data structures
- 📐 **Squarified Layout**: Optimized rectangle placement algorithm
- 🎨 **Color Coding**: Automatic or custom color assignment
- 🏷️ **Smart Labels**: Labels shown for readable rectangles only
- 📏 **Aspect Ratio Control**: Maintain desired proportions
- 🎯 **Depth-based Styling**: Different styles for different hierarchy levels

## Quick Start

```kotlin
@Composable
fun MyScreen() {
    TreeMapChart(
        data = TreeMapData(
            title = "File System Usage",
            nodes = listOf(
                TreeMapNode(
                    name = "Documents",
                    children = listOf(
                        TreeMapNode("Reports", size = 5000f),
                        TreeMapNode("Presentations", size = 3500f),
                        TreeMapNode("Spreadsheets", size = 2000f)
                    )
                ),
                TreeMapNode(
                    name = "Media",
                    children = listOf(
                        TreeMapNode("Videos", size = 15000f),
                        TreeMapNode("Photos", size = 8000f),
                        TreeMapNode("Music", size = 4000f)
                    )
                )
            ),
            strokeColor = Color.White,
            defaultFill = Color(0xFF8884d8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## Data Structure

### TreeMapData
```kotlin
data class TreeMapData(
    val title: String? = null,
    val nodes: List<TreeMapNode>,
    val dataKey: String = "size",
    val config: ChartConfig = ChartConfig(),
    val aspectRatio: Float = 4f / 3f,
    val strokeColor: Color = Color.White,
    val strokeWidth: Float = 2f,
    val defaultFill: Color = Color(0xFF8884d8),
    val colorPalette: List<Color> = [...],
    val showLabels: Boolean = true,
    val labelTextSize: Float = 14f,
    val labelColor: Color = Color.White
)
```

### TreeMapNode
```kotlin
data class TreeMapNode(
    val name: String,
    val size: Float = 0f,
    val children: List<TreeMapNode> = emptyList(),
    val customColor: Color? = null,
    val depth: Int = 0
)
```

## Configuration Options

### Color Palette
Customize the color scheme:
```kotlin
TreeMapData(
    nodes = myNodes,
    colorPalette = listOf(
        Color(0xFF8889DD),
        Color(0xFF9597E4),
        Color(0xFF8DC77B),
        Color(0xFFA5D297),
        Color(0xFFE2CF45),
        Color(0xFFF8C12D)
    )
)
```

### Aspect Ratio
Control the shape of the treemap:
```kotlin
TreeMapData(
    nodes = myNodes,
    aspectRatio = 4f / 3f,  // Standard 4:3
    // or aspectRatio = 16f / 9f for wide format
    // or aspectRatio = 1f for square
)
```

### Stroke Style
Customize borders:
```kotlin
TreeMapData(
    nodes = myNodes,
    strokeColor = Color.White,
    strokeWidth = 2f
)
```

### Labels
Control label display:
```kotlin
TreeMapData(
    nodes = myNodes,
    showLabels = true,
    labelTextSize = 14f,
    labelColor = Color.White
)
```

## Hierarchical Data

TreeMap supports multiple levels of nesting:

```kotlin
TreeMapNode(
    name = "Root",
    children = listOf(
        TreeMapNode(
            name = "Level 1 - Category A",
            children = listOf(
                TreeMapNode("Subcategory A1", size = 1000f),
                TreeMapNode("Subcategory A2", size = 1500f)
            )
        ),
        TreeMapNode(
            name = "Level 1 - Category B",
            children = listOf(
                TreeMapNode(
                    name = "Subcategory B1",
                    children = listOf(
                        TreeMapNode("Item B1a", size = 500f),
                        TreeMapNode("Item B1b", size = 300f)
                    )
                )
            )
        )
    )
)
```

## Common Use Cases

### File System Visualization
```kotlin
TreeMapChart(
    data = TreeMapData(
        title = "Disk Usage",
        nodes = listOf(
            TreeMapNode(
                name = "System",
                children = listOf(
                    TreeMapNode("OS Files", size = 25000f),
                    TreeMapNode("Applications", size = 15000f),
                    TreeMapNode("Libraries", size = 8000f)
                )
            ),
            TreeMapNode(
                name = "User Data",
                children = listOf(
                    TreeMapNode("Documents", size = 12000f),
                    TreeMapNode("Media", size = 45000f),
                    TreeMapNode("Downloads", size = 8000f)
                )
            )
        )
    )
)
```

### Budget Allocation
```kotlin
TreeMapChart(
    data = TreeMapData(
        title = "Annual Budget",
        nodes = listOf(
            TreeMapNode(
                name = "Operations",
                children = listOf(
                    TreeMapNode("Salaries", size = 500000f),
                    TreeMapNode("Office", size = 100000f),
                    TreeMapNode("Utilities", size = 50000f)
                )
            ),
            TreeMapNode(
                name = "Marketing",
                children = listOf(
                    TreeMapNode("Digital", size = 150000f),
                    TreeMapNode("Traditional", size = 100000f),
                    TreeMapNode("Events", size = 75000f)
                )
            )
        )
    )
)
```

### Sales by Region
```kotlin
TreeMapChart(
    data = TreeMapData(
        title = "Regional Sales",
        nodes = listOf(
            TreeMapNode(
                name = "North America",
                children = listOf(
                    TreeMapNode("USA", size = 850000f),
                    TreeMapNode("Canada", size = 150000f),
                    TreeMapNode("Mexico", size = 75000f)
                )
            ),
            TreeMapNode(
                name = "Europe",
                children = listOf(
                    TreeMapNode("UK", size = 250000f),
                    TreeMapNode("Germany", size = 300000f),
                    TreeMapNode("France", size = 200000f)
                )
            )
        )
    )
)
```

## Tips & Best Practices

### 1. Size Values
Ensure size values are meaningful and positive:
```kotlin
TreeMapNode(
    name = "Category",
    size = 1000f  // Must be > 0
)
```

### 2. Hierarchy Depth
Keep depth reasonable (2-3 levels optimal):
```kotlin
// Good: 2-3 levels
Root -> Category -> Subcategory

// Avoid: Too many levels
Root -> L1 -> L2 -> L3 -> L4 -> L5
```

### 3. Label Visibility
Labels only show when rectangles are large enough. For many small items, labels may be hidden automatically.

### 4. Color Contrast
Use sufficient contrast between stroke and fill:
```kotlin
TreeMapData(
    strokeColor = Color.White,  // Light border
    defaultFill = Color(0xFF8884d8)  // Dark fill
)
```

### 5. Data Preparation
Sort data by size for better visualization:
```kotlin
val sortedChildren = children.sortedByDescending { it.size }
```

## Layout Algorithm

The component uses a **squarified treemap** algorithm that:
1. Minimizes aspect ratios of rectangles
2. Groups similar-sized items together
3. Recursively subdivides space
4. Maintains readability and balance

## Performance

For optimal performance:
- Limit total nodes to < 100 for smooth rendering
- Use appropriate hierarchy depth (2-3 levels)
- Consider data aggregation for very large datasets

## Comparison with Recharts

This component matches the Recharts Treemap API:
- ✅ Hierarchical data structure
- ✅ Squarified layout algorithm
- ✅ Custom colors per node
- ✅ Stroke and fill styling
- ✅ Aspect ratio control
- ✅ Label display options

## Related Components

- **PieChart**: For simple part-to-whole relationships
- **DonutChart**: For hierarchical pie charts
- **Sunburst** (future): For radial hierarchies

## Support

For issues or questions:
1. Check the implementation in `TreeMapChart.kt`
2. Review the preview example in the same file
3. See `ChartDemoScreen.kt` for live examples

