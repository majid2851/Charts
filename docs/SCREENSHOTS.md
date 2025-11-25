# 📸 Screenshot Guide

This document provides instructions for creating and organizing screenshots for the Compose Charts library.

## Screenshot Requirements

### Image Specifications

- **Format**: PNG (for transparency and quality)
- **Resolution**: 1080x1920 (portrait) or 1920x1080 (landscape)
- **DPI**: 320 (xxhdpi)
- **File Size**: < 500KB (optimize with tools like TinyPNG)
- **Background**: White or light gray for consistency

### Naming Convention

Use descriptive, lowercase names with underscores:

```
simple_line_chart.png
stacked_bar_chart.png
donut_chart_with_legend.png
multi_line_chart_dark_theme.png
```

## Required Screenshots

### 1. Area Charts (`docs/screenshots/area/`)

- [ ] `simple_area_chart.png` - Basic area chart
- [ ] `stacked_area_chart.png` - Multiple stacked areas
- [ ] `percent_area_chart.png` - 100% stacked areas
- [ ] `gradient_area_chart.png` - Area with gradient fill
- [ ] `responsive_area_chart.png` - Responsive container demo

### 2. Bar Charts (`docs/screenshots/bar/`)

- [ ] `simple_bar_chart.png` - Basic vertical bars
- [ ] `horizontal_bar_chart.png` - Horizontal orientation
- [ ] `stacked_bar_chart.png` - Stacked bars
- [ ] `grouped_bar_chart.png` - Side-by-side bars
- [ ] `mixed_bar_chart.png` - Positive and negative values
- [ ] `bar_with_background.png` - Bars with custom background

### 3. Line Charts (`docs/screenshots/line/`)

- [ ] `simple_line_chart.png` - Single line
- [ ] `multi_line_chart.png` - Multiple lines
- [ ] `curved_line_chart.png` - Smooth curves
- [ ] `line_with_dots.png` - Lines with data points
- [ ] `dashed_line_chart.png` - Dashed line style
- [ ] `biaxial_line_chart.png` - Two Y-axes

### 4. Pie Charts (`docs/screenshots/pie/`)

- [ ] `simple_pie_chart.png` - Basic pie
- [ ] `donut_chart.png` - Pie with inner radius
- [ ] `pie_with_labels.png` - Inside labels
- [ ] `pie_with_legend.png` - External legend
- [ ] `semi_donut_chart.png` - Half-circle donut
- [ ] `custom_colors_pie.png` - Custom color palette

### 5. Scatter Charts (`docs/screenshots/scatter/`)

- [ ] `scatter_chart.png` - Basic scatter plot
- [ ] `bubble_chart.png` - 3D bubble chart
- [ ] `scatter_with_labels.png` - Labeled points
- [ ] `scatter_with_cells.png` - Grid background
- [ ] `3d_scatter_chart.png` - Three-dimensional data

### 6. Radar Charts (`docs/screenshots/radar/`)

- [ ] `simple_radar_chart.png` - Single data set
- [ ] `multi_radar_chart.png` - Multiple data sets
- [ ] `specified_domain_radar.png` - Custom domain

### 7. Composed Charts (`docs/screenshots/composed/`)

- [ ] `line_bar_area_chart.png` - All types combined
- [ ] `same_data_composed.png` - Same data, different visualizations
- [ ] `vertical_composed.png` - Vertical orientation
- [ ] `axis_labels_composed.png` - Custom axis labels
- [ ] `scatter_line_best_fit.png` - Scatter with regression line
- [ ] `banded_chart.png` - Range bands with lines
- [ ] `target_price_chart.png` - Price target visualization

### 8. TreeMap Charts (`docs/screenshots/treemap/`)

- [ ] `simple_treemap.png` - Hierarchical rectangles
- [ ] `treemap_with_labels.png` - Labeled sections

### 9. Radial Bar Charts (`docs/screenshots/radialbar/`)

- [ ] `radial_bar_chart.png` - Concentric circular bars
- [ ] `radial_with_legend.png` - With legend

