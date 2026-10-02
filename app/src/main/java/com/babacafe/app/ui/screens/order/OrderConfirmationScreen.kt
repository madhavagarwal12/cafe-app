package com.babacafe.app.ui.screens.order

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.babacafe.app.data.repository.OrderRepository
import com.babacafe.app.di.AppContainer
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
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
fun OrderConfirmationScreen(
    onNavigate: (Screen) -> Unit,
    orderRepository: OrderRepository = AppContainer.orderRepository,
    modifier: Modifier = Modifier
) {
    val activeOrder by orderRepository.currentActiveOrder.collectAsState()
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        BloomTopBar(
            title = "ORDER CONFIRMED",
            showBack = false,
            cartItemCount = 0,
            onCartClick = {},
            onInfoClick = { onNavigate(Screen.Info) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Success Banner Card
            BloomCard(
                variant = BloomCardVariant.CREAM,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Success",
                        tint = BloomPollen500,
                        modifier = Modifier.size(54.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "ORDER SENT TO KITCHEN",
                        style = typography.h1,
                        color = BloomInk900
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "TOKEN: ${activeOrder?.tokenNumber ?: "BABA-0000"}",
                        style = typography.display,
                        color = colors.bg
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    BloomTag(
                        text = "${activeOrder?.orderType?.label ?: "DINE-IN"} · ${activeOrder?.tableNumber ?: "COUNTER"}",
                        hasPollenDot = true
                    )
                }
            }

            // Order Receipt Breakdown
            activeOrder?.let { order ->
                BloomCard(
                    variant = BloomCardVariant.DEEP,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "ORDER TIME",
                                style = typography.caption,
                                color = colors.textMuted
                            )
                            Text(
                                text = order.formattedTime,
                                style = typography.bodySmall,
                                color = BloomCream100
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "PAYMENT METHOD",
                                style = typography.caption,
                                color = colors.textMuted
                            )
                            Text(
                                text = order.paymentOption.title,
                                style = typography.bodySmall,
                                color = BloomPollen500
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            thickness = spacing.borderHairline,
                            color = colors.line
                        )

                        Text(
                            text = "ORDERED ITEMS",
                            style = typography.caption,
                            color = colors.textMuted
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        order.items.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${item.quantity}× ${item.menuItem.name}",
                                    style = typography.bodySmall,
                                    color = BloomCream100
                                )
                                Text(
                                    text = "₹${item.totalPrice.toInt()}",
                                    style = typography.mono,
                                    color = BloomCream200
                                )
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 10.dp),
                            thickness = spacing.borderHairline,
                            color = colors.line
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOTAL PAYABLE",
                                style = typography.h2,
                                color = BloomCream100
                            )
                            Text(
                                text = "₹${order.total.toInt()}",
                                style = typography.h1,
                                color = BloomPollen500
                            )
                        }
                    }
                }
            }

            // Next Actions
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BloomButton(
                    text = "ORDER MORE ITEMS",
                    onClick = { onNavigate(Screen.Menu) },
                    variant = BloomButtonVariant.PRIMARY,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.RestaurantMenu,
                            contentDescription = null,
                            tint = colors.bg,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )

                BloomButton(
                    text = "CAFE LOCATION & DIRECTIONS",
                    onClick = { onNavigate(Screen.Info) },
                    variant = BloomButtonVariant.OUTLINE,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = BloomCream200,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }
    }
}
