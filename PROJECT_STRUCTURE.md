# Project Structure Documentation

## Overview

This document provides a detailed explanation of the Charts Library project structure, following Clean Architecture and MVI (Model-View-Intent) pattern.

## Architecture Layers

### 1. Domain Layer (`domain/`)

The innermost layer containing business logic and models. No dependencies on Android framework.

#### `domain/model/`

**Base Interfaces:**
- `ChartData.kt` - Base interface for all chart types
- `ChartConfig.kt` - Common configuration for charts (colors, animations, padding)
- Common data structures: `DataPoint`, `LabeledEntry`

**Chart-Specific Models:**

1. **LineChartData.kt**
   - Contains: `LineChartData`, `LineDataSet`
   - Features: Multiple lines, curved lines, area fill, point markers

2. **BarChartData.kt**
   - Contains: `BarChartData`, `BarDataSet`
   - Features: Vertical/Horizontal, Grouped/Stacked bars

3. **PieChartData.kt**
   - Contains: `PieChartData`, `PieSlice`
   - Features: Pie/Donut/Semi-circle styles, exploded slices

4. **AreaChartData.kt**
   - Contains: `AreaChartData`, `AreaDataSet`
   - Features: Stacked areas, percentage stacking

5. **ScatterChartData.kt**
   - Contains: `ScatterChartData`, `ScatterDataSet`, `ScatterPoint`
   - Features: Different point shapes, bubble variation

6. **RadarChartData.kt**
   - Contains: `RadarChartData`, `RadarDataSet`
   - Features: Spider web visualization, multiple datasets

7. **CandlestickChartData.kt**
   - Contains: `CandlestickChartData`, `CandleEntry`
   - Features: OHLC data, volume display

8. **GaugeChartData.kt**
   - Contains: `GaugeChartData`, `GaugeRange`
   - Features: Semi/Full circle, linear gauge, color ranges

---

### 2. Presentation Layer (`presentation/`)

Contains ViewModels implementing MVI pattern. Manages UI state and handles user intents.

#### `presentation/base/`

**Core MVI Components:**

1. **ViewState.kt**
   - Base interface for all states
   - Represents the complete UI state at any point in time

2. **ViewIntent.kt**
   - Base interface for all user intents
   - Represents user actions (clicks, gestures, etc.)

3. **ViewEffect.kt**
   - Base interface for one-time events
   - Examples: Navigation, Toast messages, Snackbar

4. **MviViewModel.kt**
   - Base ViewModel class for MVI pattern
   - Manages: State (StateFlow), Effects (Channel)
   - Abstract method: `handleIntent(intent: Intent)`

#### Chart ViewModels Structure

Each chart type follows the same pattern:

**Example: `presentation/line/`**

1. **LineChartContract.kt** - Contains all MVI components:
   ```kotlin
   object LineChartContract {
       data class State(...)       // UI state
       sealed class Intent(...)    // User actions
       sealed class Effect(...)    // Side effects
   }
   ```

2. **LineChartViewModel.kt** - Handles intents and updates state:
   ```kotlin
   class LineChartViewModel : MviViewModel<State, Intent, Effect> {
       override fun handleIntent(intent: Intent) { ... }
   }
   ```

**All Chart ViewModels:**
- `line/` - LineChartContract, LineChartViewModel
- `bar/` - BarChartContract, BarChartViewModel
- `pie/` - PieChartContract, PieChartViewModel
- `area/` - AreaChartContract, AreaChartViewModel
- `scatter/` - ScatterChartContract, ScatterChartViewModel
- `radar/` - RadarChartContract, RadarChartViewModel
- `candlestick/` - CandlestickChartContract, CandlestickChartViewModel
- `gauge/` - GaugeChartContract, GaugeChartViewModel

---

### 3. UI Layer (`ui/`)

Contains Jetpack Compose UI components. Depends on domain and presentation layers.

#### `ui/components/base/`

**BaseChart.kt** - Common utilities:
- `ChartComponent` interface
- `ChartCanvas` - Wrapper around Canvas composable
- `ChartUtils` - Helper functions (normalize, calculate min/max, etc.)

#### `ui/components/[chart-type]/`

Each chart type has its own composable:

1. **LineChart.kt** - `@Composable LineChart(...)`
2. **BarChart.kt** - `@Composable BarChart(...)`
3. **PieChart.kt** - `@Composable PieChart(...)`
4. **AreaChart.kt** - `@Composable AreaChart(...)`
5. **ScatterChart.kt** - `@Composable ScatterChart(...)`
6. **RadarChart.kt** - `@Composable RadarChart(...)`
7. **CandlestickChart.kt** - `@Composable CandlestickChart(...)`
8. **GaugeChart.kt** - `@Composable GaugeChart(...)`

