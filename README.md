# Charts Library - Android

A comprehensive, customizable charts library for Android built with **Jetpack Compose**, **MVI architecture**, and **Clean Architecture** principles.

## 📊 Chart Types

The library includes 8 different chart types:

1. **Line Chart** - Display trends and data over time
2. **Bar Chart** - Compare categorical data (Vertical/Horizontal, Grouped/Stacked)
3. **Pie Chart** - Show proportions (Pie, Donut, Semi-circle)
4. **Area Chart** - Visualize volume over time (Standard, Stacked, Percentage)
5. **Scatter Chart** - Display correlation and distribution (including Bubble charts)
6. **Radar Chart** - Show multi-dimensional data (Spider chart)
7. **Candlestick Chart** - Financial data visualization (OHLC)
8. **Gauge Chart** - Display KPIs and metrics (Semi-circle, Full-circle, Linear)

## 🏗️ Architecture

This project follows **Clean Architecture** + **MVI (Model-View-Intent)** pattern:

```
📦 com.majid2851.charts
├── 📂 domain                    # Business logic & models
│   └── 📂 model
│       ├── ChartData.kt         # Base interfaces
│       ├── ChartConfig.kt       # Common configurations
│       ├── LineChartData.kt
│       ├── BarChartData.kt
│       ├── PieChartData.kt
│       ├── AreaChartData.kt
│       ├── ScatterChartData.kt
│       ├── RadarChartData.kt
│       ├── CandlestickChartData.kt
│       └── GaugeChartData.kt
│
├── 📂 presentation              # MVI ViewModels
│   ├── 📂 base
│   │   ├── ViewState.kt
│   │   ├── ViewIntent.kt
│   │   ├── ViewEffect.kt
│   │   └── MviViewModel.kt     # Base ViewModel for MVI
│   ├── 📂 line
│   │   ├── LineChartContract.kt
│   │   └── LineChartViewModel.kt
│   ├── 📂 bar
│   ├── 📂 pie
│   ├── 📂 area
│   ├── 📂 scatter
│   ├── 📂 radar
│   ├── 📂 candlestick
│   └── 📂 gauge
│
└── 📂 ui                        # Compose UI components
    ├── 📂 components
    │   ├── 📂 base
    │   │   └── BaseChart.kt    # Common utilities
    │   ├── 📂 line
    │   │   └── LineChart.kt
    │   ├── 📂 bar
    │   │   └── BarChart.kt
    │   ├── 📂 pie
    │   │   └── PieChart.kt
    │   ├── 📂 area
    │   │   └── AreaChart.kt
    │   ├── 📂 scatter
    │   │   └── ScatterChart.kt
    │   ├── 📂 radar
    │   │   └── RadarChart.kt
    │   ├── 📂 candlestick
    │   │   └── CandlestickChart.kt
    │   └── 📂 gauge
    │       └── GaugeChart.kt
    ├── 📂 screens
    │   └── ChartDemoScreen.kt  # Demo screen
    └── 📂 theme
```

## 🎯 MVI Pattern

Each chart follows the MVI pattern:

```kotlin
// State - Represents the UI state
data class State(
    val chartData: ChartData? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAnimating: Boolean = false
) : ViewState

// Intent - User actions
sealed class Intent : ViewIntent {
    data class LoadChartData(val data: ChartData) : Intent()
    data class UpdateChartData(val data: ChartData) : Intent()
}

// Effect - One-time events
sealed class Effect : ViewEffect {
    data class ShowError(val error: String) : Effect()
}

// ViewModel
class ChartViewModel : MviViewModel<State, Intent, Effect>(State()) {
    override fun handleIntent(intent: Intent) {
        // Handle user intents
    }
}
```

## 📋 Features

### Core Features
- ✅ **8 Chart Types** - Line, Bar, Pie, Area, Scatter, Radar, Candlestick, Gauge
- ✅ **Jetpack Compose** - Modern UI toolkit
- ✅ **MVI Architecture** - Unidirectional data flow
- ✅ **Clean Architecture** - Separation of concerns
- ✅ **Highly Configurable** - Extensive customization options
- ✅ **Type-safe** - Kotlin with strong typing

