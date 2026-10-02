package com.babacafe.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Bloom Design System Color Tokens
 * Derived from botanical editorial poster aesthetics:
 * Flat field blue background, bone display typography, petal high-contrast text,
 * and deliberate single pollen yellow accent.
 */
val BloomBlue700 = Color(0xFF355E86) // Deep Blue (Dense text surfaces, bottom bars)
val BloomBlue600 = Color(0xFF3F6E9B) // Press Blue (Hover / pressed states)
val BloomBlue500 = Color(0xFF4E7FAE) // Field Blue (PRIMARY poster field background)
val BloomBlue400 = Color(0xFF6A96C0) // Sky Blue (Lifted surface)
val BloomBlue200 = Color(0xFFB9CFE3) // Mist (Subtle tints & outlines)

val BloomCream100 = Color(0xFFF6F0E7) // Petal (Highest-contrast text, headings)
val BloomCream200 = Color(0xFFEDE2D3) // Bone (Display type, borders, card surface)
val BloomCream300 = Color(0xFFDCCDB8) // Linen (Dividers, muted borders)

val BloomPollen500 = Color(0xFFF0A41C) // Pollen (Primary accent - 1 focal point per view)
val BloomPollen600 = Color(0xFFD9861A) // Amber (Pollen pressed state)
val BloomStem500 = Color(0xFF8C7A3C)   // Stem (Olive accents / organic lines)
val BloomInk900 = Color(0xFF1F3247)    // Ink (Text on cream surfaces)

// Translucent helpers
val BloomLine = Color(0x59EDE2D3) // 35% Bone for hairline dividers
val BloomTextMuted = Color(0xB8EDE2D3) // 72% Bone
val BloomGhostType = Color(0x5FEDE2D3) // ~38% Opacity ghost overlay

@Immutable
data class BloomColors(
    val bg: Color = BloomBlue500,
    val surface: Color = BloomBlue400,
    val surfaceDeep: Color = BloomBlue700,
    val surfaceCream: Color = BloomCream200,
    val text: Color = BloomCream200,
    val textStrong: Color = BloomCream100,
    val textMuted: Color = BloomTextMuted,
    val textInk: Color = BloomInk900,
    val line: Color = BloomLine,
    val accent: Color = BloomPollen500,
    val accentPressed: Color = BloomPollen600,
    val stem: Color = BloomStem500
)

val LocalBloomColors = staticCompositionLocalOf { BloomColors() }