Each chart composable:
- Takes chart data as parameter
- Displays title and description
- Renders the chart (TODO: implement drawing)
- Shows legend if enabled

#### `ui/screens/`

**ChartDemoScreen.kt** - Demo application:
- Shows all chart types
- Allows switching between charts
- Provides sample data for each chart type
- Sample data generators for testing

#### `ui/theme/`

Standard Compose theme files:
- `Color.kt` - Color palette
- `Theme.kt` - Material 3 theme
- `Type.kt` - Typography

---

## Data Flow (MVI)

```
User Action (Intent)
    ↓
ViewModel.handleIntent()
    ↓
Update State / Send Effect
    ↓
StateFlow / Channel
    ↓
Composable observes and recomposes
    ↓
UI Updates
```

### Example Flow:

1. **User taps on a data point**
   ```kotlin
   // UI Layer
   onClick = { 
       viewModel.handleIntent(Intent.SelectDataPoint(index)) 
   }
   ```

2. **ViewModel handles intent**
   ```kotlin
   // Presentation Layer
   override fun handleIntent(intent: Intent) {
       when(intent) {
           is Intent.SelectDataPoint -> {
               setState { copy(selectedPoint = intent.index) }
               sendEffect(Effect.ShowTooltip(intent.index))
           }
       }
   }
   ```

3. **UI observes state**
   ```kotlin
   // UI Layer
   val state by viewModel.state.collectAsState()
   
   LineChart(
       data = state.chartData,
       selectedPoint = state.selectedPoint
   )
   ```

---

## File Organization Rules

### Naming Conventions:
- **Data Models**: `[ChartType]Data.kt` (e.g., `LineChartData.kt`)
- **Contracts**: `[ChartType]Contract.kt` (e.g., `LineChartContract.kt`)
- **ViewModels**: `[ChartType]ViewModel.kt` (e.g., `LineChartViewModel.kt`)
- **Composables**: `[ChartType].kt` (e.g., `LineChart.kt`)

### Package Structure:
- One chart type = One package
- Each package contains: Contract + ViewModel (presentation) OR Composable (ui)

---

## Dependencies Between Layers

```
UI Layer
  ↓ depends on
Presentation Layer
  ↓ depends on
Domain Layer
  ↓ depends on
Nothing (pure Kotlin)
```

**Dependency Rules:**
- Domain layer: No Android dependencies
- Presentation layer: Can use Android lifecycle (ViewModel)
- UI layer: Can use Compose and Android framework

---

## Testing Strategy

### Unit Tests:
- **Domain Models**: Test data validation
- **ViewModels**: Test state transitions and intent handling
- **Utils**: Test calculation functions

### Integration Tests:
- Test ViewModel + Repository interactions
- Test data flow through layers

### UI Tests:
- Test chart rendering
- Test user interactions
- Test animations

---

## Implementation Checklist

### ✅ Completed:
- [x] Domain models for all 8 chart types
- [x] Base MVI architecture (ViewState, ViewIntent, ViewEffect, MviViewModel)
- [x] All 8 chart ViewModels with Contracts
- [x] All 8 chart Composables (skeleton)
- [x] Demo screen with sample data
- [x] MainActivity integration

### 🚧 To Implement:
- [ ] Canvas drawing logic for each chart type
- [ ] Touch gesture handlers
- [ ] Animation implementations
- [ ] Legend components
- [ ] Axis and grid rendering
- [ ] Data validation
- [ ] Error handling
- [ ] Tests

---

## Extension Points

### To Add a New Chart Type:

1. **Create Domain Model**
   - `domain/model/NewChartData.kt`
   - Extend `ChartData` interface

2. **Create Presentation Layer**
   - `presentation/new/NewChartContract.kt`
   - `presentation/new/NewChartViewModel.kt`

3. **Create UI Component**
   - `ui/components/new/NewChart.kt`
   - Implement `@Composable NewChart()`

4. **Add to Demo**
   - Update `ChartDemoScreen.kt`
   - Add sample data generator

---

## Best Practices

1. **State Immutability**: Always use `copy()` to update state
2. **Single Responsibility**: Each class has one clear purpose
3. **Type Safety**: Use sealed classes for Intent and Effect
4. **Composition**: Prefer composition over inheritance
5. **Testability**: Keep business logic in ViewModels, not Composables
6. **Documentation**: Add KDoc comments to public APIs

---

## Resources

- [MVI Architecture](https://hannesdorfmann.com/android/model-view-intent/)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [StateFlow vs SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

---

Last Updated: November 6, 2025

