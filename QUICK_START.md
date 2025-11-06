# Quick Start Guide

## 🚀 Getting Started with Charts Library

This guide will help you understand and start implementing the chart drawing logic.

## Project Overview

You now have a **complete MVI + Clean Architecture skeleton** for a charts library with 8 chart types. The architecture is ready, but the actual chart drawing logic needs to be implemented.

## What's Ready ✅

1. **Domain Layer** - All data models for 8 chart types
2. **Presentation Layer** - Complete MVI architecture with ViewModels
3. **UI Layer** - All composable chart components (with TODO placeholders)
4. **Demo App** - Working demo screen that shows all charts
5. **Documentation** - README and PROJECT_STRUCTURE docs

## What Needs Implementation 🚧

The actual chart rendering logic using Canvas API. Each chart composable has TODO comments indicating what needs to be done.

## Implementation Order (Recommended)

### 1. Start with Line Chart (Easiest)

**File**: `ui/components/line/LineChart.kt`

**Steps to implement**:

```kotlin
ChartCanvas {
    val width = size.width
    val height = size.height
    
    // Step 1: Calculate scaling
    val maxValue = data.lines.flatMap { it.dataPoints }
        .maxOfOrNull { it.y } ?: 0f
    
    // Step 2: Draw axes
    drawLine(
        color = Color.Gray,
        start = Offset(0f, height),
        end = Offset(width, height),
        strokeWidth = 2f
    )
    
    // Step 3: Draw lines
    data.lines.forEach { lineDataSet ->
        val path = Path()
        lineDataSet.dataPoints.forEachIndexed { index, point ->
            val x = (index / (lineDataSet.dataPoints.size - 1f)) * width
            val y = height - (point.y / maxValue) * height
            
            if (index == 0) path.moveTo(x, y)
            else path.lineTo(x, y)
        }
        
        drawPath(
            path = path,
            color = lineDataSet.lineColor,
            style = Stroke(width = lineDataSet.lineWidth)
        )
    }
    
    // Step 4: Draw points
    // Step 5: Add grid lines
    // Step 6: Add labels
}
```

### 2. Bar Chart (Medium)

**File**: `ui/components/bar/BarChart.kt`

**Key concepts**:
- Calculate bar width based on number of bars
- Handle grouped vs stacked modes
- Draw rectangles with `drawRect()`

### 3. Pie Chart (Medium)

**File**: `ui/components/pie/PieChart.kt`

**Key concepts**:
- Convert values to angles (360° total)
- Use `drawArc()` for slices
- Calculate center point
- Handle donut style (inner circle)

### 4. Area Chart (Medium)

**File**: `ui/components/area/AreaChart.kt`

**Similar to Line Chart but**:
- Use `Path.lineTo()` to create boundary
- Fill with `drawPath(style = Fill)`

### 5. Scatter Chart (Easy)

**File**: `ui/components/scatter/ScatterChart.kt`

**Key concepts**:
- Plot points using `drawCircle()` or custom shapes
- Scale X and Y independently

### 6. Radar Chart (Hard)

**File**: `ui/components/radar/RadarChart.kt`

**Key concepts**:
- Calculate points in circular pattern
- Use trigonometry (sin, cos)
- Draw web structure first
- Then overlay data polygons

### 7. Candlestick Chart (Hard)

**File**: `ui/components/candlestick/CandlestickChart.kt`

**Key concepts**:
- Draw wicks (high-low lines)
- Draw body rectangles (open-close)
- Color based on bullish/bearish

### 8. Gauge Chart (Medium-Hard)

**File**: `ui/components/gauge/GaugeChart.kt`

**Key concepts**:
- Use `drawArc()` for gauge arc
- Calculate needle angle
- Rotate and draw needle

## Common Canvas Drawing Functions

```kotlin
// Lines
drawLine(color, start, end, strokeWidth)

// Rectangles
drawRect(color, topLeft, size)

// Circles
drawCircle(color, center, radius)

// Arcs (for pie/gauge)
drawArc(color, startAngle, sweepAngle, useCenter)

// Paths (for complex shapes)
val path = Path()
path.moveTo(x, y)
path.lineTo(x, y)
drawPath(path, color, style)

// Text
drawContext.canvas.nativeCanvas.drawText(...)
```

## Adding Animations

Use Compose animation APIs:

```kotlin
val animationProgress by animateFloatAsState(
    targetValue = if (state.isAnimating) 1f else 0f,
    animationSpec = tween(durationMillis = 300)
)

// Then multiply your values by animationProgress
val animatedY = y * animationProgress
```

## Adding Touch Interactions

```kotlin
@Composable
fun LineChart(data: LineChartData, modifier: Modifier) {
    var selectedPoint by remember { mutableStateOf<Int?>(null) }
    
    Box(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                // Calculate which point was tapped
                // Update selectedPoint
            }
        }
    ) {
        ChartCanvas { /* draw chart */ }
    }
}
```

## Testing Your Implementation

1. **Run the app** - `MainActivity` will show the demo screen
2. **Switch between charts** - Use the filter chips to test each chart
3. **Modify sample data** - Edit `ChartDemoScreen.kt` sample data generators
4. **Check different configurations** - Test with different chart configs

## File Structure Reference

```
📂 Implementation Priority:
1. ui/components/line/LineChart.kt          ⭐ Start here
2. ui/components/bar/BarChart.kt
3. ui/components/pie/PieChart.kt
4. ui/components/area/AreaChart.kt
5. ui/components/scatter/ScatterChart.kt
6. ui/components/gauge/GaugeChart.kt
7. ui/components/radar/RadarChart.kt
8. ui/components/candlestick/CandlestickChart.kt
```

