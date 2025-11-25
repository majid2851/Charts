# 🚀 Quick Start Guide

Get up and running with Compose Charts in 5 minutes!

## Prerequisites

- Android Studio Arctic Fox or later
- Kotlin 1.9.0+
- Jetpack Compose 1.5.0+
- Minimum SDK 21

## Step 1: Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

Sync your project.

## Step 2: Your First Chart

### Simple Line Chart

Create a new Composable function:

```kotlin
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.majid2851.charts.ui.components.line.variants.SimpleLineChart

@Composable
fun MyFirstChart() {
    SimpleLineChart(
        title = "Monthly Sales",
        categories = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"),
        values = listOf(400f, 300f, 600f, 800f, 500f, 700f),
        lineColor = Color.Blue,
        showGrid = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    )
}
```

### Use in Your Screen

```kotlin
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Your chart
        MyFirstChart()
    }
}
```

## Step 3: Run Your App

Build and run your app. You should see a beautiful line chart!

## Common Chart Examples

### Bar Chart

```kotlin
@Composable
fun SalesBarChart() {
    SimpleBarChart(
        title = "Product Sales",
        categories = listOf("Product A", "Product B", "Product C", "Product D"),
        values = listOf(2400f, 1398f, 9800f, 3908f),
        barColor = Color(0xFF8884d8),
        showGrid = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Pie Chart

```kotlin
@Composable
fun MarketSharePieChart() {
    SimplePieChart(
        title = "Browser Market Share",
        slices = listOf(
            PieSlice("Chrome", 65f, Color(0xFF0088FE)),
            PieSlice("Safari", 20f, Color(0xFF00C49F)),
            PieSlice("Firefox", 10f, Color(0xFFFFBB28)),
            PieSlice("Others", 5f, Color(0xFFFF8042))
        ),
        showLegend = true,
        innerRadius = 0.5f, // Makes it a donut chart
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    )
}
```

### Area Chart

```kotlin
@Composable
fun RevenueAreaChart() {
    SimpleAreaChart(
        title = "Revenue Over Time",
        categories = listOf("Q1", "Q2", "Q3", "Q4"),
        values = listOf(5000f, 7500f, 6200f, 8900f),
        lineColor = Color(0xFF8884d8),
        fillColor = Color(0xFF8884d8),
        fillOpacity = 0.3f,
        isCurved = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Multi-Line Chart

```kotlin
@Composable
fun TemperatureComparisonChart() {
    MultiLineChart(
        title = "Temperature Comparison",
        lines = listOf(
            LineDataSet(
                label = "City A",
                dataPoints = listOf(
                    DataPoint(0f, 20f),
                    DataPoint(1f, 22f),
                    DataPoint(2f, 25f),
                    DataPoint(3f, 23f),
                    DataPoint(4f, 27f)
                ),
                lineColor = Color.Red,
                lineWidth = 2f
            ),
            LineDataSet(
                label = "City B",
                dataPoints = listOf(
                    DataPoint(0f, 15f),
                    DataPoint(1f, 18f),
                    DataPoint(2f, 20f),
                    DataPoint(3f, 19f),
                    DataPoint(4f, 22f)
                ),
                lineColor = Color.Blue,
                lineWidth = 2f
            )
        ),
        categories = listOf("Mon", "Tue", "Wed", "Thu", "Fri"),
        showLegend = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

## Step 4: Customize Your Charts

### Change Colors

```kotlin
SimpleLineChart(
    title = "Custom Colors",
    categories = categories,
    values = values,
    lineColor = Color(0xFFFF6B6B),  // Custom red
    gridColor = Color(0xFFE0E0E0),   // Light gray grid
    backgroundColor = Color(0xFFF5F5F5) // Light background
)
```

### Adjust Sizing

```kotlin
SimpleBarChart(
    title = "Custom Size",
    categories = categories,
    values = values,
    width = 500.dp,    // Custom width
    height = 400.dp,   // Custom height
    barSize = 30f      // Thicker bars
)
```

### Enable Interactions

```kotlin
SimpleBarChart(
    title = "Interactive Chart",
    categories = categories,
    values = values,
    onBarClick = { category, value, index ->
        println("Clicked: $category with value $value at index $index")
        // Show a dialog, navigate, or update state
    }
)
```

### Hide/Show Elements

```kotlin
SimpleLineChart(
    title = "Minimal Chart",
    categories = categories,
    values = values,
    showGrid = false,    // Hide grid
    showAxis = false,    // Hide axis
    showLegend = false,  // Hide legend
    showPoints = true    // Show data points
)
```

## Step 5: Responsive Charts

Make charts adapt to different screen sizes:

```kotlin
@Composable
fun ResponsiveChart() {
    ResponsiveAreaChart(
        areas = listOf(
            AreaDataSet(
                label = "Revenue",
                dataPoints = myDataPoints,
                fillColor = Color(0xFF8884d8)
            )
        ),
        modifier = Modifier
            .fillMaxWidth()  // Takes full width
            .height(300.dp)   // Fixed height
    )
}
```

## Common Patterns

### Dashboard with Multiple Charts

```kotlin
@Composable
fun Dashboard() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Sales Overview", style = MaterialTheme.typography.headlineMedium)
        }
        
        item {
            SimpleLineChart(
                title = "Daily Sales",
                categories = dailyCategories,
                values = dailyValues,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        
        item {
            SimpleBarChart(
                title = "Product Performance",
                categories = productNames,
                values = productSales,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        
        item {
            SimplePieChart(
                title = "Market Share",
                slices = marketSlices,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        }
    }
}
```

### Dynamic Data

```kotlin
@Composable
fun DynamicChart() {
    var data by remember { mutableStateOf(generateRandomData()) }
    
    Column {
        Button(onClick = { data = generateRandomData() }) {
            Text("Refresh Data")
        }
        
        SimpleLineChart(
            title = "Dynamic Data",
            categories = data.categories,
            values = data.values,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}

fun generateRandomData(): ChartData {
    return ChartData(
        categories = listOf("A", "B", "C", "D", "E"),
        values = List(5) { Random.nextFloat() * 100 }
    )
}
```

### Card Wrapper

```kotlin
@Composable
fun ChartCard(
    title: String,
    chart: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            chart()
        }
    }
}

// Usage
ChartCard(title = "Sales") {
    SimpleLineChart(
        categories = categories,
        values = values,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}
```

## Troubleshooting

### Chart Not Showing

1. **Check Modifier**: Ensure you set width and height:
```kotlin
modifier = Modifier
    .fillMaxWidth()
    .height(300.dp)
```

2. **Check Data**: Ensure your data list is not empty:
```kotlin
values = listOf(10f, 20f, 30f) // ✅ Good
values = emptyList() // ❌ Won't show anything
```

3. **Check Colors**: Ensure colors have sufficient contrast:
```kotlin
lineColor = Color.Blue,      // ✅ Visible
backgroundColor = Color.White // ✅ Good contrast
```

### Performance Issues

1. **Limit Data Points**: For smooth performance, limit to 100-200 points
2. **Disable Animations**: Set `animationEnabled = false` for large datasets
3. **Use LazyColumn**: When showing multiple charts

### Layout Issues

```kotlin
// Problem: Chart takes up no space
SimpleLineChart(/* ... */)

// Solution: Add explicit sizing
SimpleLineChart(
    modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)  // ← Important!
)
```

## Next Steps

- 📚 Read the [Full Documentation](../README.md)
- 🎨 Explore [More Examples](EXAMPLES.md)
- 🔧 Check the [API Reference](API_REFERENCE.md)
- 💡 View [Best Practices](BEST_PRACTICES.md)

## Need Help?

- 📖 [FAQ](FAQ.md)
- 🐛 [Report Issues](https://github.com/majid2851/compose-charts/issues)
- 💬 [Discussions](https://github.com/majid2851/compose-charts/discussions)
- 📧 Email: majid2851@gmail.com

---

Happy charting! 📊

