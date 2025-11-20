# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep all public chart components
-keep public class com.majid2851.charts.ui.components.** { *; }

# Keep all data models
-keep class com.majid2851.charts.domain.model.** { *; }

# Keep theme classes
-keep class com.majid2851.charts.ui.theme.** { *; }

# Keep Compose annotations
-keepattributes *Annotation*

# Keep generic signatures for Kotlin
-keepattributes Signature

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable


