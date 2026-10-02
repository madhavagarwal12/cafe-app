package com.babacafe.app.ui.screens.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun MenuItemCard(
    item: MenuItem,
    onClick: () -> Unit,
    onQuickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors

    BloomCard(
        variant = if (item.isBabaSpecial) BloomCardVariant.DEEP else BloomCardVariant.DEFAULT,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        BloomTag(
                            text = item.category.title,
                            hasPollenDot = item.isBabaSpecial
                        )
                        if (item.displayBadge != null) {
                            BloomTag(text = item.displayBadge)
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.name.uppercase(),
                        style = typography.h2,
                        color = BloomCream100
                    )

                    if (item.hindiName.isNotEmpty()) {
                        Text(
                            text = item.hindiName,
                            style = typography.bodySmall,
                            color = colors.textMuted
                        )
                    }
                }

                // Price Badge
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "₹${item.basePrice.toInt()}",
                        style = typography.h1,
                        color = BloomPollen500
                    )
                    if (item.sizeOptions.isNotEmpty()) {
                        Text(
                            text = "STARTING",
                            style = typography.caption,
                            color = colors.textMuted
                        )
                    }
                }
            }

            if (item.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.description,
                    style = typography.bodySmall,
                    color = colors.textMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Size hints
                if (item.sizeOptions.isNotEmpty()) {
                    Text(
                        text = item.sizeOptions.joinToString(" / ") { "${it.label.first()}: ₹${it.price.toInt()}" },
                        style = typography.caption,
                        color = BloomCream200
                    )
                } else if (item.availableStyles.isNotEmpty()) {
                    Text(
                        text = "DRY / GRAVY AVAILABLE",
                        style = typography.caption,
                        color = BloomCream200
                    )
                } else {
                    Text(
                        text = "100% PURE VEG",
                        style = typography.caption,
                        color = colors.textMuted
                    )
                }

                BloomButton(
                    text = if (item.sizeOptions.isNotEmpty() || item.availableStyles.isNotEmpty()) "CUSTOMIZE" else "ADD +",
                    onClick = {
                        if (item.sizeOptions.isNotEmpty() || item.availableStyles.isNotEmpty()) {
                            onClick()
                        } else {
                            onQuickAdd()
                        }
                    },
                    variant = if (item.isBabaSpecial) BloomButtonVariant.ACCENT else BloomButtonVariant.PRIMARY,
                    isSmall = true
                )
            }
        }
    }
}
