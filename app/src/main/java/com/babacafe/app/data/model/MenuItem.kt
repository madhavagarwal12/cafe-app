package com.babacafe.app.data.model

data class SizeOption(
    val label: String, // e.g., "Small", "Medium", "Large", "Single", "Double"
    val price: Double
)

enum class PreparationStyle(val label: String, val priceModifier: Double = 0.0) {
    DEFAULT("Standard"),
    DRY("Dry"),
    GRAVY("Gravy")
}

data class MenuItem(
    val id: String,
    val name: String,
    val hindiName: String = "",
    val category: Category,
    val description: String = "",
    val basePrice: Double,
    val sizeOptions: List<SizeOption> = emptyList(),
    val availableStyles: List<PreparationStyle> = emptyList(),
    val isVeg: Boolean = true,
    val isBestseller: Boolean = false,
    val isBabaSpecial: Boolean = false,
    val displayBadge: String? = null
)
