# CartesianGrid Configuration Guide

## Overview
All LineChart components now support fully customizable CartesianGrid (inspired by Recharts). You can control every aspect of the grid lines including style, color, width, opacity, and more.

## Grid Line Styles

### Available Styles
- `GridLineStyle.SOLID` - Continuous lines
- `GridLineStyle.DASHED` - Dashed lines with custom patterns
- `GridLineStyle.DOTTED` - Dotted lines (2px dots with 4px spacing)

## CartesianGridConfig Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `showHorizontalLines` | Boolean | true | Show/hide horizontal grid lines |
| `showVerticalLines` | Boolean | true | Show/hide vertical grid lines |
| `horizontalLineStyle` | GridLineStyle | DASHED | Style for horizontal lines |
| `verticalLineStyle` | GridLineStyle | DASHED | Style for vertical lines |
| `horizontalLineColor` | Color | LightGray | Color of horizontal lines |
| `verticalLineColor` | Color | LightGray | Color of vertical lines |
| `horizontalLineWidth` | Float | 1f | Stroke width for horizontal lines |
| `verticalLineWidth` | Float | 1f | Stroke width for vertical lines |
| `horizontalLineAlpha` | Float | 0.3f | Opacity of horizontal lines (0.0-1.0) |
| `verticalLineAlpha` | Float | 0.3f | Opacity of vertical lines (0.0-1.0) |
| `horizontalDashPattern` | FloatArray | [10f, 10f] | Dash pattern for horizontal dashed lines |
| `verticalDashPattern` | FloatArray | [10f, 10f] | Dash pattern for vertical dashed lines |
| `horizontalLineCount` | Int | 5 | Number of horizontal grid lines |
| `verticalLineCount` | Int | 5 | Number of vertical grid lines |

## Usage Examples

### 1. Using Presets

```kotlin
import com.majid2851.charts.domain.model.CartesianGridPresets

// Default Recharts-style grid
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.rechartsStyleGrid()
)

// Solid grid lines
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.solidGrid()
)

// Dashed grid lines
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.dashedGrid()
)

// Dotted grid lines
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.dottedGrid()
)

// Horizontal lines only
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.horizontalOnlyGrid()
)

// Vertical lines only
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.verticalOnlyGrid()
)
```

### 2. Custom Configuration

```kotlin
SimpleLineChart(
    cartesianGridConfig = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        verticalLineStyle = GridLineStyle.SOLID,
        horizontalLineColor = Color.Blue,
        verticalLineColor = Color.Green,
        horizontalLineWidth = 2f,
        verticalLineWidth = 1f,
        horizontalLineAlpha = 0.5f,
        verticalLineAlpha = 0.3f,
        horizontalDashPattern = floatArrayOf(5f, 5f),
        verticalDashPattern = floatArrayOf(10f, 10f),
        horizontalLineCount = 6,
        verticalLineCount = 8
    )
)
```

### 3. Recharts "3 3" Style

```kotlin
SimpleLineChart(
    cartesianGridConfig = CartesianGridPresets.customStrokeDashGrid(
        horizontalPattern = floatArrayOf(3f, 3f),
        verticalPattern = floatArrayOf(3f, 3f),
        color = Color(0xFFCCCCCC)
    )
)
```

### 4. Different Styles for Horizontal and Vertical

```kotlin
SimpleLineChart(
    cartesianGridConfig = CartesianGridConfig(
        showHorizontalLines = true,
        showVerticalLines = true,
        horizontalLineStyle = GridLineStyle.DASHED,
        verticalLineStyle = GridLineStyle.DOTTED,
        horizontalLineColor = Color(0xFFE0E0E0),
        verticalLineColor = Color(0xFFE0E0E0)
    )
)
```

## Available Preset Functions

### CartesianGridPresets Object

| Function | Description |
|----------|-------------|
| `defaultGrid()` | Standard dashed grid (default) |
| `solidGrid(color, alpha)` | Solid lines grid |
| `dashedGrid(color, alpha, dashPattern)` | Dashed lines grid |
| `dottedGrid(color, alpha)` | Dotted lines grid |
| `horizontalOnlyGrid(color, alpha, style)` | Only horizontal lines |
| `verticalOnlyGrid(color, alpha, style)` | Only vertical lines |
| `customStrokeDashGrid(horizontalPattern, verticalPattern, color)` | Custom dash patterns |
| `rechartsStyleGrid()` | Matches Recharts default appearance |

## Integration with All Line Charts

Every line chart type supports CartesianGrid configuration:

- ✅ SimpleLineChart
- ✅ TinyLineChart
- ✅ DashedLineChart
- ✅ LineChartWithReferenceLines
- ✅ LineChartConnectNulls
- ✅ CustomizedDotLineChart
- ✅ CurvedLineChart
- ✅ FilledAreaLineChart
- ✅ MultiSeriesLineChart
- ✅ NegativeValuesLineChart

## Color Customization

You can use any color source:

```kotlin
// Hex colors
horizontalLineColor = Color(0xFF4ECDC4)

// Named colors
horizontalLineColor = Color.Blue

// Theme colors
horizontalLineColor = AppColors.GridColorDefault

// RGB colors
horizontalLineColor = Color(red = 0.3f, green = 0.5f, blue = 0.8f)

// With alpha
horizontalLineColor = Color.Blue.copy(alpha = 0.5f)
```

## Dash Pattern Customization

Dash patterns are defined as FloatArray where:
- Even indices (0, 2, 4...) = line segment lengths
- Odd indices (1, 3, 5...) = gap lengths

Examples:
```kotlin
floatArrayOf(10f, 10f)  // 10px line, 10px gap (default)
floatArrayOf(5f, 5f)    // Short dashes
floatArrayOf(20f, 5f)   // Long dashes, short gaps
floatArrayOf(3f, 3f)    // Recharts style (strokeDasharray="3 3")
floatArrayOf(15f, 3f, 3f, 3f)  // Complex pattern: long dash, short gap, short dash, short gap
```

## Performance Considerations

- Higher `lineCount` values = more lines = slightly slower rendering
- Dotted style is slightly more efficient than custom dash patterns
- Solid lines are most efficient
- Alpha blending has minimal performance impact

## Examples in Code

See `CartesianGridExamples.kt` for live examples of all grid configurations.













