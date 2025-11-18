# ComposedChart Implementation Guide

## Overview

The `ComposedChart` component is a powerful chart that combines multiple chart types (Line, Bar, Area, and Scatter) in a single visualization. This implementation is based on the Recharts ComposedChart library and provides all the essential functionality needed for creating complex, multi-layered charts in Jetpack Compose.

## Features

### Core Features
- ✅ **Multiple Chart Types**: Combine Line, Bar, Area, and Scatter charts
- ✅ **Flexible Data Structure**: Support for multiple data sets per chart type
- ✅ **Shared Axes**: All chart types share the same coordinate system
- ✅ **Cartesian Grid**: Configurable grid with dashed or solid lines
- ✅ **Axis Labels**: Customizable X and Y axis labels
- ✅ **Legend**: Automatic legend generation for all data sets
- ✅ **Responsive Design**: Adapts to different screen sizes
- ✅ **Color Customization**: Individual colors for each data set
- ✅ **Orientation Support**: Vertical and horizontal chart layouts

### Chart Type Specific Features

#### Line
- Smooth or straight lines
- Configurable line width and color
- Point markers with customizable size
- Support for dashed lines

#### Bar
- Customizable bar width
- Individual bar colors
- Support for grouped bars

#### Area
- Filled areas with opacity control
- Stroke outline
- Support for stacking

#### Scatter
- Various point shapes (Circle, Square, Triangle, etc.)
- Configurable point size and color
- Support for custom point colors

## Implementation Architecture

### Data Models

#### ComposedChartData
Main data structure that holds all chart configuration:

```kotlin
data class ComposedChartData(
    val title: String? = null,
    val description: String? = null,
    val categories: List<String>,
    val lineDataSets: List<LineDataSet> = emptyList(),
    val barDataSets: List<BarDataSet> = emptyList(),
    val areaDataSets: List<AreaDataSet> = emptyList(),
    val scatterDataSets: List<ScatterDataSet> = emptyList(),
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val orientation: ChartOrientation = ChartOrientation.VERTICAL
) : ChartData
```

#### BarDataSet
```kotlin
data class BarDataSet(
    val dataKey: String,
    val label: String,
    val dataPoints: List<DataPoint?>,
    val color: Color = Color.Blue,
    val barSize: Float = 20f
)
```

### Component Structure

```
composed/
├── ComposedChart.kt              # Main component
└── variants/
    ├── LineBarAreaComposedChart.kt
    ├── SameDataComposedChart.kt
    ├── VerticalComposedChart.kt
    ├── ComposedChartWithAxisLabels.kt
    ├── ScatterAndLineOfBestFit.kt
    └── BandedChart.kt
```

## Variants Implemented

### 1. Line Bar Area Composed Chart
Combines all four chart types in a single visualization.

**Key Features:**
- Area chart for background trends
- Bar chart for discrete values
- Line chart for continuous data
- Scatter points for individual data points

**Use Case:** Complex data analysis requiring multiple perspectives

### 2. Same Data Composed Chart
Displays the same data using both bars and lines.

**Key Features:**
- Same data rendered as bars and lines
- Helps visualize both discrete and continuous aspects
- Clear comparison between representations

**Use Case:** Showing data in multiple formats for better understanding

### 3. Vertical Composed Chart
Horizontal bars with vertical orientation.

**Key Features:**
- Vertical layout (horizontal bars)
- Swapped X and Y axes
- Compact design for narrow spaces

**Use Case:** Category comparisons with long labels

### 4. Composed Chart With Axis Labels
Enhanced chart with custom axis labels and positioning.

**Key Features:**
- Custom X and Y axis labels
- Configurable label positions
- Enhanced readability

**Use Case:** Professional presentations and reports

### 5. Scatter And Line Of Best Fit
Scatter points with trend lines.

**Key Features:**
- Multiple scatter data sets
- Lines of best fit
- Different colors for different data series
- No point markers on trend lines

**Use Case:** Statistical analysis and regression visualization

### 6. Banded Chart
Line chart with range bands.

**Key Features:**
- Area representing value ranges
- Line showing actual values
- Support for null values with `connectNulls`
- Dashed grid for better readability

**Use Case:** Showing data with confidence intervals or ranges

## Usage Examples

### Basic Usage

