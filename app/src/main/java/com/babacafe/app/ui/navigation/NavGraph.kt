package com.babacafe.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.babacafe.app.ui.components.BloomBottomNav
import com.babacafe.app.ui.screens.cart.CartScreen
import com.babacafe.app.ui.screens.hero.HeroScreen
import com.babacafe.app.ui.screens.info.CafeInfoScreen
import com.babacafe.app.ui.screens.menu.MenuScreen
import com.babacafe.app.ui.screens.order.OrderConfirmationScreen
import com.babacafe.app.ui.screens.reservation.TableReservationScreen
import com.babacafe.app.ui.theme.BloomBlue500

@Composable
fun BabaCafeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        containerColor = BloomBlue500,
        bottomBar = {
            // Show bottom nav on main top-level destinations
            val showBottomNav = currentRoute in listOf(
                Screen.Hero.route,
                Screen.Menu.route,
                Screen.Reservation.route,
                Screen.Cart.route,
                Screen.Info.route
            )
            if (showBottomNav) {
                BloomBottomNav(
                    currentRoute = currentRoute,
                    onNavigate = { screen ->
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.Hero.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Hero.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Hero.route) {
                HeroScreen(
                    onNavigate = { screen -> navController.navigate(screen.route) }
                )
            }

            composable(Screen.Menu.route) {
                MenuScreen(
                    onNavigate = { screen -> navController.navigate(screen.route) }
                )
            }

            composable(Screen.Cart.route) {
                CartScreen(
                    onNavigate = { screen -> navController.navigate(screen.route) }
                )
            }

            composable(Screen.OrderConfirmation.route) {
                OrderConfirmationScreen(
                    onNavigate = { screen ->
                        if (screen == Screen.Hero) {
                            navController.popBackStack(Screen.Hero.route, inclusive = false)
                        } else {
                            navController.navigate(screen.route)
                        }
                    }
                )
            }

            composable(Screen.Reservation.route) {
                TableReservationScreen(
                    onNavigate = { screen -> navController.navigate(screen.route) }
                )
            }

            composable(Screen.Info.route) {
                CafeInfoScreen(
                    onNavigate = { screen -> navController.navigate(screen.route) }
                )
            }
        }
    }
}
