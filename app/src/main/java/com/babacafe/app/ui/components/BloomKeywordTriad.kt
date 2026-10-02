package com.babacafe.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.babacafe.app.ui.theme.BloomTheme

/**
 * Bloom Design System Keyword Triad
 * Three single words on one baseline, spread across width.
 */
@Composable
fun BloomKeywordTriad(
    first: String,
    second: String,
    third: String,
    modifier: Modifier = Modifier
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = first.uppercase(),
            style = typography.label,
            color = colors.text
        )
        Text(
            text = second.uppercase(),
            style = typography.label,
            color = colors.text
        )
        Text(
            text = third.uppercase(),
            style = typography.label,
            color = colors.text
        )
    }
}
