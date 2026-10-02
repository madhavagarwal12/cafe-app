package com.babacafe.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * Bloom Design System Typography Tokens
 * Three primary typographic voices:
 * 1. Mega Display & Display: Ultra-condensed editorial font for giant architecture type
 * 2. Headings (H1, H2, H3): Bold narrow grotesk for section headers and menu titles
 * 3. Body, Labels & Footnote Captions: Clean sans-serif uppercase/sentence case
 */

// We use SansSerif with condensed/tight tracking for genuine Bloom editorial scale
val DisplayFontFamily = FontFamily.SansSerif
val HeadFontFamily = FontFamily.SansSerif
val BodyFontFamily = FontFamily.SansSerif

@Immutable
data class BloomTypography(
    // Giant Mega Display (Six Caps style)
    val megaDisplay: TextStyle = TextStyle(
        fontFamily = DisplayFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 68.sp,
        lineHeight = 62.sp,
        letterSpacing = (-0.02).em
    ),
    // Section Display Word
    val display: TextStyle = TextStyle(
        fontFamily = DisplayFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 42.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.01).em
    ),
    // H1 Headline (Antonio 700 style)
    val h1: TextStyle = TextStyle(
        fontFamily = HeadFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.02.em
    ),
    // H2 Subheading (Antonio 600 style)
    val h2: TextStyle = TextStyle(
        fontFamily = HeadFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.03.em
    ),
    // H3 Category / Card title (Archivo 600)
    val h3: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.01.em
    ),
    // Label / Keyword (Archivo 500 UPPER)
    val label: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.05.em
    ),
    // Eyebrow / Tag
    val eyebrow: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.14.em
    ),
    // Body text (Archivo 400 sentence case)
    val body: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.01.em
    ),
    // Body Small / Description
    val bodySmall: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.01.em
    ),
    // Museum-style Narrow Edge Footnote Captions (Archivo 400 UPPER)
    val caption: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.04.em
    ),
    // Micro Colophon
    val micro: TextStyle = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 11.sp,
        letterSpacing = 0.06.em
    ),
    // Monospace numeric
    val mono: TextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

val LocalBloomTypography = staticCompositionLocalOf { BloomTypography() }
