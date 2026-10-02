package com.babacafe.app.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babacafe.app.data.model.CartItem
import com.babacafe.app.data.model.OrderType
import com.babacafe.app.data.model.PaymentOption
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
import com.babacafe.app.ui.components.BloomHeader
import com.babacafe.app.ui.components.BloomInput
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.components.BloomTopBar
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun CartScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: CartViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val orderType by viewModel.orderType.collectAsState()
    val tableNumber by viewModel.tableNumber.collectAsState()
    val customerName by viewModel.customerName.collectAsState()
    val customerPhone by viewModel.customerPhone.collectAsState()
    val orderNotes by viewModel.orderNotes.collectAsState()
    val paymentOption by viewModel.paymentOption.collectAsState()

    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    val subtotal = cartItems.sumOf { it.totalPrice }
    val gst = subtotal * 0.05
    val grandTotal = subtotal + gst

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        BloomTopBar(
            title = "ORDER CART",
            showBack = true,
            onBackClick = { onNavigate(Screen.Menu) },
            cartItemCount = cartItems.sumOf { it.quantity },
            onCartClick = {},
            onInfoClick = { onNavigate(Screen.Info) }
        )

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = BloomCream200.copy(alpha = 0.5f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "YOUR ORDER CART IS EMPTY",
                        style = typography.h2,
                        color = BloomCream100
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Select delicious teas, burgers, pastas and shakes from the menu.",
                        style = typography.bodySmall,
                        color = colors.textMuted
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    BloomButton(
                        text = "EXPLORE MENU",
                        onClick = { onNavigate(Screen.Menu) },
                        variant = BloomButtonVariant.ACCENT
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Section 1: Order Mode Selector
                item {
                    BloomHeader(
                        number = "01",
                        title = "ORDER TYPE",
                        subtitle = "Select how you would like to receive your food",
                        showDivider = false
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OrderType.values().forEach { type ->
                            BloomTag(
                                text = type.label,
                                isSelected = orderType == type,
                                onClick = { viewModel.onOrderTypeChange(type) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    if (orderType == OrderType.DINE_IN) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Table 01", "Table 02", "Table 03", "Table 04", "Table 05", "Outdoor").forEach { tbl ->
                                BloomTag(
                                    text = tbl,
                                    isSelected = tableNumber == tbl,
                                    onClick = { viewModel.onTableNumberChange(tbl) }
                                )
                            }
                        }
                    }
                }

                // Section 2: Cart Items
                item {
                    BloomHeader(
                        number = "02",
                        title = "ITEMS (${cartItems.sumOf { it.quantity }})",
                        subtitle = "Review and customize portion counts"
                    )
                }

                items(cartItems) { item ->
                    CartItemRow(
                        item = item,
                        onIncrement = { viewModel.incrementQuantity(item) },
                        onDecrement = { viewModel.decrementQuantity(item) },
                        onRemove = { viewModel.removeItem(item) }
                    )
                }

                // Section 3: Customer Details & Notes
                item {
                    BloomHeader(
                        number = "03",
                        title = "DETAILS & NOTES",
                        subtitle = "Table server and chef notes"
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    BloomInput(
                        value = customerName,
                        onValueChange = { viewModel.onCustomerNameChange(it) },
                        label = "Your Name",
                        placeholder = "e.g. Rahul Sharma"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    BloomInput(
                        value = customerPhone,
                        onValueChange = { viewModel.onCustomerPhoneChange(it) },
                        label = "Phone Number (Optional)",
                        placeholder = "e.g. 9876543210",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    BloomInput(
                        value = orderNotes,
                        onValueChange = { viewModel.onOrderNotesChange(it) },
                        label = "Special Cooking Request",
                        placeholder = "e.g. Extra napkins, less sweet chai"
                    )
                }

                // Section 4: Payment Option (No Payment Gateway as requested)
                item {
                    BloomHeader(
                        number = "04",
                        title = "PAYMENT METHOD",
                        subtitle = "Pay seamlessly without third-party gateways"
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        PaymentOption.values().forEach { option ->
                            val isSelected = paymentOption == option
                            BloomCard(
                                variant = if (isSelected) BloomCardVariant.CREAM else BloomCardVariant.DEFAULT,
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { viewModel.onPaymentOptionChange(option) }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = option.title.uppercase(),
                                            style = typography.h3,
                                            color = if (isSelected) colors.textInk else BloomCream100
                                        )
                                        Text(
                                            text = option.subtitle,
                                            style = typography.caption,
                                            color = if (isSelected) colors.textInk.copy(alpha = 0.7f) else colors.textMuted
                                        )
                                    }

                                    BloomTag(
                                        text = if (isSelected) "SELECTED" else "CHOOSE",
                                        isSelected = isSelected
                                    )
                                }
                            }
                        }
                    }
                }

                // Section 5: Bill Summary
                item {
                    BloomHeader(
                        number = "05",
                        title = "BILL SUMMARY",
                        subtitle = "Direct transparent cafe pricing"
                    )
                    Spacer(modifier = Modifier.height(8.dp))

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
                                    text = "ITEM TOTAL",
                                    style = typography.bodySmall,
                                    color = colors.textMuted
                                )
                                Text(
                                    text = "₹${subtotal.toInt()}",
                                    style = typography.body,
                                    color = BloomCream100
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "GST (5%)",
                                    style = typography.bodySmall,
                                    color = colors.textMuted
                                )
                                Text(
                                    text = "₹${gst.toInt()}",
                                    style = typography.body,
                                    color = BloomCream100
                                )
                            }

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                thickness = spacing.borderHairline,
                                color = colors.line
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "GRAND TOTAL",
                                    style = typography.h2,
                                    color = BloomCream100
                                )
                                Text(
                                    text = "₹${grandTotal.toInt()}",
                                    style = typography.h1,
                                    color = BloomPollen500
                                )
                            }
                        }
                    }
                }

                // CTA Button
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    BloomButton(
                        text = "CONFIRM & PLACE ORDER · ₹${grandTotal.toInt()}",
                        onClick = {
                            val order = viewModel.placeOrder()
                            if (order != null) {
                                onNavigate(Screen.OrderConfirmation)
                            }
                        },
                        variant = BloomButtonVariant.ACCENT,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = colors.textInk,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing

    BloomCard(
        variant = BloomCardVariant.DEFAULT,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.menuItem.name.uppercase(),
                    style = typography.h3,
                    color = BloomCream100
                )

                val details = mutableListOf<String>()
                if (item.selectedSize != null) details.add(item.selectedSize.label)
                if (item.selectedStyle != PreparationStyle.DEFAULT) details.add(item.selectedStyle.label)
                if (item.specialInstructions.isNotEmpty()) details.add(item.specialInstructions)

                if (details.isNotEmpty()) {
                    Text(
                        text = details.joinToString(" · "),
                        style = typography.caption,
                        color = colors.textMuted
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${item.unitPrice.toInt()} × ${item.quantity} = ₹${item.totalPrice.toInt()}",
                    style = typography.mono,
                    color = BloomPollen500
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .border(spacing.borderHairline, BloomCream200)
                        .clickable { onDecrement() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = BloomCream200,
                        modifier = Modifier.size(14.dp)
                    )
                }

                Text(
                    text = item.quantity.toString(),
                    style = typography.h3,
                    color = BloomCream100
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .border(spacing.borderHairline, BloomCream200)
                        .clickable { onIncrement() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = BloomCream200,
                        modifier = Modifier.size(14.dp)
                    )
                }

                IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = colors.textMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
