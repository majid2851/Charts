# 📊 Compose Charts Library

A comprehensive, production-ready charting library for Jetpack Compose with 9+ chart types and 50+ pre-built variants.

[![](https://img.shields.io/badge/Compose-1.5.1-blue.svg)](https://developer.android.com/jetpack/compose)
[![](https://img.shields.io/badge/Kotlin-1.9+-purple.svg)](https://kotlinlang.org/)
[![](https://img.shields.io/badge/minSdk-24-green.svg)](https://developer.android.com/studio/releases/platforms)
[![](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](LICENSE)

## 🎯 Project Structure

This repository contains:

- **`charts-library/`** - The reusable charting library for developers
- **`app/`** - Demo application with 50+ chart examples

## 📊 Chart Types

The library includes 9 different chart types with multiple variants:

1. **Line Chart** (10+ variants) - Trends, multi-series, curved lines, area fill, zoom/pan
2. **Bar Chart** (6+ variants) - Grouped, stacked, horizontal, biaxial
3. **Pie Chart** (3+ variants) - Standard pie, donut, two-level hierarchical
4. **Area Chart** (9+ variants) - Stacked, percent, smooth curves
5. **Scatter Chart** (6+ variants) - Bubble charts, 3D effects, with labels
6. **Radar Chart** (2+ variants) - Multi-dimensional data visualization
7. **Composed Chart** (8+ variants) - Combine multiple chart types
8. **Radial Bar Chart** - Circular progress and metrics
9. **TreeMap** - Hierarchical data visualization

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

## 📦 Installation

### Option 1: JitPack (Recommended)

Add JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency:

```kotlin
dependencies {
    implementation("com.github.majid2851:charts:1.0.0")
}
```

### Option 2: Local Maven (Testing)

```bash
./gradlew :charts-library:publishToMavenLocal
```

```kotlin
repositories {
    mavenLocal()
}
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

## 🚀 Quick Start

### Simple Line Chart

```kotlin
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.domain.model.LineChartData
import com.majid2851.charts.domain.model.LineDataSet
import com.majid2851.charts.domain.model.DataPoint

@Composable
fun MyChart() {
    LineChart(
        data = LineChartData(
            lines = listOf(
                LineDataSet(
                    label = "Sales",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 100f),
                        DataPoint(x = 1f, y = 200f),
                        DataPoint(x = 2f, y = 150f),
                        DataPoint(x = 3f, y = 300f)
                    ),
                    lineColor = Color(0xFF8884d8),
                    isCurved = true,
                    fillArea = true
                )
            ),
            xAxisLabels = listOf("Jan", "Feb", "Mar", "Apr")
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Bar Chart

```kotlin
import com.majid2851.charts.ui.components.bar.BarChart
import com.majid2851.charts.domain.model.BarChartData
import com.majid2851.charts.domain.model.BarDataSet

@Composable
fun MyBarChart() {
    BarChart(
        data = BarChartData(
            categories = listOf("Q1", "Q2", "Q3", "Q4"),
            datasets = listOf(
                BarDataSet(
                    label = "Revenue",
                    dataPoints = listOf(
                        DataPoint(x = 0f, y = 4000f),
                        DataPoint(x = 1f, y = 3000f),
                        DataPoint(x = 2f, y = 2000f),
                        DataPoint(x = 3f, y = 2780f)
                    ),
                    color = Color(0xFF8884d8)
                )
            )
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Pie Chart

```kotlin
import com.majid2851.charts.ui.components.pie.PieChart
import com.majid2851.charts.domain.model.PieChartData
import com.majid2851.charts.domain.model.PieSlice

@Composable
fun MyPieChart() {
    PieChart(
        data = PieChartData(
            slices = listOf(
                PieSlice(name = "A", value = 400f, color = Color(0xFF0088FE)),
                PieSlice(name = "B", value = 300f, color = Color(0xFF00C49F)),
                PieSlice(name = "C", value = 300f, color = Color(0xFFFFBB28)),
                PieSlice(name = "D", value = 200f, color = Color(0xFFFF8042))
            ),
            config = PieChartConfig(showLabels = true)
        ),
        modifier = Modifier.size(300.dp)
    )
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

## ✨ Features

### Core Features
- ✅ **9 Chart Types** - Line, Bar, Pie, Area, Scatter, Radar, Composed, Radial Bar, TreeMap
- ✅ **50+ Variants** - Pre-configured examples for common use cases
- ✅ **Jetpack Compose** - Modern declarative UI
- ✅ **Material 3 Support** - Follow Material Design guidelines
- ✅ **Highly Customizable** - Fine-grained control over every aspect
- ✅ **Responsive Design** - Automatic adaptation to screen sizes
- ✅ **Performance Optimized** - Efficient Canvas-based rendering
- ✅ **Interactive Features** - Zoom, pan, tap interactions, crosshair
- ✅ **Complex Compositions** - Combine multiple chart types in one view
- ✅ **Rich Styling** - Colors, gradients, patterns, and more

### Interactive Features
- ✅ Zoom and pan gestures (Line Chart)
- ✅ Tap to highlight data points
- ✅ Crosshair for precise value reading
- ✅ Multi-point selection
- ✅ Drag to show vertical line
- ✅ Touch interactions across all charts

### Visualization Features
- ✅ Curved lines (Bézier interpolation)
- ✅ Dashed lines
- ✅ Area fills with gradients
- ✅ Stacked charts (Bar, Area)
- ✅ Grouped charts (Bar)
- ✅ Reference lines
- ✅ Custom grid patterns
- ✅ Negative value support
- ✅ Connect nulls option
- ✅ Custom point shapes and sizes

## 📚 Documentation

- **[charts-library/README.md](charts-library/README.md)** - Complete library documentation
- **[charts-library/API.md](charts-library/API.md)** - Full API reference
- **[charts-library/QUICK_START.md](charts-library/QUICK_START.md)** - Get started in minutes
- **[LIBRARY_PUBLISHING_GUIDE.md](LIBRARY_PUBLISHING_GUIDE.md)** - How to publish the library

## 🎯 Demo App

The `app/` module contains a comprehensive demo with:
- **50+ chart variants** across all chart types
- **Interactive examples** showing zoom, pan, and tap features
- **Customization demos** for colors, styles, and configurations
- **Responsive examples** showing adaptive layouts
- **Best practices** and recommended patterns

Run the demo app to explore all features:

```bash
./gradlew :app:installDebug
```

## 🚀 For Library Users

### Installation
See [Installation](#installation) section above.

### Quick Start
See [Quick Start](#quick-start) section above.

### Full Documentation
Read the complete documentation in [charts-library/README.md](charts-library/README.md).

### API Reference
Browse the API documentation in [charts-library/API.md](charts-library/API.md).

## 🔧 For Library Developers

### Building the Library

```bash
# Build the library
./gradlew :charts-library:build

# Run tests
./gradlew :charts-library:test

# Publish to local Maven
./gradlew :charts-library:publishToMavenLocal
```

### Publishing

See [LIBRARY_PUBLISHING_GUIDE.md](LIBRARY_PUBLISHING_GUIDE.md) for detailed instructions on:
- Publishing to JitPack
- Publishing to GitHub Packages
- Publishing to Maven Central
- Version management
- CI/CD automation

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

