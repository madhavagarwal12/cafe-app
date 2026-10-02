package com.babacafe.app.ui.screens.hero

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
import com.babacafe.app.ui.components.BloomFlowerSymbol
import com.babacafe.app.ui.components.BloomHeader
import com.babacafe.app.ui.components.BloomKeywordTriad
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.components.BloomTopBar
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomGhostType
import com.babacafe.app.ui.theme.BloomInk900
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomStem500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun HeroScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: HeroViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val bestsellers by viewModel.bestsellers.collectAsState()
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        // Sticky Top Bar
        BloomTopBar(
            title = "बाबा CAFÉ & RESTRO",
            cartItemCount = 0,
            onCartClick = { onNavigate(Screen.Cart) },
            onInfoClick = { onNavigate(Screen.Info) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 32.dp)
        ) {
            // ==========================================
            // 01. SIGNATURE POSTER HERO COMPOSITION
            // ==========================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Top-Left Mega Word
                Text(
                    text = "BABA",
                    style = typography.megaDisplay,
                    color = BloomCream200,
                    modifier = Modifier.align(Alignment.TopStart)
                )

                // Top-Right Footnote Caption
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(130.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    BloomTag(
                        text = "OPEN · 5AM - 12AM",
                        hasPollenDot = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "CASUAL EATERY & CHAI RESTRO AT GOPALPURA BYPASS JAIPUR.",
                        style = typography.caption,
                        color = colors.textMuted,
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )
                }

                // Centered Botanical Subject
                BloomFlowerSymbol(
                    size = 190.dp,
                    showStem = true,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 10.dp)
                )

                // Bottom-Right Mega Word with Ghost Layering
                Row(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "CA",
                        style = typography.megaDisplay,
                        color = BloomGhostType
                    )
                    Text(
                        text = "FÉ",
                        style = typography.megaDisplay,
                        color = BloomCream200
                    )
                }

                // Bottom-Left Micro Story
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .width(140.dp)
                ) {
                    Text(
                        text = "PURE VEG · BUDGET FRIENDLY · STUDENT VIBE",
                        style = typography.caption,
                        color = colors.textMuted
                    )
                }
            }

            // Keyword Triad
            BloomKeywordTriad(
                first = "Calm",
                second = "Fresh",
                third = "Gather",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )

            // CTA Action Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BloomButton(
                    text = "EXPLORE MENU",
                    onClick = { onNavigate(Screen.Menu) },
                    variant = BloomButtonVariant.PRIMARY,
                    modifier = Modifier.weight(1f),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = colors.bg,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )

                BloomButton(
                    text = "DINE-IN",
                    onClick = { onNavigate(Screen.Reservation) },
                    variant = BloomButtonVariant.ACCENT,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ==========================================
            // 02. GOOGLE RATING & HIGHLIGHT STATS
            // ==========================================
            BloomCard(
                variant = BloomCardVariant.CREAM,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = BloomPollen500,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "5.0",
                                style = typography.h1,
                                color = BloomInk900
                            )
                        }

                        BloomTag(
                            text = "GOOGLE VERIFIED",
                            hasPollenDot = true
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "“Great food and taste, peaceful calm atmosphere, fast service and super affordable for daily hangout with friends.”",
                        style = typography.body,
                        color = BloomInk900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "BASED ON 20+ FIVE-STAR CUSTOMER REVIEWS",
                        style = typography.caption,
                        color = BloomInk900.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ==========================================
            // 03. POPULAR & BESTSELLERS SECTION
            // ==========================================
            BloomHeader(
                number = "01",
                title = "SIGNATURE SPECIALS",
                subtitle = "Handcrafted favorites from Baba's kitchen",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(bestsellers) { item ->
                    SpecialItemCard(
                        item = item,
                        onQuickAdd = { viewModel.quickAddToCart(item) },
                        onClick = { onNavigate(Screen.Menu) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ==========================================
            // 04. LOCATION & AMENITIES BANNER
            // ==========================================
            BloomHeader(
                number = "02",
                title = "VISIT THE RESTRO",
                subtitle = "Gopalpura Mode, Jaipur",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            BloomCard(
                variant = BloomCardVariant.DEEP,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { onNavigate(Screen.Info) }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "7A, GOPALPURA BYPASS RD",
                                style = typography.h2,
                                color = BloomCream100
                            )
                            Text(
                                text = "Vasundhara Colony, Gopal Pura Mode, Jaipur (302018)",
                                style = typography.bodySmall,
                                color = colors.textMuted
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Map",
                            tint = BloomPollen500,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BloomTag(text = "Dine-In")
                        BloomTag(text = "Takeaway")
                        BloomTag(text = "Free Parking")
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecialItemCard(
    item: MenuItem,
    onQuickAdd: () -> Unit,
    onClick: () -> Unit
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors

    BloomCard(
        variant = BloomCardVariant.DEFAULT,
        modifier = Modifier
            .width(220.dp)
            .height(210.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BloomTag(
                        text = item.category.title,
                        hasPollenDot = item.isBabaSpecial
                    )
                    Text(
                        text = "₹${item.basePrice.toInt()}",
                        style = typography.h2,
                        color = BloomPollen500
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.name.uppercase(),
                    style = typography.h3,
                    color = BloomCream100,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (item.hindiName.isNotEmpty()) {
                    Text(
                        text = item.hindiName,
                        style = typography.bodySmall,
                        color = colors.textMuted
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    style = typography.caption,
                    color = colors.textMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            BloomButton(
                text = "ADD +",
                onClick = onQuickAdd,
                variant = BloomButtonVariant.OUTLINE,
                isSmall = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
