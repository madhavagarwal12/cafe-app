package com.babacafe.app.ui.navigation

sealed class Screen(val route: String, val title: String) {
    data object Hero : Screen("hero", "Baba Café")
    data object Menu : Screen("menu", "Menu")
    data object Cart : Screen("cart", "Cart")
    data object OrderConfirmation : Screen("order_confirmation", "Order Confirmed")
    data object Reservation : Screen("reservation", "Reserve Table")
    data object Info : Screen("info", "About & Location")
}
