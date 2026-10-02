package com.babacafe.app.ui.screens.menu

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babacafe.app.data.model.Category
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomInput
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.components.BloomTopBar
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun MenuScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: MenuViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val items by viewModel.menuItems.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val cartItemCount by viewModel.cartItemCount.collectAsState()
    val selectedDetailItem by viewModel.selectedItemForDetail.collectAsState()

    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            BloomTopBar(
                title = "CATALOG & MENU",
                cartItemCount = cartItemCount,
                onCartClick = { onNavigate(Screen.Cart) },
                onInfoClick = { onNavigate(Screen.Info) }
            )

            // Search Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                BloomInput(
                    value = searchQuery,
                    onValueChange = { viewModel.onSearchQueryChange(it) },
                    label = "SEARCH MENU ITEMS",
                    placeholder = "Search tea, maggie, biloni tikki, pizza...",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = BloomCream200,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    trailingIcon = if (searchQuery.isNotEmpty()) {
                        {
                            IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = BloomCream200,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    } else null
                )
            }

            // Category Horizontal Tabs
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(Category.values()) { category ->
                    val isSelected = selectedCategory == category
                    BloomTag(
                        text = "${category.shortCode} · ${category.title}",
                        isSelected = isSelected,
                        onClick = { viewModel.onCategorySelect(category) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Items Count & Pure Veg Badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${items.size} ITEMS LISTED",
                    style = typography.caption,
                    color = colors.textMuted
                )

                Text(
                    text = "100% PURE VEG EATERY",
                    style = typography.caption,
                    color = BloomPollen500
                )
            }

            // Menu Items List
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (items.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "NO ITEMS FOUND MATCHING \"$searchQuery\"",
                                style = typography.body,
                                color = colors.textMuted
                            )
                        }
                    }
                } else {
                    items(items) { item ->
                        MenuItemCard(
                            item = item,
                            onClick = { viewModel.openItemDetail(item) },
                            onQuickAdd = {
                                viewModel.addToCart(
                                    menuItem = item,
                                    selectedSize = item.sizeOptions.firstOrNull(),
                                    quantity = 1
                                )
                            }
                        )
                    }
                }
            }
        }

        // Floating Cart Summary Bar
        if (cartItemCount > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BloomButton(
                    text = "VIEW CART (${cartItemCount} ITEMS)",
                    onClick = { onNavigate(Screen.Cart) },
                    variant = BloomButtonVariant.ACCENT,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = null,
                            tint = colors.textInk,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }

        // Customization Dialog
        selectedDetailItem?.let { item ->
            ItemDetailDialog(
                item = item,
                onDismiss = { viewModel.closeItemDetail() },
                onAddToCart = { mItem, size, style, notes, qty ->
                    viewModel.addToCart(mItem, size, style, notes, qty)
                }
            )
        }
    }
}