## Useful Resources

### Canvas Drawing
- [Jetpack Compose Canvas](https://developer.android.com/jetpack/compose/graphics/draw/overview)
- [DrawScope API](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/drawscope/DrawScope)

### Animations
- [Compose Animations](https://developer.android.com/jetpack/compose/animation)
- [animateFloatAsState](https://developer.android.com/jetpack/compose/animation#animate-as-state)

### Gestures
- [Compose Gestures](https://developer.android.com/jetpack/compose/touch-input/pointer-input)
- [detectTapGestures](https://developer.android.com/reference/kotlin/androidx/compose/foundation/gestures/package-summary#detectTapGestures)

## Tips for Success

1. **Start Simple** - Get basic rendering working first, then add features
2. **Test Frequently** - Run the app after each major change
3. **Use Logs** - Log calculated values to debug positioning
4. **Draw Guide Lines** - Add temporary grid lines to verify coordinate systems
5. **Break Down Complex Charts** - Implement one feature at a time

## Example: Complete Line Chart Implementation

For a complete reference implementation, you can follow this pattern:

```kotlin
@Composable
fun LineChart(data: LineChartData, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(data.config.chartPadding)) {
        // Title
        data.title?.let { Text(it, style = MaterialTheme.typography.titleLarge) }
        
        // Chart
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            ChartCanvas {
                // 1. Calculate dimensions
                val chartWidth = size.width
                val chartHeight = size.height
                val padding = 50f
                
                // 2. Find min/max values
                val allPoints = data.lines.flatMap { it.dataPoints }
                val maxY = allPoints.maxOfOrNull { it.y } ?: 1f
                val maxX = allPoints.maxOfOrNull { it.x } ?: 1f
                
                // 3. Draw grid (if enabled)
                if (data.config.showGrid) {
                    // Draw horizontal lines
                    for (i in 0..5) {
                        val y = padding + (chartHeight - 2 * padding) * i / 5
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(padding, y),
                            end = Offset(chartWidth - padding, y),
                            strokeWidth = 1f
                        )
                    }
                }
                
                // 4. Draw axes
                if (data.config.showAxis) {
                    // X axis
                    drawLine(
                        color = Color.Black,
                        start = Offset(padding, chartHeight - padding),
                        end = Offset(chartWidth - padding, chartHeight - padding),
                        strokeWidth = 2f
                    )
                    // Y axis
                    drawLine(
                        color = Color.Black,
                        start = Offset(padding, padding),
                        end = Offset(padding, chartHeight - padding),
                        strokeWidth = 2f
                    )
                }
                
                // 5. Draw lines
                data.lines.forEach { lineDataSet ->
                    val path = Path()
                    
                    lineDataSet.dataPoints.forEachIndexed { index, point ->
                        val x = padding + ((point.x / maxX) * (chartWidth - 2 * padding))
                        val y = chartHeight - padding - ((point.y / maxY) * (chartHeight - 2 * padding))
                        
                        if (index == 0) {
                            path.moveTo(x, y)
                        } else {
                            if (lineDataSet.isCurved) {
                                // Smooth curve using quadraticBezierTo
                                val prevPoint = lineDataSet.dataPoints[index - 1]
                                val prevX = padding + ((prevPoint.x / maxX) * (chartWidth - 2 * padding))
                                val prevY = chartHeight - padding - ((prevPoint.y / maxY) * (chartHeight - 2 * padding))
                                val midX = (prevX + x) / 2
                                val midY = (prevY + y) / 2
                                path.quadraticBezierTo(prevX, prevY, midX, midY)
                                path.lineTo(x, y)
                            } else {
                                path.lineTo(x, y)
                            }
                        }
                    }
                    
                    // Draw the line
                    drawPath(
                        path = path,
                        color = lineDataSet.lineColor,
                        style = Stroke(
                            width = lineDataSet.lineWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                    
                    // Fill area if enabled
                    if (lineDataSet.fillArea) {
                        val fillPath = Path().apply {
                            addPath(path)
                            lineTo(
                                padding + ((lineDataSet.dataPoints.last().x / maxX) * (chartWidth - 2 * padding)),
                                chartHeight - padding
                            )
                            lineTo(
                                padding + ((lineDataSet.dataPoints.first().x / maxX) * (chartWidth - 2 * padding)),
                                chartHeight - padding
                            )
                            close()
                        }
                        drawPath(
                            path = fillPath,
                            color = lineDataSet.fillColor
                        )
                    }
                    
                    // Draw points if enabled
                    if (lineDataSet.showPoints) {
                        lineDataSet.dataPoints.forEach { point ->
                            val x = padding + ((point.x / maxX) * (chartWidth - 2 * padding))
                            val y = chartHeight - padding - ((point.y / maxY) * (chartHeight - 2 * padding))
                            
                            drawCircle(
                                color = lineDataSet.lineColor,
                                radius = lineDataSet.pointRadius,
                                center = Offset(x, y)
                            )
                        }
                    }
                }
            }
        }
        
        // Legend
        if (data.config.showLegend) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                data.lines.forEach { line ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(line.lineColor)
                        )
                        Text(line.label, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
```

## Next Steps

1. Open `ui/components/line/LineChart.kt`
2. Replace the placeholder with actual drawing code
3. Run the app and test
4. Move to the next chart type
5. Repeat!

Good luck! 🚀