### Chart Features (To be implemented)
- ⏳ Touch interactions (pan, zoom, tap)
- ⏳ Smooth animations
- ⏳ Legends (customizable position)
- ⏳ Axis labels & grid lines
- ⏳ Tooltips/data labels
- ⏳ Multiple datasets support
- ⏳ Real-time data updates
- ⏳ Custom colors & themes
- ⏳ Export to image

## 🚀 Usage

### Basic Example - Line Chart

```kotlin
@Composable
fun MyScreen() {
    val lineChartData = LineChartData(
        title = "Sales Over Time",
        lines = listOf(
            LineDataSet(
                label = "Revenue",
                dataPoints = listOf(
                    DataPoint(0f, 100f),
                    DataPoint(1f, 150f),
                    DataPoint(2f, 120f),
                    DataPoint(3f, 180f)
                ),
                lineColor = Color.Blue,
                isCurved = true,
                fillArea = true
            )
        )
    )
    
    LineChart(
        data = lineChartData,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Using with ViewModel (MVI)

```kotlin
@Composable
fun ChartScreen(viewModel: LineChartViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.handleIntent(
            LineChartContract.Intent.LoadChartData(sampleData)
        )
    }
    
    state.chartData?.let { data ->
        LineChart(data = data)
    }
}
```

## 🎨 Customization

### Chart Configuration

```kotlin
val config = ChartConfig(
    showGrid = true,
    showAxis = true,
    showLegend = true,
    animationEnabled = true,
    animationDuration = 300,
    backgroundColor = Color.White,
    chartPadding = 16.dp,
    isInteractive = true
)
```

### Axis Configuration

```kotlin
val axisConfig = AxisConfig(
    showLabels = true,
    showGridLines = true,
    labelCount = 5,
    axisColor = Color.Gray,
    gridColor = Color.LightGray,
    labelTextSize = 12f
)
```

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVI + Clean Architecture
- **State Management**: StateFlow
- **Coroutines**: Kotlin Coroutines
- **Min SDK**: 24
- **Target SDK**: 34

## 📦 Dependencies

```gradle
// Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.foundation:foundation")

// ViewModel & MVI
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 📝 Implementation Status

### ✅ Completed
- [x] Project structure setup
- [x] Domain layer (all chart data models)
- [x] Presentation layer (MVI base + all ViewModels)
- [x] UI layer (all chart component skeletons)
- [x] Demo screen with all chart types
- [x] Clean Architecture implementation
- [x] MVI pattern implementation

### 🚧 To Be Implemented
- [ ] Actual chart drawing logic (Canvas drawing)
- [ ] Touch gesture handling
- [ ] Animation implementations
- [ ] Legend components
- [ ] Axis rendering
- [ ] Grid rendering
- [ ] Label rendering
- [ ] Data point highlighting
- [ ] Zoom and pan functionality
- [ ] Export functionality
- [ ] Unit tests
- [ ] Integration tests
- [ ] Documentation
- [ ] Sample apps

## 🎯 Next Steps

1. **Implement Line Chart Drawing**
   - Draw axes and grid
   - Draw line paths
   - Add point markers
   - Implement curved lines

2. **Add Touch Interactions**
   - Pan gesture
   - Zoom gesture
   - Tap to select data points

3. **Implement Animations**
   - Entry animations
   - Update animations
   - Gesture animations

4. **Add Remaining Features**
   - Legend rendering
   - Tooltips
   - Data labels
   - Export functionality

## 📄 License

This project is available for use by other developers.

## 👨‍💻 Author

Created by majid2851

## 🤝 Contributing

This is a library project open for contributions. Feel free to:
- Report bugs
- Suggest new features
- Submit pull requests
- Improve documentation

## 📞 Contact

For questions or suggestions, please open an issue in the repository.

---

**Note**: This is currently a skeleton project with the complete architecture in place. Chart rendering implementations are marked with TODO comments and need to be implemented.