### 10. Responsive Charts (`docs/screenshots/responsive/`)

- [ ] `responsive_area.png`
- [ ] `responsive_composed.png`
- [ ] `responsive_pie.png`

### 11. Theme Examples (`docs/screenshots/themes/`)

- [ ] `light_theme_example.png`
- [ ] `dark_theme_example.png`
- [ ] `custom_theme_example.png`

## How to Capture Screenshots

### Method 1: Android Studio Preview

1. Open the chart's preview composable
2. Right-click on the preview
3. Select "Copy Image"
4. Paste into an image editor
5. Crop to desired size
6. Save as PNG

### Method 2: Android Emulator

1. Run the sample app
2. Navigate to the desired chart
3. Press `Ctrl+S` (Windows/Linux) or `Cmd+S` (Mac)
4. Save the screenshot
5. Crop and optimize

### Method 3: Real Device

1. Run the app on a physical device
2. Take a screenshot:
   - **Android**: Power + Volume Down
   - **iOS**: Power + Volume Up
3. Transfer to computer
4. Crop and optimize

### Method 4: Automated Screenshots

Use the `Screenshot` composable wrapper:

```kotlin
@Composable
fun CaptureScreenshot(name: String, content: @Composable () -> Unit) {
    // Implementation for automated screenshot capture
}
```

## Screenshot Organization

```
docs/
└── screenshots/
    ├── area/
    ├── bar/
    ├── line/
    ├── pie/
    ├── scatter/
    ├── radar/
    ├── composed/
    ├── treemap/
    ├── radialbar/
    ├── responsive/
    └── themes/
```

## Image Optimization

Use these tools to optimize screenshots:

1. **TinyPNG** - https://tinypng.com/
2. **ImageOptim** (Mac) - https://imageoptim.com/
3. **Squoosh** (Web) - https://squoosh.app/

Target: Reduce file size by 50-70% without visible quality loss.

## Best Practices

### Chart Setup for Screenshots

```kotlin
@Preview(
    name = "Simple Line Chart",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 400,
    heightDp = 300
)
@Composable
fun SimpleLineChartScreenshot() {
    SimpleLineChart(
        title = "Monthly Revenue",
        categories = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"),
        values = listOf(4000f, 3000f, 5000f, 7000f, 5000f, 6000f),
        lineColor = Color(0xFF8884d8),
        showGrid = true,
        showLegend = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    )
}
```

### Screenshot Checklist

- [ ] Chart is centered and fully visible
- [ ] Title and labels are readable
- [ ] Legend is visible (if applicable)
- [ ] No debug information showing
- [ ] Consistent padding and margins
- [ ] Clean, professional appearance
- [ ] Representative data (not random)
- [ ] Proper aspect ratio maintained

## Adding Screenshots to README

Once captured, update `README.md`:

```markdown
### Area Charts
<p align="center">
  <img src="docs/screenshots/area/simple_area_chart.png" width="250" alt="Simple Area Chart"/>
  <img src="docs/screenshots/area/stacked_area_chart.png" width="250" alt="Stacked Area Chart"/>
  <img src="docs/screenshots/area/percent_area_chart.png" width="250" alt="Percent Area Chart"/>
</p>
```

## Creating Preview Grid

For comparison screenshots:

```kotlin
@Preview(showBackground = true, widthDp = 800, heightDp = 600)
@Composable
fun ChartComparison() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Light Theme")
            SimpleLineChart(/* ... */)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text("Dark Theme")
            SimpleLineChart(/* ... */)
        }
    }
}
```

## Animated GIFs

For interactive features, create GIFs:

1. Use **ScreenToGif** (Windows) or **Kap** (Mac)
2. Record interaction (clicking, scrolling, etc.)
3. Optimize with **Gifsicle** or online tools
4. Save to `docs/screenshots/animations/`

Example naming: `interactive_pie_chart.gif`

---

**Questions?** Contact majid2851@gmail.com

