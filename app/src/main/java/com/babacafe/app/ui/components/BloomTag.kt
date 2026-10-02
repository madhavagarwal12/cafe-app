package com.babacafe.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomLine
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

/**
 * Bloom Design System Tag / Pill Component
 * 28px height, 999px pill radius, 1px line border, 11px uppercase tracking, optional 6px Pollen dot.
 */
@Composable
fun BloomTag(
    text: String,
    modifier: Modifier = Modifier,
    hasPollenDot: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors

    val bg = if (isSelected) colors.surfaceCream else Color.Transparent
    val textColor = if (isSelected) colors.textInk else colors.text
    val borderColor = if (isSelected) colors.surfaceCream else colors.line

    val baseModifier = modifier
        .height(28.dp)
        .clip(RoundedCornerShape(999.dp))
        .background(bg)
        .border(1.dp, borderColor, RoundedCornerShape(999.dp))

    val finalModifier = if (onClick != null) {
        baseModifier.clickable { onClick() }
    } else {
        baseModifier
    }

    Box(
        modifier = finalModifier.padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasPollenDot) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(BloomPollen500)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text.uppercase(),
                style = typography.eyebrow,
                color = textColor
            )
        }
    }
}
