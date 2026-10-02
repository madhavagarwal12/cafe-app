package com.babacafe.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomTheme

/**
 * Bloom Design System Section Header
 * Big condensed number + Antonio 700 uppercase title + editorial subtitle
 */
@Composable
fun BloomHeader(
    number: String,
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showDivider: Boolean = true
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing

    Column(modifier = modifier.fillMaxWidth()) {
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(bottom = spacing.s4),
                thickness = spacing.borderHairline,
                color = colors.line
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = number,
                style = typography.display,
                color = colors.text
            )
            Spacer(modifier = Modifier.width(spacing.s4))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title.uppercase(),
                    style = typography.h1,
                    color = colors.textStrong
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = typography.bodySmall,
                        color = colors.textMuted
                    )
                }
            }
        }
    }
}
