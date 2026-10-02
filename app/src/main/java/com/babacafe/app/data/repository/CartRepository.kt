package com.babacafe.app.data.repository

import com.babacafe.app.data.model.CartItem
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.data.model.SizeOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(
        menuItem: MenuItem,
        selectedSize: SizeOption? = null,
        selectedStyle: PreparationStyle = PreparationStyle.DEFAULT,
        specialInstructions: String = "",
        quantity: Int = 1
    ) {
        val currentList = _cartItems.value.toMutableList()
        val existingIndex = currentList.indexOfFirst {
            it.menuItem.id == menuItem.id &&
                    it.selectedSize?.label == selectedSize?.label &&
                    it.selectedStyle == selectedStyle &&
                    it.specialInstructions == specialInstructions
        }

        if (existingIndex != -1) {
            val existing = currentList[existingIndex]
            currentList[existingIndex] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            currentList.add(
                CartItem(
                    id = UUID.randomUUID().toString(),
                    menuItem = menuItem,
                    selectedSize = selectedSize,
                    selectedStyle = selectedStyle,
                    specialInstructions = specialInstructions,
                    quantity = quantity
                )
            )
        }
        _cartItems.value = currentList
    }

    fun updateQuantity(cartItemId: String, delta: Int) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == cartItemId }
        if (index != -1) {
            val item = currentList[index]
            val newQty = item.quantity + delta
            if (newQty <= 0) {
                currentList.removeAt(index)
            } else {
                currentList[index] = item.copy(quantity = newQty)
            }
            _cartItems.value = currentList
        }
    }

    fun removeItem(cartItemId: String) {
        _cartItems.value = _cartItems.value.filter { it.id != cartItemId }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
