package com.babacafe.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomBlue700
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomTheme

enum class BloomCardVariant {
    DEFAULT, // Flat field blue with 1px hairline border
    CREAM,   // Bone cream background, ink text
    DEEP     // Deep blue background for dense text / high contrast
}

/**
 * Bloom Design System Card
 * Flat, square 0dp radius, 1px line border, no drop shadows.
 */
@Composable
fun BloomCard(
    modifier: Modifier = Modifier,
    variant: BloomCardVariant = BloomCardVariant.DEFAULT,
    padding: Dp = 16.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing

    val bgColor = when (variant) {
        BloomCardVariant.DEFAULT -> colors.bg
        BloomCardVariant.CREAM -> colors.surfaceCream
        BloomCardVariant.DEEP -> BloomBlue700
    }

    val contentColor = when (variant) {
        BloomCardVariant.DEFAULT -> colors.text
        BloomCardVariant.CREAM -> colors.textInk
        BloomCardVariant.DEEP -> colors.textStrong
    }

    val border = when (variant) {
        BloomCardVariant.DEFAULT -> BorderStroke(spacing.borderHairline, colors.line)
        BloomCardVariant.CREAM -> BorderStroke(spacing.borderHairline, BloomCream200)
        BloomCardVariant.DEEP -> BorderStroke(spacing.borderHairline, colors.line)
    }

    Surface(
        modifier = if (onClick != null) modifier.clickable { onClick() } else modifier,
        shape = RoundedCornerShape(spacing.radiusSquare),
        color = bgColor,
        contentColor = contentColor,
        border = border,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier.padding(padding),
            content = content
        )
    }
}
