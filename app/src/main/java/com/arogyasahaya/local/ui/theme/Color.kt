package com.arogyasahaya.local.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Colors - Deep Teal for high contrast
val DeepTeal = Color(0xFF00695C)
val DeepTealLight = Color(0xFF4DB6AC)
val DeepTealDark = Color(0xFF004D40)
val OnDeepTeal = Color.White

// Secondary Colors - Deep Orange for high contrast
val DeepOrange = Color(0xFFE65100)
val DeepOrangeLight = Color(0xFFFF8A50)
val DeepOrangeDark = Color(0xFFAD2A00)
val OnDeepOrange = Color.White

// Neutral Colors
val BackgroundLight = Color(0xFFFAFAFA)
val SurfaceLight = Color.White
val OnBackgroundLight = Color(0xFF1A1A1A)
val OnSurfaceLight = Color(0xFF1A1A1A)

val BackgroundDark = Color(0xFF121212)
val SurfaceDark = Color(0xFF1E1E1E)
val OnBackgroundDark = Color(0xFFEAEAEA)
val OnSurfaceDark = Color(0xFFEAEAEA)

// Error Colors - High contrast red
val ErrorLight = Color(0xFFB71C1C)
val ErrorLightVariant = Color(0xFFFFCDD2)
val OnErrorLight = Color.White

val ErrorDark = Color(0xFFFF8A80)
val ErrorDarkVariant = Color(0xFF690000)
val OnErrorDark = Color(0xFF690000)

// Container Colors
val PrimaryContainerLight = Color(0xFF80CBC4)
val OnPrimaryContainerLight = Color(0xFF00211E)

val PrimaryContainerDark = Color(0xFF004D40)
val OnPrimaryContainerDark = Color(0xFF80CBC4)

val SecondaryContainerLight = Color(0xFFFFDCC2)
val OnSecondaryContainerLight = Color(0xFF3E1400)

val SecondaryContainerDark = Color(0xFF5A1F00)
val OnSecondaryContainerDark = Color(0xFFFFDCC2)

// Surface Variants for cards and elevated surfaces
val SurfaceVariantLight = Color(0xFFECEFF1)
val OnSurfaceVariantLight = Color(0xFF37474F)

val SurfaceVariantDark = Color(0xFF37474F)
val OnSurfaceVariantDark = Color(0xFFECEFF1)

// Outline and Border Colors
val OutlineLight = Color(0xFF90A4AE)
val OutlineDark = Color(0xFF78909C)

// Light Color Scheme - High Contrast
val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = DeepTeal,
    onPrimary = OnDeepTeal,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = DeepOrange,
    onSecondary = OnDeepOrange,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = DeepTealLight,
    onTertiary = Color(0xFF00332E),
    tertiaryContainer = DeepTealLight,
    onTertiaryContainer = Color(0xFF00211E),
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorLightVariant,
    onErrorContainer = OnErrorLight,
    outline = OutlineLight,
    outlineVariant = Color(0xFFCFD8DC),
    inverseSurface = Color(0xFF263238),
    inverseOnSurface = Color(0xFFF0F4F5),
    inversePrimary = DeepTealLight,
    surfaceTint = DeepTeal
)

// Dark Color Scheme - High Contrast
val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = DeepTealLight,
    onPrimary = Color(0xFF00332E),
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = DeepOrangeLight,
    onSecondary = Color(0xFF3E1400),
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = DeepTeal,
    onTertiary = Color(0xFFB2DFDB),
    tertiaryContainer = DeepTealDark,
    onTertiaryContainer = Color(0xFF80CBC4),
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorDarkVariant,
    onErrorContainer = OnErrorDark,
    outline = OutlineDark,
    outlineVariant = Color(0xFF546E7A),
    inverseSurface = Color(0xFFECEFF1),
    inverseOnSurface = Color(0xFF263238),
    inversePrimary = DeepTeal,
    surfaceTint = DeepTealLight
)
