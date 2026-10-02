package com.babacafe.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 8-point spacing scale from Bloom Design System:
 * 4, 8, 12, 16, 24, 32, 48, 64, 96, 128
 */
@Immutable
data class BloomSpacing(
    val s1: Dp = 4.dp,
    val s2: Dp = 8.dp,
    val s3: Dp = 12.dp,
    val s4: Dp = 16.dp,
    val s5: Dp = 24.dp,
    val s6: Dp = 32.dp,
    val s7: Dp = 48.dp,
    val s8: Dp = 64.dp,
    val s9: Dp = 96.dp,
    val s10: Dp = 128.dp,

    // Geometry rules
    val radiusSquare: Dp = 0.dp,
    val radiusPill: Dp = 999.dp,
    val borderHairline: Dp = 1.dp,
    val borderFocus: Dp = 1.5.dp
)

val LocalBloomSpacing = staticCompositionLocalOf { BloomSpacing() }
