# 🤝 Contributing to Compose Charts

Thank you for your interest in contributing to Compose Charts! This document provides guidelines and instructions for contributing.

## Code of Conduct

By participating in this project, you agree to maintain a respectful and inclusive environment. Please be kind and professional in all interactions.

## How Can I Contribute?

### 🐛 Reporting Bugs

Before creating a bug report:
1. **Check existing issues** to avoid duplicates
2. **Use the latest version** of the library
3. **Verify it's not a usage error** by reviewing the documentation

When creating a bug report, include:
- **Clear title** describing the issue
- **Steps to reproduce** the problem
- **Expected behavior** vs actual behavior
- **Screenshots** if applicable
- **Environment details**:
  - Library version
  - Android Studio version
  - Kotlin version
  - Device/Emulator details
- **Code sample** (minimal reproducible example)

**Bug Report Template:**

```markdown
### Description
Brief description of the issue

### Steps to Reproduce
1. Step one
2. Step two
3. Step three

### Expected Behavior
What you expected to happen

### Actual Behavior
What actually happened

### Environment
- Library Version: 1.0.0
- Kotlin Version: 1.9.0
- Android Studio Version: Hedgehog 2023.1.1
- Device: Pixel 6 (Android 14)

### Code Sample
\```kotlin
// Minimal code that reproduces the issue
\```

### Screenshots
(if applicable)
```

### 💡 Suggesting Features

We welcome feature suggestions! Please:
1. **Search existing issues** for similar requests
2. **Describe the use case** - why is this feature needed?
3. **Provide examples** of how it would work
4. **Consider alternatives** - are there existing ways to achieve this?

**Feature Request Template:**

```markdown
### Feature Description
Clear description of the proposed feature

### Use Case
Why is this feature needed? What problem does it solve?

### Proposed API
\```kotlin
// Example of how the feature would be used
\```

### Alternatives Considered
Other ways this could be implemented

### Additional Context
Any other relevant information
```

### 📝 Improving Documentation

Documentation contributions are highly valued! You can help by:
- Fixing typos and grammatical errors
- Adding code examples
- Clarifying confusing sections
- Adding missing documentation
- Creating tutorials and guides

### 💻 Code Contributions

## Development Setup

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Kotlin 1.9.0 or later
- Git

### Setup Steps

1. **Fork the repository**
   ```bash
   # Click "Fork" button on GitHub
   ```

2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/compose-charts.git
   cd compose-charts
   ```

3. **Add upstream remote**
   ```bash
   git remote add upstream https://github.com/majid2851/compose-charts.git
   ```

4. **Open in Android Studio**
   - File → Open → Select the cloned directory
   - Let Gradle sync complete

5. **Run the sample app**
   - Select the `app` configuration
   - Click Run

6. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## Project Structure

```
compose-charts/
├── app/                          # Sample application
│   └── src/main/java/
│       └── com/majid2851/charts/
│           ├── ui/
│           │   ├── components/   # Chart implementations
│           │   │   ├── area/
│           │   │   ├── bar/
│           │   │   ├── line/
│           │   │   ├── pie/
│           │   │   ├── scatter/
│           │   │   ├── radar/
│           │   │   ├── composed/
│           │   │   ├── treemap/
│           │   │   ├── radialbar/
│           │   │   └── base/     # Base chart components
│           │   ├── screens/      # Example screens
│           │   └── theme/        # Theme and styling
│           ├── domain/
│           │   └── model/        # Data models
│           └── utils/            # Utility functions
├── docs/                         # Documentation
└── README.md
```

## Coding Guidelines

### Kotlin Style

Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html):

```kotlin
// ✅ Good
fun calculateTotal(items: List<Item>): Float {
    return items.sumOf { it.price.toDouble() }.toFloat()
}

// ❌ Bad
fun calculate_total(items:List<Item>):Float{
    return items.sumOf{it.price.toDouble()}.toFloat()
}
```

### Compose Best Practices

1. **Use remember for expensive operations**
```kotlin
@Composable
fun MyChart(data: List<DataPoint>) {
    val processedData = remember(data) {
        processData(data) // Expensive operation
    }
    // ...
}
```

2. **Stable parameters**
```kotlin
// ✅ Good - stable parameter
@Composable
fun MyChart(
    data: List<DataPoint>, // Immutable list
    color: Color = Color.Blue // Primitive type
)

// ⚠️ Be careful with lambdas
@Composable
fun MyChart(
    onItemClick: (Int) -> Unit = {} // May cause recomposition
)
```

3. **Use derivedStateOf when appropriate**
```kotlin
@Composable
fun MyChart(data: List<DataPoint>) {
    val maxValue by remember {
        derivedStateOf { data.maxOfOrNull { it.y } ?: 0f }
    }
}
```

### Naming Conventions

- **Files**: `PascalCase` (e.g., `SimpleLineChart.kt`)
- **Classes**: `PascalCase` (e.g., `ChartConfig`)
- **Functions**: `camelCase` (e.g., `calculateScale`)
- **Constants**: `UPPER_SNAKE_CASE` (e.g., `DEFAULT_PADDING`)
- **Composables**: `PascalCase` (e.g., `SimpleLineChart()`)

### Documentation

All public APIs must be documented:

```kotlin
/**
 * A simple line chart for visualizing data trends.
 *
 * @param title The chart title to display
 * @param categories List of category labels for the X-axis
 * @param values List of numeric values for the Y-axis
 * @param lineColor The color of the line
 * @param modifier Compose modifier for styling and layout
 *
 * @sample com.majid2851.charts.samples.SimpleLineChartSample
 */
