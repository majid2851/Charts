# Interactive Line Chart - Visual Guide

## What Happens When You Click a Point?

### Before Click (Normal State)

```
      ╱
     ╱ 
    ○   <- Small dot (4dp radius)
   ╱    <- Thin line (2.5dp width)
  ╱     <- Original blue color
 ○
```

**Properties:**
- Point Size: 4dp (small)
- Point Color: Blue (original line color)
- Line Width: 2.5dp (normal)
- Line Color: Blue (original)
- Border: None

---

### After Click (Active State)

```
      ╱
     ╱ 
    ⦿   <- Larger dot (10dp radius)
   ╱    <- Thicker line (3.5dp width)
  ╱     <- Changed to cyan color
 ○
```

**Properties:**
- Point Size: **10dp** (2.5x larger!) ⬆️
- Point Color: **Red** (custom active color) 🔴
- Point Border: **White** (2dp border) ⭕
- Line Width: **3.5dp** (thicker) 📏
- Line Color: **Cyan** (custom active color) 🔵

---

## Side-by-Side Comparison

### Multi-Line Chart Example

#### BEFORE ANY CLICKS
```
Line 1 (Blue):   ○────○────○────○────○
Line 2 (Green):  ○────○────○────○────○
```
- All points same size
- All lines same width
- Original colors maintained

#### AFTER CLICKING POINT ON LINE 1
```
Line 1 (Blue):   ○────⦿────○────○────○  <- Active point is RED, line is CYAN
Line 2 (Green):  ○────○────○────○────○  <- Unchanged
```
- Selected point: LARGER + RED + WHITE BORDER
- Connected line segments: THICKER + CYAN
- Other line: Unchanged

#### AFTER CLICKING POINT ON LINE 2
```
Line 1 (Blue):   ○────○────○────○────○  <- Back to normal
Line 2 (Green):  ○────○────⦿────○────○  <- Active point is YELLOW, line is PINK
```
- Previous selection cleared
- New selection: LARGER + YELLOW + WHITE BORDER
- Line 2 becomes: THICKER + PINK

---

## Real-World Visual Example

### Sales Dashboard Scenario

**Setup:**
- Product A: Blue line
- Product B: Green line
- Active colors: Red points, Cyan lines

**User Action:** *Clicks on March data point for Product A*

```
         Product A Sales (Blue → Cyan when active)
         
Jan  Feb  Mar  Apr  May  Jun
 ○────○────⦿────○────○────○   <- Product A
 │    │    ↑    │    │    │
 │    │    │    │    │    │
 │    │  CLICKED HERE!
 │    │    │
 │    │  Point becomes:
 │    │  - 2.5x BIGGER
 │    │  - RED color
 │    │  - WHITE border
 │    │  
 │    │  Line becomes:
 │    │  - CYAN color
 │    │  - THICKER
 │
 ○────○────○────○────○────○   <- Product B (unchanged)
```

**Result Displayed:**
```
┌─────────────────────────────────┐
│ Selected Point Information      │
│                                 │
│ Product: Product A              │
│ Month: March                    │
│ Value: $9,800                   │
│ Index: 2                        │
└─────────────────────────────────┘
```

---

## Color Transformation Examples

### Example 1: Blue Line
```
NORMAL STATE:
Point: ●  (Blue, 4dp)
Line:  ━  (Blue, 2.5dp)

ACTIVE STATE:
Point: ⦿  (Red, 10dp, white border)
Line:  ━  (Cyan, 3.5dp)
```

### Example 2: Green Line
```
NORMAL STATE:
Point: ●  (Green, 4dp)
Line:  ━  (Green, 2.5dp)

ACTIVE STATE:
Point: ⦿  (Yellow, 10dp, white border)
Line:  ━  (Pink, 3.5dp)
```

### Example 3: Custom Configuration
```
NORMAL STATE:
Point: ●  (Purple, 6dp)
Line:  ━  (Purple, 3dp)

ACTIVE STATE:
Point: ⦿  (Orange, 12dp, gold border)
Line:  ━  (Orange, 5dp)
```

