# Radar Chart Component

## Overview
Radar Chart (Spider Chart) implementation for Android Compose with polar grids and multiple series support.

## Quick Start

```kotlin
@Composable
fun MyRadarChart() {
    RadarChart(
        data = RadarChartData(
            title = "Player Stats",
            labels = listOf("Speed", "Power", "Defense", "Skill", "Accuracy"),
            dataSets = listOf(
                RadarDataSet(
                    label = "Player 1",
                    values = listOf(80f, 90f, 70f, 85f, 75f),
                    lineColor = Color(0xFF8884d8),
                    fillColor = Color(0xFF8884d8),
                    fillOpacity = 0.6f
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
```

## File Structure

```
radar/
├── RadarChart.kt              # Main component
├── README.md                  # This file
└── variants/
    ├── SimpleRadarChart.kt            # Basic radar
    └── SpecifiedDomainRadarChart.kt   # Custom domain
```

## Variants

### 1. Simple Radar Chart
- Single data series
- Polar grid
- Basic configuration

### 2. Specified Domain Radar Chart
- Multiple series
- Custom domain [0-150]
- Angled radius axis
- Legend

## Features

### Polar Grid Types
- **POLYGON**: Straight lines (default)
- **CIRCLE**: Circular grid

### Custom Domain
```kotlin
domain = Pair(0f, 150f)  // Min to max values
```

### Multi-Series
```kotlin
dataSets = listOf(
    RadarDataSet(label = "Series 1", values = data1),
    RadarDataSet(label = "Series 2", values = data2)
)
```

### Interactive
```kotlin
onPointSelected = { setIndex, pointIndex, value ->
    // Handle selection
}
```

## Configuration

### RadarChartData
- `title`: Chart title
- `labels`: Category labels
- `dataSets`: Data series
- `domain`: Value range
- `outerRadius`: Size (0-1)
- `polarGridConfig`: Grid settings
- `polarAngleAxisConfig`: Label settings
- `polarRadiusAxisConfig`: Radial axis

### RadarDataSet
- `label`: Series name
- `values`: Data values
- `lineColor`: Border color
- `fillColor`: Fill color
- `fillOpacity`: Transparency
- `showPoints`: Show dots
- `pointSize`: Dot size

## Common Use Cases
- Performance metrics
- Skill comparisons
- Multi-dimensional data
- Product features
- Survey results
- Game stats

---

**Last Updated:** November 17, 2025  
**Version:** 1.0.0

