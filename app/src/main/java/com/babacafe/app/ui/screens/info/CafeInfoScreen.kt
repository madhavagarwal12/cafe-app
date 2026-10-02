package com.babacafe.app.ui.screens.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
import com.babacafe.app.ui.components.BloomFlowerSymbol
import com.babacafe.app.ui.components.BloomHeader
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.components.BloomTopBar
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomInk900
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun CafeInfoScreen(
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    val googleMapsUrl = "https://maps.app.goo.gl/ucXhJN1Q3gN3t85p9"
    val geoUri = "geo:26.8634613,75.791848?q=7A,+Gopalpura+Bypass+Rd,+Vasundhara+Colony,+Gopal+Pura+Mode,+Jaipur"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        BloomTopBar(
            title = "ABOUT & LOCATION",
            showBack = true,
            onBackClick = { onNavigate(Screen.Hero) },
            cartItemCount = 0,
            onCartClick = { onNavigate(Screen.Cart) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Header Story Card
            BloomCard(
                variant = BloomCardVariant.CREAM,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "बाबा CAFÉ & RESTRO",
                                style = typography.h1,
                                color = BloomInk900
                            )
                            Text(
                                text = "JAIPUR'S FAVORITE COFFEE & CHAI HANGOUT",
                                style = typography.caption,
                                color = BloomInk900.copy(alpha = 0.7f)
                            )
                        }

                        BloomFlowerSymbol(
                            size = 64.dp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Baba Café & Restro is a budget-friendly, pure vegetarian cafe situated on Gopalpura Bypass in Jaipur. Renowned for its calm, aesthetic atmosphere, prompt service, and delicious street-style food ranging from authentic Kulhad Chai and Poha to rich pastas, loaded pizzas, and shakes.",
                        style = typography.body,
                        color = BloomInk900
                    )
                }
            }

            // Location & Directions Section
            BloomHeader(
                number = "01",
                title = "EXACT LOCATION",
                subtitle = "Vasundhara Colony, Gopal Pura Mode, Jaipur"
            )

            BloomCard(
                variant = BloomCardVariant.DEEP,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = BloomPollen500,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "7A, Gopalpura Bypass Rd, Vasundhara Colony, Gopal Pura Mode, Jaipur, Rajasthan 302018",
                                style = typography.body,
                                color = BloomCream100
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Coordinates: 26.8634613, 75.791848",
                                style = typography.mono,
                                color = colors.textMuted
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        BloomButton(
                            text = "OPEN GOOGLE MAPS",
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl))
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                                    context.startActivity(geoIntent)
                                }
                            },
                            variant = BloomButtonVariant.ACCENT,
                            modifier = Modifier.weight(1f),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = colors.textInk,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )

                        BloomButton(
                            text = "CALL CAFE",
                            onClick = {
                                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919876543210"))
                                context.startActivity(callIntent)
                            },
                            variant = BloomButtonVariant.OUTLINE,
                            modifier = Modifier.weight(1f),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = null,
                                    tint = BloomCream200,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }
                }
            }

            // Timings & Rating
            BloomHeader(
                number = "02",
                title = "HOURS & RATINGS",
                subtitle = "Serving Jaipur day and night"
            )

            BloomCard(
                variant = BloomCardVariant.DEFAULT,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = BloomPollen500,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "DAILY OPENING HOURS",
                                style = typography.h3,
                                color = BloomCream100
                            )
                        }

                        BloomTag(text = "OPEN TODAY", hasPollenDot = true)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "5:00 AM – 12:00 AM (Midnight)",
                        style = typography.h2,
                        color = BloomCream100
                    )
                    Text(
                        text = "Morning chai & breakfast to late-night bites.",
                        style = typography.bodySmall,
                        color = colors.textMuted
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = spacing.borderHairline,
                        color = colors.line
                    )

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
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "5.0 GOOGLE RATING",
                                style = typography.h3,
                                color = BloomCream100
                            )
                        }

                        Text(
                            text = "20+ VERIFIED REVIEWS",
                            style = typography.caption,
                            color = colors.textMuted
                        )
                    }
                }
            }

            // Amenities / Features Grid
            BloomHeader(
                number = "03",
                title = "FACILITIES & VIBE",
                subtitle = "What to expect when you visit"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BloomTag(text = "Dine-In", modifier = Modifier.weight(1f))
                BloomTag(text = "Takeaway", modifier = Modifier.weight(1f))
                BloomTag(text = "100% Pure Veg", modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BloomTag(text = "Free Parking Lot", modifier = Modifier.weight(1f))
                BloomTag(text = "Street Parking", modifier = Modifier.weight(1f))
                BloomTag(text = "NFC / UPI", modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BloomTag(text = "Solo Friendly", modifier = Modifier.weight(1f))
                BloomTag(text = "Group Hangouts", modifier = Modifier.weight(1f))
                BloomTag(text = "Swiggy Delivery", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
