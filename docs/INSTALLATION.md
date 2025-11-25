# 📦 Installation Guide

## Requirements

- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 34
- **Kotlin**: 1.9.0+
- **Jetpack Compose**: 1.5.0+
- **Compose Compiler**: 1.5.0+

## Step-by-Step Installation

### 1. Add Repository (if needed)

If you're using a custom Maven repository, add it to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // If published on JitPack
    }
}
```

Or in `build.gradle` (project level) for older projects:

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add Dependency

#### Kotlin DSL (`build.gradle.kts`)

```kotlin
dependencies {
    // Compose Charts Library
    implementation("com.majid2851:compose-charts:1.0.0")
    
    // Required Compose dependencies (if not already included)
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    
    // Required for Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
}
```

#### Groovy DSL (`build.gradle`)

```groovy
dependencies {
    // Compose Charts Library
    implementation 'com.majid2851:compose-charts:1.0.0'
    
    // Required Compose dependencies
    implementation 'androidx.compose.ui:ui:1.5.4'
    implementation 'androidx.compose.material3:material3:1.1.2'
    implementation 'androidx.compose.ui:ui-tooling-preview:1.5.4'
}
```

### 3. Enable Compose

Make sure Compose is enabled in your app's `build.gradle.kts`:

```kotlin
android {
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}
```

### 4. Sync Project

Click **"Sync Now"** in Android Studio to download the library.

### 5. Verify Installation

Create a simple chart to verify the installation:

```kotlin
import com.majid2851.charts.ui.components.line.variants.SimpleLineChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

@Composable
fun TestChart() {
    SimpleLineChart(
        title = "Test Chart",
        categories = listOf("A", "B", "C"),
        values = listOf(10f, 20f, 15f),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

## Alternative Installation Methods

### JitPack (if published)

#### Step 1: Add JitPack repository

```kotlin
// settings.gradle.kts
maven { url = uri("https://jitpack.io") }
```

#### Step 2: Add dependency

```kotlin
dependencies {
    implementation("com.github.majid2851:compose-charts:1.0.0")
}
```

### Maven

```xml
<dependency>
    <groupId>com.majid2851</groupId>
    <artifactId>compose-charts</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Local AAR File

If you have the AAR file locally:

1. Place the AAR file in `app/libs/` directory
2. Add to `build.gradle.kts`:

```kotlin
dependencies {
    implementation(files("libs/compose-charts-1.0.0.aar"))
}
```

## Troubleshooting

### Common Issues

#### Issue: "Cannot resolve symbol 'SimpleLineChart'"

**Solution**: Make sure you've imported the correct package:
```kotlin
import com.majid2851.charts.ui.components.line.variants.SimpleLineChart
```

#### Issue: "Compose compiler version mismatch"

**Solution**: Ensure your Compose compiler version matches the library requirements:
```kotlin
composeOptions {
    kotlinCompilerExtensionVersion = "1.5.0"
}
```

#### Issue: "Duplicate class found"

**Solution**: Exclude conflicting dependencies:
```kotlin
implementation("com.majid2851:compose-charts:1.0.0") {
    exclude(group = "androidx.compose.ui", module = "ui")
}
```

#### Issue: "Minimum SDK version"

**Solution**: Make sure your app's minimum SDK is 21 or higher:
```kotlin
android {
    defaultConfig {
        minSdk = 21
    }
}
```

### Getting Help

If you encounter any issues:

1. Check the [FAQ](FAQ.md)
2. Search [existing issues](https://github.com/majid2851/compose-charts/issues)
3. Create a [new issue](https://github.com/majid2851/compose-charts/issues/new) with:
   - Library version
   - Android Studio version
   - Kotlin version
   - Complete error message
   - Minimal reproducible example

## Next Steps

- Read the [Quick Start Guide](../README.md#-quick-start)
- Explore [Chart Examples](EXAMPLES.md)
- Check out the [API Reference](API_REFERENCE.md)

---

**Need help?** Contact us at majid2851@gmail.com