```kotlin
@Composable
fun MyComposedChart() {
    val categories = listOf("A", "B", "C", "D", "E")
    
    ComposedChart(
        data = ComposedChartData(
            title = "My Chart",
            categories = categories,
            lineDataSets = listOf(
                LineDataSet(
                    label = "Line",
                    dataPoints = listOf(
                        DataPoint(0f, 100f),
                        DataPoint(1f, 200f),
                        DataPoint(2f, 150f),
                        DataPoint(3f, 300f),
                        DataPoint(4f, 250f)
                    ),
                    lineColor = Color.Blue
                )
            ),
            barDataSets = listOf(
                BarDataSet(
                    dataKey = "bar",
                    label = "Bar",
                    dataPoints = listOf(
                        DataPoint(0f, 80f),
                        DataPoint(1f, 180f),
                        DataPoint(2f, 130f),
                        DataPoint(3f, 280f),
                        DataPoint(4f, 230f)
                    ),
                    color = Color.Red
                )
            )
        ),
        modifier = Modifier.fillMaxWidth().height(400.dp)
    )
}
```

### Advanced Usage with All Chart Types

```kotlin
@Composable
fun AdvancedComposedChart() {
    val categories = listOf("Jan", "Feb", "Mar", "Apr", "May")
    
    ComposedChart(
        data = ComposedChartData(
            title = "Sales Dashboard",
            categories = categories,
            areaDataSets = listOf(
                AreaDataSet(
                    label = "Target Range",
                    dataPoints = listOf(
                        DataPoint(0f, 1000f),
                        DataPoint(1f, 1200f),
                        DataPoint(2f, 1100f),
                        DataPoint(3f, 1300f),
                        DataPoint(4f, 1400f)
                    ),
                    fillColor = Color.Gray.copy(alpha = 0.2f),
                    lineColor = Color.Gray
                )
            ),
            barDataSets = listOf(
                BarDataSet(
                    dataKey = "sales",
                    label = "Actual Sales",
                    dataPoints = listOf(
                        DataPoint(0f, 800f),
                        DataPoint(1f, 1100f),
                        DataPoint(2f, 1000f),
                        DataPoint(3f, 1250f),
                        DataPoint(4f, 1350f)
                    ),
                    color = Color(0xFF413ea0)
                )
            ),
            lineDataSets = listOf(
                LineDataSet(
                    label = "Trend",
                    dataPoints = listOf(
                        DataPoint(0f, 850f),
                        DataPoint(1f, 1050f),
                        DataPoint(2f, 1100f),
                        DataPoint(3f, 1200f),
                        DataPoint(4f, 1350f)
                    ),
                    lineColor = Color(0xFFff7300)
                )
            ),
            scatterDataSets = listOf(
                ScatterDataSet(
                    label = "Key Events",
                    dataPoints = listOf(
                        ScatterPoint(1f, 1100f),
                        ScatterPoint(3f, 1250f)
                    ),
                    pointColor = Color.Red,
                    pointSize = 8f
                )
            ),
            config = ChartConfig(
                showGrid = true,
                showAxis = true,
                showLegend = true
            ),
            xAxisConfig = AxisConfig(
                axisLabel = "Month",
                showLabels = true
            ),
            yAxisConfig = AxisConfig(
                axisLabel = "Amount ($)",
                showLabels = true
            )
        ),
        modifier = Modifier.fillMaxWidth().height(500.dp)
    )
}
```

## Drawing Layer Order

The chart types are drawn in the following order (bottom to top):
1. **Grid** (background)
2. **Area** (filled backgrounds)
3. **Bar** (discrete values)
4. **Line** (continuous data)
5. **Scatter** (individual points)
6. **Axes** (labels and ticks)

This layering ensures proper visibility and visual hierarchy.

## Coordinate Mapping

### X-Axis Mapping
Categories are distributed evenly across the chart width:
```kotlin
val categoryWidth = chartWidth / categories.size
val x = padding + (index + 0.5f) * categoryWidth
```

### Y-Axis Mapping
Values are mapped linearly from data bounds to canvas coordinates:
```kotlin
val normalizedY = (y - bounds.minY) / (bounds.maxY - bounds.minY)
val canvasY = padding + chartHeight - normalizedY * chartHeight
```

