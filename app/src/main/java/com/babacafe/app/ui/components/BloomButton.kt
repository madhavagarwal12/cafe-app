package com.babacafe.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomPollen600
import com.babacafe.app.ui.theme.BloomTheme

enum class BloomButtonVariant {
    PRIMARY,   // Bone background, Field Blue text
    OUTLINE,   // Transparent bg, Bone border, Bone text
    ACCENT,    // Pollen Yellow bg, Ink text
    GHOST      // Underlined text, transparent bg
}

/**
 * Bloom Design System Button
 * 48px standard height (36px small), square 0dp corners, 1.5px border, uppercase tracking.
 */
@Composable
fun BloomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: BloomButtonVariant = BloomButtonVariant.PRIMARY,
    enabled: Boolean = true,
    isSmall: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    val height = if (isSmall) 36.dp else 48.dp
    val horizontalPadding = if (isSmall) 16.dp else 24.dp

    val bgColor by animateColorAsState(
        targetValue = when (variant) {
            BloomButtonVariant.PRIMARY -> if (isPressed) BloomCream100 else BloomCream200
            BloomButtonVariant.OUTLINE -> if (isPressed) BloomCream200 else Color.Transparent
            BloomButtonVariant.ACCENT -> if (isPressed) BloomPollen600 else BloomPollen500
            BloomButtonVariant.GHOST -> Color.Transparent
        },
        label = "btn_bg"
    )

    val contentColor by animateColorAsState(
        targetValue = when (variant) {
            BloomButtonVariant.PRIMARY -> colors.bg
            BloomButtonVariant.OUTLINE -> if (isPressed) colors.bg else BloomCream200
            BloomButtonVariant.ACCENT -> colors.textInk
            BloomButtonVariant.GHOST -> if (isPressed) BloomCream100 else BloomCream200
        },
        label = "btn_fg"
    )

    val border = when (variant) {
        BloomButtonVariant.PRIMARY -> BorderStroke(spacing.borderFocus, BloomCream200)
        BloomButtonVariant.OUTLINE -> BorderStroke(spacing.borderFocus, BloomCream200)
        BloomButtonVariant.ACCENT -> BorderStroke(spacing.borderFocus, BloomPollen500)
        BloomButtonVariant.GHOST -> null
    }

    val textDecoration = if (variant == BloomButtonVariant.GHOST) TextDecoration.Underline else TextDecoration.None

    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(spacing.radiusSquare),
        color = if (enabled) bgColor else bgColor.copy(alpha = 0.4f),
        contentColor = if (enabled) contentColor else contentColor.copy(alpha = 0.4f),
        border = if (enabled) border else border?.copy(brush = androidx.compose.ui.graphics.SolidColor(BloomCream200.copy(alpha = 0.3f))),
        interactionSource = interactionSource
    ) {
        Box(
            modifier = Modifier.padding(PaddingValues(horizontal = horizontalPadding)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = text.uppercase(),
                    style = if (isSmall) typography.eyebrow else typography.label,
                    color = if (enabled) contentColor else contentColor.copy(alpha = 0.4f),
                    textDecoration = textDecoration
                )

                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIcon()
                }
            }
        }
    }
}
