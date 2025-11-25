# ❓ Frequently Asked Questions

## General Questions

### What is Compose Charts?

Compose Charts is a comprehensive charting library for Jetpack Compose, inspired by Recharts. It provides 9 different chart types with extensive customization options.

### Is it free to use?

Yes! Compose Charts is open-source and licensed under Apache License 2.0, which means you can use it freely in both personal and commercial projects.

### What Android versions are supported?

Minimum SDK: API 21 (Android 5.0 Lollipop) and above.

### Can I use this in production?

Yes, the library is production-ready and optimized for performance.

## Installation

### How do I add this library to my project?

Add to your `build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

### I'm getting "Cannot resolve symbol" errors

Make sure you:
1. Have synced your Gradle files
2. Are using the correct import statements
3. Have the minimum required Compose version

## Usage

### Why is my chart not showing?

Common causes:
1. **No size specified** - Add `.fillMaxWidth().height(300.dp)` to modifier
2. **Empty data** - Ensure your data lists are not empty
3. **Colors** - Check that colors have sufficient contrast

### How do I change chart colors?

Every chart has color parameters:
```kotlin
SimpleLineChart(
    lineColor = Color.Blue,
    backgroundColor = Color.White,
    gridColor = Color.Gray
)
```

### Can I make charts responsive?

Yes! Use `ResponsiveContainer` or the pre-built responsive variants:
```kotlin
ResponsiveAreaChart(modifier = Modifier.fillMaxWidth())
```

### How do I handle click events?

Most charts support click callbacks:
```kotlin
SimpleBarChart(
    onBarClick = { category, value, index ->
        // Handle click
    }
)
```

### Can I animate charts?

Yes, animations are enabled by default. Control with:
```kotlin
ChartConfig(animationEnabled = true)
```

## Customization

### How do I hide the grid?

```kotlin
SimpleLineChart(
    showGrid = false
)
```

### How do I create a donut chart?

Set the `innerRadius`:
```kotlin
SimplePieChart(
    innerRadius = 0.5f  // 50% = donut hole
)
```

### Can I use gradient colors?

Yes, for area charts:
```kotlin
SimpleAreaChart(
    gradientColors = listOf(Color.Blue, Color.Transparent)
)
```

### How do I customize the legend?

```kotlin
ChartConfig(
    showLegend = true,
    legendPosition = LegendPosition.BOTTOM
)
```

## Performance

### How many data points can I display?

For smooth performance:
- **Line/Area charts**: Up to 200 points
- **Bar charts**: Up to 100 bars
- **Scatter charts**: Up to 500 points

For more data points, consider:
1. Sampling the data
2. Disabling animations
3. Simplifying visual elements

### Charts are lagging

Try:
1. Reduce data points
2. Disable animations: `animationEnabled = false`
3. Simplify line curves: `isCurved = false`
4. Use `LazyColumn` for multiple charts

## Data

### What data format is required?

Most charts use simple lists:
```kotlin
categories = listOf("A", "B", "C")
values = listOf(10f, 20f, 15f)
```

For complex charts, use data models:
```kotlin
dataPoints = listOf(
    DataPoint(x = 0f, y = 10f, label = "Point A")
)
```

### Can I use dynamic data?

Yes! Charts automatically update when data changes:
```kotlin
var values by remember { mutableStateOf(listOf(10f, 20f)) }

SimpleLineChart(
    values = values  // Updates when values change
)
```

### How do I handle null values?

Use nullable data points:
```kotlin
dataPoints = listOf(
    DataPoint(0f, 10f),
    null,  // Gap in chart
    DataPoint(2f, 20f)
)
```

## Styling

### Can I use custom fonts?

Yes, through Material Theme:
```kotlin
MaterialTheme(
    typography = Typography(
        titleLarge = TextStyle(fontFamily = myCustomFont)
    )
) {
    SimpleLineChart(...)
}
```

### How do I match my app theme?

Charts respect Material Theme colors:
```kotlin
val primaryColor = MaterialTheme.colorScheme.primary
SimpleLineChart(lineColor = primaryColor)
```

### Can I rotate labels?

Currently labels are horizontal. Vertical labels are on the roadmap.

## Chart-Specific

### Line Charts

**Q: How do I make smooth curves?**
```kotlin
SimpleLineChart(isCurved = true)
```

**Q: Can I show multiple lines?**
```kotlin
MultiLineChart(
    lines = listOf(line1, line2, line3)
)
```

### Bar Charts

**Q: How do I make horizontal bars?**
```kotlin
SimpleBarChart(
    orientation = ChartOrientation.HORIZONTAL
)
```

**Q: Can I stack bars?**
```kotlin
StackedBarChart(
    barSets = listOf(set1, set2)
)
```

### Pie Charts

**Q: How do I position labels?**
```kotlin
SimplePieChart(
    labelPosition = PieLabelPosition.OUTSIDE
)
```

**Q: Can I create a half-pie?**
```kotlin
SimplePieChart(
    startAngle = 180f,
    endAngle = 360f
)
```

### Scatter Charts

**Q: How do I create bubble charts?**
```kotlin
BubbleChart(
    zAxisConfig = ZAxisConfig(
        dataKey = "z",
        range = Pair(10f, 50f)
    )
)
```

### Composed Charts

**Q: Can I combine different chart types?**
```kotlin
LineBarAreaComposedChart(
    lineDataSets = lines,
    barDataSets = bars,
    areaDataSets = areas
)
```

## Troubleshooting

### Build errors

**Error: "Compose version mismatch"**
- Ensure Compose version is 1.5.0+
- Check `kotlinCompilerExtensionVersion`

**Error: "Cannot access ChartData"**
- Verify correct import statements
- Clean and rebuild project

### Runtime issues

**Chart appears clipped**
- Add proper padding
- Check parent container size
- Verify modifier settings

**Colors not showing**
- Check alpha values
- Verify color contrast
- Test on different themes

### Layout problems

**Chart too small**
- Add explicit size: `.height(300.dp)`
- Use `.fillMaxWidth()`
- Check parent constraints

**Chart overlapping other elements**
- Use proper spacing in layouts
- Add padding to parent
- Check z-index/elevation

## Advanced

### Can I export charts as images?

Not built-in, but you can use Android's view-to-bitmap conversion.

### Can I print charts?

Yes, charts can be included in print layouts.

### Are charts accessible?

Basic accessibility support is included. Enhanced support is planned.

### Can I use this with Compose Multiplatform?

Currently Android-only. Multiplatform support is under consideration.

## Contributing

### How can I contribute?

See our [Contributing Guide](../CONTRIBUTING.md).

### I found a bug, what should I do?

[Create an issue](https://github.com/majid2851/compose-charts/issues) with:
- Description
- Steps to reproduce
- Expected vs actual behavior
- Code sample
- Environment details

### Can I request features?

Yes! [Open a feature request](https://github.com/majid2851/compose-charts/issues/new?template=feature_request.md).

## Support

### Where can I get help?

- 📖 [Documentation](../README.md)
- 💬 [GitHub Discussions](https://github.com/majid2851/compose-charts/discussions)
- 🐛 [Issue Tracker](https://github.com/majid2851/compose-charts/issues)
- 📧 Email: majid2851@gmail.com

### Is there a community?

Join us on:
- GitHub Discussions
- Stack Overflow (tag: compose-charts)

---

**Still have questions?** Feel free to ask on [GitHub Discussions](https://github.com/majid2851/compose-charts/discussions)!

