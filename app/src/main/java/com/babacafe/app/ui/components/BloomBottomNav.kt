package com.babacafe.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomBlue700
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Hero, "Home", Icons.Default.Home),
    BottomNavItem(Screen.Menu, "Menu", Icons.Default.RestaurantMenu),
    BottomNavItem(Screen.Reservation, "Dine-In", Icons.Default.DateRange),
    BottomNavItem(Screen.Cart, "Cart", Icons.Default.ShoppingBag),
    BottomNavItem(Screen.Info, "About", Icons.Default.Info)
)

@Composable
fun BloomBottomNav(
    currentRoute: String?,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BloomBlue700)
    ) {
        HorizontalDivider(
            thickness = spacing.borderHairline,
            color = colors.line
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = currentRoute == item.screen.route

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigate(item.screen) }
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected) BloomPollen500 else BloomCream200.copy(alpha = 0.7f),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.label.uppercase(),
                        style = typography.caption,
                        color = if (isSelected) BloomCream100 else colors.textMuted
                    )
                }
            }
        }
    }
}
