# Quick Start - Interactive Line Chart

## 🚀 Fastest Way to Get Started

### Copy-Paste Example

```kotlin
import com.majid2851.charts.ui.components.line.InteractiveLineChart
import com.majid2851.charts.domain.model.*

@Composable
fun MyInteractiveChart() {
    InteractiveLineChart(
        data = LineChartData(
            title = "Sales Data",
            lines = listOf(
                LineDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(0f, 100f),
                        DataPoint(1f, 200f),
                        DataPoint(2f, 150f),
                        DataPoint(3f, 300f),
                        DataPoint(4f, 250f)
                    ),
                    lineColor = Color.Blue,
                    interactionConfig = PointInteractionConfig(
                        activePointRadius = 10f,
                        activePointColor = Color.Red,
                        activeLineColor = Color.Red
                    )
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

**That's it!** Click any point to see:
- ⚫ Point becomes **bigger**
- 🔴 Point turns **red**
- ⭕ **White border** appears
- 🔵 Line turns **red** too

---

## 🎯 Key Configuration

### Change Active Colors
```kotlin
interactionConfig = PointInteractionConfig(
    activePointColor = Color.Red,     // Point color when clicked
    activeLineColor = Color.Cyan      // Line color when clicked
)
```

### Change Sizes
```kotlin
interactionConfig = PointInteractionConfig(
    activePointRadius = 12f,          // How big the point gets
    activeLineWidth = 4f              // How thick the line gets
)
```

### Get Click Events
```kotlin
InteractiveLineChart(
    data = myData,
    onPointSelected = { lineIndex, pointIndex, dataPoint ->
        println("Clicked: Line $lineIndex, Point $pointIndex")
        println("Value: ${dataPoint?.y}")
    }
)
```

---

## 📋 Prebuilt Components

### Use Pre-configured Interactive Charts

```kotlin
// Already configured with nice defaults
InteractiveSimpleLineChart(
    activePointColor = Color.Red,
    activeLineColor = Color.Cyan
)
```

See `InteractiveSimpleLineChart.kt` for the ready-to-use component.

---

## 🎨 Quick Color Presets

### Preset 1: Red/Cyan (Vibrant)
```kotlin
activePointColor = Color(0xFFFF6B6B)
activeLineColor = Color(0xFF4ECDC4)
```

### Preset 2: Yellow/Pink (Playful)
```kotlin
activePointColor = Color(0xFFFFD93D)
activeLineColor = Color(0xFFFF6BCB)
```

### Preset 3: Orange/Green (Bold)
```kotlin
activePointColor = Color(0xFFFF8C42)
activeLineColor = Color(0xFF2ECC71)
```

### Preset 4: Keep Original (Subtle)
```kotlin
activePointColor = null  // Uses line color
activeLineColor = null   // Uses line color
activePointRadius = 8f   // Just makes it bigger
```

---

## 🔧 Common Customizations

### Remove Border
```kotlin
showActivePointBorder = false
```

### Bigger Points
```kotlin
activePointRadius = 15f  // Extra large
```

### Thicker Lines
```kotlin
activeLineWidth = 5f  // Very thick
```

### Custom Border Color
```kotlin
activePointBorderColor = Color.Yellow
activePointBorderWidth = 3f
```

---

## 📱 Where to Find Examples

1. **Basic Example**: `InteractiveLineChartExample.kt`
2. **Advanced Example**: `InteractiveSimpleLineChart.kt`
3. **Full Guide**: `INTERACTIVE_LINE_CHART_GUIDE.md`
4. **Visual Guide**: `INTERACTIVE_VISUAL_GUIDE.md`

---

## ✅ Checklist

After implementing, verify:
- [ ] Can click on points
- [ ] Points get bigger when clicked
- [ ] Colors change (if configured)
- [ ] Border appears (if enabled)
- [ ] Line changes color (if configured)
- [ ] Previous selection clears
- [ ] Callback fires (if provided)

---

## 🐛 Troubleshooting

### Nothing happens when I click
- Check: `enableInteraction = true` in config
- Check: Points are within 50px of click

### Colors don't change
- Check: `activePointColor` and `activeLineColor` are set
- If `null`, they use original colors

### Point doesn't get bigger
- Check: `activePointRadius` > `pointRadius`

### Can't see the point
- Try: Larger `activePointRadius` (10f or more)
- Try: High contrast `activePointColor`

---

## 🎓 Learn More

**Full Documentation**: `INTERACTIVE_LINE_CHART_GUIDE.md`  
**Implementation Details**: `INTERACTIVE_FEATURE_SUMMARY.md`  
**Visual Examples**: `INTERACTIVE_VISUAL_GUIDE.md`

---

## 💡 Tips

1. **Use high contrast colors** for active state (e.g., Blue → Red)
2. **Make active points 2-3x larger** than normal
3. **Always enable borders** for better visibility
4. **Test on real device** - touch targets matter!
5. **Use callbacks** to show detailed info in a card/tooltip

---

That's all you need! Start with the copy-paste example and customize from there. 🎉