## Configuration Options

### ChartConfig
- `backgroundColor`: Background color of the chart
- `showGrid`: Show/hide Cartesian grid
- `showAxis`: Show/hide axes
- `showLegend`: Show/hide legend
- `chartPadding`: Padding around the chart
- `cartesianGrid`: Grid configuration

### AxisConfig
- `showLabels`: Show/hide axis labels
- `axisLabel`: Label text for the axis
- `labelPosition`: Position of the label
- `axisColor`: Color of axis lines and labels
- `labelTextSize`: Font size for labels
- `labelCount`: Number of labels on the axis

## Performance Considerations

1. **Data Size**: ComposedChart can handle datasets with hundreds of points efficiently
2. **Multiple Data Sets**: Performance scales linearly with the number of data sets
3. **Recomposition**: Uses `remember` and proper state management to minimize recompositions
4. **Canvas Drawing**: All drawing operations are optimized for performance

## Best Practices

### 1. Data Preparation
```kotlin
// Ensure data points align across all chart types
val xValues = listOf(0f, 1f, 2f, 3f, 4f)
val lineData = xValues.map { x -> DataPoint(x, calculateLineValue(x)) }
val barData = xValues.map { x -> DataPoint(x, calculateBarValue(x)) }
```

### 2. Color Selection
```kotlin
// Use distinct colors for different chart types
val areaColor = Color(0xFF8884d8)    // Blue
val barColor = Color(0xFF413ea0)     // Dark blue
val lineColor = Color(0xFFff7300)    // Orange
val scatterColor = Color.Red         // Red
```

### 3. Legend Management
```kotlin
// Keep legend labels concise
val lineSet = LineDataSet(
    label = "Sales",  // Not "Monthly Sales Data 2023"
    // ...
)
```

### 4. Null Handling
```kotlin
// Handle missing data appropriately
val areaData = listOf(
    DataPoint(0f, 100f),
    null,  // Missing data
    DataPoint(2f, 150f)
)
```

## Comparison with Recharts

| Feature | Recharts | Our Implementation | Status |
|---------|----------|-------------------|--------|
| Multiple chart types | ✓ | ✓ | ✅ Complete |
| Cartesian grid | ✓ | ✓ | ✅ Complete |
| Axis labels | ✓ | ✓ | ✅ Complete |
| Legend | ✓ | ✓ | ✅ Complete |
| Tooltips | ✓ | ⚠️ | 🔄 Planned |
| Animations | ✓ | ⚠️ | 🔄 Planned |
| Responsive | ✓ | ✓ | ✅ Complete |
| Custom shapes | ✓ | ✓ | ✅ Complete |

## Future Enhancements

1. **Interactive Features**
   - Hover tooltips showing data values
   - Click events for data points
   - Zoom and pan functionality

2. **Advanced Styling**
   - Gradient fills for areas
   - Pattern fills for bars
   - Custom point shapes

3. **Animation**
   - Entry animations for each chart type
   - Smooth transitions between data updates
   - Staggered animations

4. **Export Options**
   - Save as image
   - Export data as CSV
   - Print-friendly layouts

## Troubleshooting

### Issue: Bars and lines don't align
**Solution**: Ensure all data points use the same x-values (indices)

### Issue: Legend items missing
**Solution**: Check that `showLegend = true` in ChartConfig

### Issue: Chart appears squashed
**Solution**: Set appropriate height modifier: `.height(400.dp)`

### Issue: Axis labels cut off
**Solution**: Increase padding in ChartConfig

## Testing

The ComposedChart component includes several test variants in `ComposedChartExamplesScreen.kt`:

```kotlin
@Composable
fun ComposedChartExamplesScreen() {
    // Shows all 6 implemented variants
}
```

Run this screen to verify all chart variations work correctly.

## References

- [Recharts ComposedChart Documentation](https://recharts.org/en-US/api/ComposedChart)
- [Jetpack Compose Canvas](https://developer.android.com/jetpack/compose/graphics/draw/overview)
- [Material Design Charts](https://material.io/design/communication/data-visualization.html)

## Conclusion

The ComposedChart implementation provides a flexible and powerful way to create complex, multi-layered charts in Jetpack Compose. It matches the core functionality of Recharts while leveraging Compose's declarative approach and performance optimizations.