---

## Size Comparison Chart

```
Normal Point:       ●        4dp radius
Active Point:       ⚫       10dp radius
                    ↑
           2.5x bigger!

Normal Line:        ━        2.5dp width
Active Line:        ━        3.5dp width
                    ↑
           1.4x thicker!
```

---

## Border Effect

### Without Border (showActivePointBorder = false)
```
⚫  <- Just a big red dot
```

### With Border (showActivePointBorder = true)
```
⦿  <- Red dot with white ring around it
◯
↑
White border (2dp) makes it pop!
```

---

## Click Detection Zone

The system looks for the nearest point within **50 pixels**:

```
                 50px radius
                    ↓
         ╱    ┌─────────┐
        ╱     │         │
       ●  ←   │    ⊕    │  <- Tap here
      ╱       │         │
     ╱        └─────────┘
    ○
   
If you tap within the circle,
that point becomes active!
```

---

## State Transition Animation

*(Conceptual - no animation implemented yet, but visual change is instant)*

```
CLICK EVENT
     ↓
     
Normal: ●━●  
        │
        ↓ (instant change)
Active: ⦿━⦿  
        ↑
     (point grows, colors change)
```

---

## Multiple Points - Only One Active at a Time

```
Tap Point 2:    ○──⦿──○──○  
                    ↑ active

Tap Point 4:    ○──○──○──⦿  
                         ↑ now active
                (Point 2 returns to normal)
```

---

## Code-to-Visual Mapping

### Default Values
```kotlin
Normal State (LineDataSet):
- pointRadius = 4f          →  ●  small dot
- lineColor = Blue          →  ━  blue line
- lineWidth = 2.5f          →  ━  thin line

Active State (PointInteractionConfig):
- activePointRadius = 10f   →  ⚫  big dot
- activePointColor = Red    →  ⚫  red dot
- activeLineColor = Cyan    →  ━  cyan line
- activeLineWidth = 3.5f    →  ━  thick line
- showActivePointBorder     →  ⦿  white ring
```

---

## Best Visual Practices

### ✅ Good Color Combinations
```
Base: Blue    → Active: Red + Cyan
Base: Green   → Active: Yellow + Pink
Base: Purple  → Active: Orange + Green
Base: Gray    → Active: Black + Red
```

### ❌ Bad Color Combinations
```
Base: Red     → Active: Red         (no contrast!)
Base: Yellow  → Active: White       (hard to see)
Base: Light   → Active: Light       (low contrast)
```

### ✅ Good Size Ratios
```
Normal: 4dp   → Active: 10dp   (2.5x - clear difference)
Normal: 6dp   → Active: 12dp   (2x - good)
Normal: 8dp   → Active: 16dp   (2x - great for large screens)
```

### ❌ Bad Size Ratios
```
Normal: 4dp   → Active: 5dp    (1.25x - barely noticeable)
Normal: 10dp  → Active: 30dp   (3x - too big, obscures data)
```

---

## Checklist for Implementing

When you click a point, verify you see:
- [ ] Point becomes **noticeably larger**
- [ ] Point **changes color** (if activePointColor set)
- [ ] **White border** appears around point (if enabled)
- [ ] **Line changes color** (if activeLineColor set)
- [ ] Line becomes **thicker** (if activeLineWidth set)
- [ ] **Previous selection clears** automatically
- [ ] **Callback fires** (if onPointSelected provided)

---

## Summary

**Visual Changes on Click:**

| Element | Before | After | Change |
|---------|--------|-------|--------|
| Point Size | 4dp | 10dp | 🔼 +150% |
| Point Color | Blue | Red | 🎨 Custom |
| Point Border | None | White 2dp | ⭕ Added |
| Line Width | 2.5dp | 3.5dp | 🔼 +40% |
| Line Color | Blue | Cyan | 🎨 Custom |

The result: **Clear, obvious visual feedback** that makes the chart highly interactive! ✨