@Composable
fun SimpleLineChart(
    title: String,
    categories: List<String>,
    values: List<Float>,
    lineColor: Color = Color.Blue,
    modifier: Modifier = Modifier
) {
    // Implementation
}
```

### Testing

1. **Write tests for new features**
```kotlin
@Test
fun `test chart data processing`() {
    val data = listOf(1f, 2f, 3f)
    val result = processChartData(data)
    assertEquals(6f, result.sum())
}
```

2. **Test edge cases**
```kotlin
@Test
fun `test empty data`() {
    val result = processChartData(emptyList())
    assertTrue(result.isEmpty())
}

@Test
fun `test single data point`() {
    val result = processChartData(listOf(5f))
    assertEquals(1, result.size)
}
```

3. **Compose UI tests**
```kotlin
@Test
fun simpleLineChartDisplaysCorrectly() {
    composeTestRule.setContent {
        SimpleLineChart(
            title = "Test Chart",
            categories = listOf("A", "B"),
            values = listOf(10f, 20f)
        )
    }
    
    composeTestRule
        .onNodeWithText("Test Chart")
        .assertIsDisplayed()
}
```

## Pull Request Process

### Before Submitting

1. **Update your branch**
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. **Run tests**
   ```bash
   ./gradlew test
   ```

3. **Check code style**
   ```bash
   ./gradlew ktlintCheck
   ```

4. **Build successfully**
   ```bash
   ./gradlew build
   ```

5. **Test manually** in the sample app

### Creating a Pull Request

1. **Push your branch**
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Open a Pull Request** on GitHub

3. **Fill out the PR template**

**Pull Request Template:**

```markdown
### Description
Brief description of changes

### Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

### Changes Made
- Change 1
- Change 2
- Change 3

### Testing
Describe how you tested your changes

### Screenshots
(if applicable)

### Checklist
- [ ] My code follows the style guidelines
- [ ] I have performed a self-review
- [ ] I have commented my code where needed
- [ ] I have added tests that prove my fix is effective or that my feature works
- [ ] New and existing tests pass locally
- [ ] I have updated the documentation
```

### Review Process

1. **Automated checks** will run (tests, lint, build)
2. **Maintainers will review** your code
3. **Address feedback** by pushing new commits
4. **Once approved**, your PR will be merged

## Adding a New Chart Type

### 1. Create Data Model

```kotlin
// domain/model/NewChartData.kt
data class NewChartData(
    override val title: String? = null,
    override val description: String? = null,
    val dataPoints: List<DataPoint>,
    val config: ChartConfig = ChartConfig()
) : ChartData
```

### 2. Implement Base Chart

```kotlin
// ui/components/newchart/NewChart.kt
@Composable
fun NewChart(
    data: NewChartData,
    modifier: Modifier = Modifier
) {
    // Implementation
}
```

### 3. Create Variant

```kotlin
// ui/components/newchart/variants/SimpleNewChart.kt
@Composable
fun SimpleNewChart(
    title: String = "New Chart",
    dataPoints: List<DataPoint> = getDefaultData(),
    modifier: Modifier = Modifier,
    // ... other parameters
) {
    NewChart(
        data = NewChartData(
            title = title,
            dataPoints = dataPoints
        ),
        modifier = modifier
    )
}
```

### 4. Add Preview

```kotlin
@Preview(showBackground = true)
@Composable
private fun SimpleNewChartPreview() {
    SimpleNewChart()
}
```

### 5. Add to Sample App

```kotlin
// ui/screens/NewChartExamplesScreen.kt
@Composable
fun NewChartExamplesScreen() {
    // Implementation
}
```

### 6. Update Documentation

- Add entry to README.md
- Create `docs/NEW_CHART.md`
- Add screenshots
- Update API reference

## Release Process

(For maintainers only)

1. Update version in `build.gradle.kts`
2. Update `CHANGELOG.md`
3. Create release branch
4. Test thoroughly
5. Create Git tag
6. Publish to Maven Central
7. Create GitHub release with notes

## Community

- 💬 [GitHub Discussions](https://github.com/majid2851/compose-charts/discussions) - Ask questions, share ideas
- 🐛 [Issue Tracker](https://github.com/majid2851/compose-charts/issues) - Report bugs, request features
- 📧 Email: majid2851@gmail.com

## Recognition

Contributors will be recognized in:
- `CONTRIBUTORS.md` file
- Release notes
- GitHub contributors page

## Questions?

If you have questions about contributing, feel free to:
- Open a discussion on GitHub
- Email majid2851@gmail.com
- Check the [FAQ](docs/FAQ.md)

Thank you for contributing to Compose Charts! 🎉

---

**License Note**: By contributing, you agree that your contributions will be licensed under the Apache License 2.0.

