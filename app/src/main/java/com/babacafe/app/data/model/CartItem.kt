package com.babacafe.app.data.model

data class CartItem(
    val id: String,
    val menuItem: MenuItem,
    val selectedSize: SizeOption? = null,
    val selectedStyle: PreparationStyle = PreparationStyle.DEFAULT,
    val specialInstructions: String = "",
    val quantity: Int = 1
) {
    val unitPrice: Double
        get() {
            var price = selectedSize?.price ?: menuItem.basePrice
            if (selectedStyle == PreparationStyle.GRAVY && menuItem.availableStyles.contains(PreparationStyle.GRAVY)) {
                // If it has gravy price difference in menu (e.g. 100 dry vs 130 gravy)
                if (menuItem.id.contains("manchurian") || menuItem.id.contains("chilli_paneer")) {
                    price = 130.0
                }
            }
            return price
        }

    val totalPrice: Double
        get() = unitPrice * quantity
}
