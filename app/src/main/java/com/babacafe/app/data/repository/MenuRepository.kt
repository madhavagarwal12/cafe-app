package com.babacafe.app.data.repository

import com.babacafe.app.data.datasource.MenuDataSource
import com.babacafe.app.data.model.Category
import com.babacafe.app.data.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class MenuRepository {
    private val _items = MutableStateFlow(MenuDataSource.items)
    val items: Flow<List<MenuItem>> = _items.asStateFlow()

    fun getItemsByCategory(category: Category): Flow<List<MenuItem>> {
        return _items.map { list ->
            if (category == Category.ALL) list
            else list.filter { it.category == category }
        }
    }

    fun getBestsellers(): Flow<List<MenuItem>> {
        return _items.map { list -> list.filter { it.isBestseller || it.isBabaSpecial } }
    }

    fun searchItems(query: String, category: Category = Category.ALL): Flow<List<MenuItem>> {
        return _items.map { list ->
            list.filter { item ->
                val matchesCategory = category == Category.ALL || item.category == category
                val matchesQuery = query.isBlank() ||
                        item.name.contains(query, ignoreCase = true) ||
                        item.hindiName.contains(query, ignoreCase = true) ||
                        item.description.contains(query, ignoreCase = true) ||
                        item.category.title.contains(query, ignoreCase = true)
                matchesCategory && matchesQuery
            }
        }
    }

    fun getItemById(id: String): MenuItem? {
        return _items.value.find { it.id == id }
    }
}
