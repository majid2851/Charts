# Consumer ProGuard rules for charts-library
# These rules will be automatically applied to apps that use this library

# Keep all public chart components and their public methods
-keep public class com.majid2851.charts.ui.components.** {
    public *;
}

# Keep all data model classes and their properties
-keep class com.majid2851.charts.domain.model.** { *; }

# Keep theme classes
-keep class com.majid2851.charts.ui.theme.** { *; }

# Keep enums
-keepclassmembers enum com.majid2851.charts.domain.model.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Compose-related attributes
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Keep Canvas drawing functions
-keepclassmembers class * {
    public void draw*(...);
}


