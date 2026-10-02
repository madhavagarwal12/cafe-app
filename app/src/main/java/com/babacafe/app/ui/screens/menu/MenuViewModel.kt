package com.babacafe.app.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babacafe.app.data.model.Category
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.data.model.SizeOption
import com.babacafe.app.data.repository.CartRepository
import com.babacafe.app.data.repository.MenuRepository
import com.babacafe.app.di.AppContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class MenuViewModel(
    private val menuRepository: MenuRepository = AppContainer.menuRepository,
    private val cartRepository: CartRepository = AppContainer.cartRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow(Category.ALL)
    val selectedCategory: StateFlow<Category> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedItemForDetail = MutableStateFlow<MenuItem?>(null)
    val selectedItemForDetail: StateFlow<MenuItem?> = _selectedItemForDetail.asStateFlow()

    val menuItems: StateFlow<List<MenuItem>> = combine(_selectedCategory, _searchQuery) { cat, query ->
        Pair(cat, query)
    }.flatMapLatest { (cat, query) ->
        menuRepository.searchItems(query, cat)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val cartItemCount: StateFlow<Int> = cartRepository.cartItems.flatMapLatest { list ->
        MutableStateFlow(list.sumOf { it.quantity })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    fun onCategorySelect(category: Category) {
        _selectedCategory.value = category
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun openItemDetail(item: MenuItem) {
        _selectedItemForDetail.value = item
    }

    fun closeItemDetail() {
        _selectedItemForDetail.value = null
    }

    fun addToCart(
        menuItem: MenuItem,
        selectedSize: SizeOption? = null,
        selectedStyle: PreparationStyle = PreparationStyle.DEFAULT,
        specialInstructions: String = "",
        quantity: Int = 1
    ) {
        cartRepository.addToCart(
            menuItem = menuItem,
            selectedSize = selectedSize,
            selectedStyle = selectedStyle,
            specialInstructions = specialInstructions,
            quantity = quantity
        )
        closeItemDetail()
    }
}
