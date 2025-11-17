# Area Chart Implementation Guide

## Overview
This document provides a comprehensive guide to the AreaChart implementation in the Charts library, matching the functionality of Recharts Area Charts in Android Compose.

## Table of Contents
1. [Core Components](#core-components)
2. [Data Models](#data-models)
3. [Variants](#variants)
4. [Usage Examples](#usage-examples)
5. [Features](#features)
6. [Customization](#customization)

---

## Core Components

### AreaChart.kt
The main composable that renders area charts with support for:
- Single or multiple area series
- Stacked areas
- Percentage (100% stacked) areas
- Interactive point selection
- Grid and axis rendering
- Custom fill colors and gradients

### AreaChartData.kt
Data models and configuration:
- `AreaChartData`: Main data container
- `AreaDataSet`: Individual area series
- `AreaStackingMode`: Enum for stacking behavior
- `AreaCurveType`: Enum for curve interpolation types

---

## Data Models

### AreaChartData
```kotlin
data class AreaChartData(
    val title: String? = null,
    val description: String? = null,
    val areas: List<AreaDataSet>,
    val config: ChartConfig = ChartConfig(),
    val xAxisConfig: AxisConfig = AxisConfig(),
    val yAxisConfig: AxisConfig = AxisConfig(),
    val stackingMode: AreaStackingMode = AreaStackingMode.NONE,
    val connectNulls: Boolean = false
)
```

### AreaDataSet
```kotlin
data class AreaDataSet(
    val label: String,
    val dataPoints: List<DataPoint?>,
    val lineColor: Color = Color.Blue,
    val fillColor: Color = Color.Blue.copy(alpha = 0.3f),
    val fillBrush: Brush? = null, // For gradient fills
    val lineWidth: Float = 2f,
    val isCurved: Boolean = true,
    val fillOpacity: Float = 0.6f,
    val stackId: String? = null
)
```

### AreaStackingMode
```kotlin
enum class AreaStackingMode {
    NONE,      // Regular area chart
    STACKED,   // Stacked values
    PERCENTAGE // Normalized to 100%
}
```

---

## Variants

### 1. Simple Area Chart
Basic area chart with a single data series.

**File:** `SimpleAreaChart.kt`

**Features:**
- Single area series
- Smooth curves
- Fill under the line
- Interactive point selection

**Usage:**
```kotlin
SimpleAreaChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

**Recharts Equivalent:**
```jsx
<AreaChart data={data}>
  <Area type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />
</AreaChart>
```

---

### 2. Stacked Area Chart
Multiple areas stacked on top of each other.

**File:** `StackedAreaChart.kt`

**Features:**
- Multiple data series
- Values stack on top of each other
- Each area shows cumulative values
- Different colors for each series

**Usage:**
```kotlin
StackedAreaChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

**Recharts Equivalent:**
```jsx
<AreaChart data={data}>
  <Area type="monotone" dataKey="uv" stackId="1" stroke="#8884d8" fill="#8884d8" />
  <Area type="monotone" dataKey="pv" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
</AreaChart>
```

---

### 3. Percent Area Chart
Areas normalized to show percentage distribution (100% stacked).

**File:** `PercentAreaChart.kt`

**Features:**
- Multiple data series normalized to 100%
- Shows proportional distribution
- Y-axis displays percentages
- Ideal for comparing relative contributions

**Usage:**
```kotlin
PercentAreaChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

**Recharts Equivalent:**
```jsx
<AreaChart data={data} stackOffset="expand">
  <Area type="monotone" dataKey="a" stackId="1" stroke="#8884d8" fill="#8884d8" />
  <Area type="monotone" dataKey="b" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
</AreaChart>
```

---

### 4. Area Chart Connect Nulls
Comparison of connecting vs not connecting null/missing values.

**File:** `AreaChartConnectNulls.kt`

**Features:**
- Demonstrates handling of missing data
- Shows two charts side by side
- One with gaps, one with interpolated connections
- Useful for incomplete datasets

**Usage:**
```kotlin
AreaChartConnectNulls(
    modifier = Modifier.fillMaxWidth()
)
```

**Recharts Equivalent:**
```jsx
// Without connectNulls
<Area type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />

// With connectNulls
<Area connectNulls type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />
```

---

### 5. Tiny Area Chart
Minimal sparkline-style chart without axes or grid.

**File:** `TinyAreaChart.kt`

**Features:**
- Compact visualization
- No axes or labels
- No grid lines
- Perfect for dashboards and cards
- Minimal padding

**Usage:**
```kotlin
TinyAreaChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
)
```

**Recharts Equivalent:**
```jsx
<AreaChart width={300} height={100} data={data}>
  <Area type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />
</AreaChart>
```

---

### 6. Area Chart Fill By Value
Dynamic gradient fill based on positive/negative values.

**File:** `AreaChartFillByValue.kt`

**Features:**
- Gradient fill that changes color at zero
- Green for positive values
- Red for negative values
- Smooth color transition
- Ideal for profit/loss visualization

**Usage:**
```kotlin
AreaChartFillByValue(
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

**Implementation:**
```kotlin
// Calculate gradient offset
val dataMax = data.maxOfOrNull { it.y } ?: 0f
val dataMin = data.minOfOrNull { it.y } ?: 0f
val offset = dataMax / (dataMax - dataMin)

// Create gradient brush
val gradientBrush = Brush.verticalGradient(
    0f to Color.Green.copy(alpha = 0.8f),
    offset to Color.Green.copy(alpha = 0.1f),
    offset to Color.Red.copy(alpha = 0.1f),
    1f to Color.Red.copy(alpha = 0.8f)
)
```

**Recharts Equivalent:**
```jsx
<defs>
  <linearGradient id="splitColor" x1="0" y1="0" x2="0" y2="1">
    <stop offset={off} stopColor="green" stopOpacity={1} />
    <stop offset={off} stopColor="red" stopOpacity={1} />
  </linearGradient>
</defs>
<Area type="monotone" dataKey="uv" stroke="#000" fill="url(#splitColor)" />
```

---

### 7. Cardinal Area Chart
Comparison of linear and smooth curved area interpolation.

**File:** `CardinalAreaChart.kt`

**Features:**
- Shows two versions of the same data
- One with linear interpolation
- One with smooth cardinal curves
- Demonstrates curve type differences

**Usage:**
```kotlin
CardinalAreaChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
)
```

**Recharts Equivalent:**
```jsx
import { curveCardinal } from 'd3-shape';
const cardinal = curveCardinal.tension(0.2);

<AreaChart data={data}>
  <Area type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />
  <Area type={cardinal} dataKey="uv" stroke="#82ca9d" fill="#82ca9d" />
</AreaChart>
```

---

### 8. Synchronized Area Charts
Multiple charts with synchronized interactions.

**File:** `SynchronizedAreaChart.kt`

**Features:**
- Two separate charts
- Shared selection state
- Clicking one chart updates both
- Shows corresponding data points
- Useful for comparing related metrics

**Usage:**
```kotlin
SynchronizedAreaChart(
    modifier = Modifier.fillMaxWidth()
)
```

**Recharts Equivalent:**
```jsx
<AreaChart syncId="anyId" data={data}>
  <Area type="monotone" dataKey="uv" stroke="#8884d8" fill="#8884d8" />
</AreaChart>

<AreaChart syncId="anyId" data={data}>
  <Area type="monotone" dataKey="pv" stroke="#82ca9d" fill="#82ca9d" />
</AreaChart>
```

---

## Features

### 1. Interactive Point Selection
Click or tap on chart to select data points:
```kotlin
AreaChart(
    data = areaChartData,
    onPointSelected = { areaIndex, pointIndex, point ->
        // Handle point selection
        println("Selected: ${point?.label} = ${point?.y}")
    }
)
```

### 2. Customizable Appearance
```kotlin
AreaChartData(
    areas = listOf(
        AreaDataSet(
            label = "Sales",
            dataPoints = data,
            lineColor = Color(0xFF8884d8),
            fillColor = Color(0xFF8884d8).copy(alpha = 0.6f),
            lineWidth = 3f,
            isCurved = true,
            fillOpacity = 0.8f
        )
    ),
    config = ChartConfig(
        showGrid = true,
        showAxis = true,
        showLegend = true,
        backgroundColor = Color.White,
        chartPadding = 16.dp
    )
)
```

### 3. Gradient Fills
```kotlin
val gradientBrush = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF8884d8).copy(alpha = 0.8f),
        Color(0xFF8884d8).copy(alpha = 0.2f)
    )
)

AreaDataSet(
    label = "Data",
    dataPoints = data,
    fillBrush = gradientBrush
)
```

### 4. Stacking Modes
```kotlin
// Regular (overlapping)
AreaChartData(
    areas = areas,
    stackingMode = AreaStackingMode.NONE
)

// Stacked (cumulative)
AreaChartData(
    areas = areas,
    stackingMode = AreaStackingMode.STACKED
)

// Percentage (normalized)
AreaChartData(
    areas = areas,
    stackingMode = AreaStackingMode.PERCENTAGE
)
```

### 5. Grid Customization
```kotlin
ChartConfig(
    showGrid = true,
    cartesianGrid = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        horizontalLineColor = Color.LightGray,
        horizontalDashPattern = floatArrayOf(10f, 10f)
    )
)
```

### 6. Axis Configuration
```kotlin
AxisConfig(
    showLabels = true,
    showGridLines = true,
    labelCount = 5,
    axisColor = Color.Gray,
    gridColor = Color.LightGray,
    labelTextSize = 14f
)
```

---

## Usage Examples

### Basic Area Chart
```kotlin
@Composable
fun MyAreaChart() {
    val data = listOf(
        DataPoint(0f, 100f, "Jan"),
        DataPoint(1f, 200f, "Feb"),
        DataPoint(2f, 150f, "Mar"),
        DataPoint(3f, 300f, "Apr")
    )

    AreaChart(
        data = AreaChartData(
            title = "Monthly Sales",
            areas = listOf(
                AreaDataSet(
                    label = "Sales",
                    dataPoints = data,
                    lineColor = Color.Blue,
                    fillColor = Color.Blue.copy(alpha = 0.3f)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

### Multi-Series Stacked Chart
```kotlin
@Composable
fun StackedSalesChart() {
    val product1 = listOf(
        DataPoint(0f, 100f, "Q1"),
        DataPoint(1f, 200f, "Q2"),
        DataPoint(2f, 150f, "Q3"),
        DataPoint(3f, 300f, "Q4")
    )
    
    val product2 = listOf(
        DataPoint(0f, 150f, "Q1"),
        DataPoint(1f, 180f, "Q2"),
        DataPoint(2f, 220f, "Q3"),
        DataPoint(3f, 250f, "Q4")
    )

    AreaChart(
        data = AreaChartData(
            title = "Product Sales by Quarter",
            areas = listOf(
                AreaDataSet(
                    label = "Product A",
                    dataPoints = product1,
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8).copy(alpha = 0.8f),
                    stackId = "1"
                ),
                AreaDataSet(
                    label = "Product B",
                    dataPoints = product2,
                    lineColor = Color(0xFF82ca9d),
                    fillColor = Color(0xFF82ca9d).copy(alpha = 0.8f),
                    stackId = "1"
                )
            ),
            stackingMode = AreaStackingMode.STACKED
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

### Interactive Chart with Custom Handling
```kotlin
@Composable
fun InteractiveAreaChart() {
    var selectedPoint by remember { mutableStateOf<DataPoint?>(null) }
    
    val data = listOf(
        DataPoint(0f, 100f, "Jan"),
        DataPoint(1f, 200f, "Feb"),
        DataPoint(2f, 150f, "Mar")
    )

    Column {
        AreaChart(
            data = AreaChartData(
                areas = listOf(
                    AreaDataSet(
                        label = "Revenue",
                        dataPoints = data,
                        lineColor = Color.Blue,
                        fillColor = Color.Blue.copy(alpha = 0.3f)
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            onPointSelected = { _, _, point ->
                selectedPoint = point
            }
        )
        
        selectedPoint?.let { point ->
            Text(
                text = "${point.label}: ${point.y}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

---

## Customization

### Custom Colors
```kotlin
// Predefined color schemes
val colorScheme1 = listOf(
    Color(0xFF8884d8),
    Color(0xFF82ca9d),
    Color(0xFFffc658)
)

val colorScheme2 = listOf(
    Color(0xFFFF6B6B),
    Color(0xFF4ECDC4),
    Color(0xFF45B7D1)
)
```

### Custom Curve Types
```kotlin
AreaDataSet(
    label = "Data",
    dataPoints = data,
    isCurved = true  // Smooth bezier curves
)

AreaDataSet(
    label = "Data",
    dataPoints = data,
    isCurved = false // Linear interpolation
)
```

### Handling Missing Data
```kotlin
val dataWithGaps = listOf(
    DataPoint(0f, 100f, "A"),
    DataPoint(1f, 200f, "B"),
    null, // Missing data
    DataPoint(3f, 150f, "D")
)

AreaChartData(
    areas = listOf(
        AreaDataSet(
            label = "Data",
            dataPoints = dataWithGaps
        )
    ),
    connectNulls = true // Connect across gaps
)
```

---

## Performance Considerations

1. **Large Datasets**: For datasets with > 100 points, consider:
   - Reducing point density
   - Disabling animations
   - Using simpler curve types

2. **Multiple Series**: When stacking many areas:
   - Limit to 5-7 series for readability
   - Use distinct colors
   - Consider percentage mode for comparison

3. **Interactive Charts**: 
   - Debounce touch events if needed
   - Use efficient state management
   - Consider virtualization for very large datasets

---

## Mapping from Recharts to Compose

| Recharts Feature | Compose Equivalent | Notes |
|-----------------|-------------------|-------|
| `<Area>` | `AreaDataSet` | Single area series |
| `type="monotone"` | `isCurved = true` | Smooth curves |
| `type="linear"` | `isCurved = false` | Straight lines |
| `stackId="1"` | `stackId = "1"` | Group for stacking |
| `connectNulls` | `connectNulls = true` | Handle missing data |
| `fill="url(#gradient)"` | `fillBrush = gradient` | Gradient fills |
| `strokeDasharray` | Not yet implemented | Dashed lines |
| `<CartesianGrid>` | `CartesianGridConfig` | Grid configuration |
| `<XAxis>` / `<YAxis>` | `AxisConfig` | Axis configuration |
| `<Tooltip>` | `onPointSelected` | Interactive callbacks |
| `syncId` | Shared state | Manual synchronization |

---

## Complete Examples Screen

See `AreaChartExamplesScreen.kt` for a comprehensive showcase of all variants with:
- Side-by-side comparisons
- Live interactions
- Code documentation
- Best practice demonstrations

---

## Future Enhancements

Potential improvements:
1. **Animations**: Smooth transitions between data updates
2. **Tooltips**: Built-in tooltip component
3. **Zoom/Pan**: Touch gestures for data exploration
4. **Export**: Save chart as image
5. **Accessibility**: Screen reader support
6. **More Curve Types**: Step, basis, bundle curves
7. **Gradients**: More gradient options and patterns

---

## Summary

The AreaChart implementation provides a complete set of features matching Recharts functionality:

✅ **8 Complete Variants**
- Simple Area Chart
- Stacked Area Chart
- Percent Area Chart
- Connect Nulls Chart
- Tiny Area Chart
- Fill By Value Chart
- Cardinal Area Chart
- Synchronized Charts

✅ **Key Features**
- Interactive point selection
- Multiple stacking modes
- Gradient fills
- Customizable appearance
- Grid and axis support
- Legend support
- Null handling

✅ **Production Ready**
- Clean API
- Type-safe
- Well documented
- Preview support
- Lint error free

---

Last Updated: November 17, 2025
Version: 1.0.0

