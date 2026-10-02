package com.babacafe.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun BloomTopBar(
    title: String,
    modifier: Modifier = Modifier,
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
    cartItemCount: Int = 0,
    onCartClick: () -> Unit = {},
    onInfoClick: (() -> Unit)? = null
) {
    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.bg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBack) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = BloomCream200
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Text(
                    text = title.uppercase(),
                    style = typography.h2,
                    color = BloomCream100
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onInfoClick != null) {
                    IconButton(onClick = onInfoClick) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Cafe Info",
                            tint = BloomCream200
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clickable { onCartClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = "Cart",
                        tint = BloomCream200,
                        modifier = Modifier.size(24.dp)
                    )

                    if (cartItemCount > 0) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.TopEnd)
                                .clip(CircleShape)
                                .background(BloomPollen500),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cartItemCount.toString(),
                                style = typography.micro,
                                color = colors.textInk
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = spacing.borderHairline,
            color = colors.line
        )
    }
}
