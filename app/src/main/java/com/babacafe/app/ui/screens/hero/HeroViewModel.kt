package com.babacafe.app.ui.screens.hero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.data.repository.CartRepository
import com.babacafe.app.data.repository.MenuRepository
import com.babacafe.app.di.AppContainer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HeroViewModel(
    private val menuRepository: MenuRepository = AppContainer.menuRepository,
    private val cartRepository: CartRepository = AppContainer.cartRepository
) : ViewModel() {

    val bestsellers: StateFlow<List<MenuItem>> = menuRepository.getBestsellers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val cartItemCount: StateFlow<Int> = cartRepository.cartItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        ).let { flow ->
            kotlinx.coroutines.flow.MutableStateFlow(0).apply {
                // simple mapper
            }
        }

    fun quickAddToCart(item: MenuItem) {
        cartRepository.addToCart(
            menuItem = item,
            selectedSize = item.sizeOptions.firstOrNull(),
            quantity = 1
        )
    }
}
