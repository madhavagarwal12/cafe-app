package com.babacafe.app

import com.babacafe.app.data.datasource.MenuDataSource
import com.babacafe.app.data.model.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MenuDataSourceTest {

    @Test
    fun testAllMenuCategoriesPresent() {
        val items = MenuDataSource.items
        assertTrue("Menu must have more than 50 items", items.size >= 50)

        // Check Tea
        val tea = items.find { it.id == "tea_regular" }
        assertNotNull(tea)
        assertEquals(10.0, tea!!.basePrice, 0.0)
        assertEquals(3, tea.sizeOptions.size)

        // Check Verified Aloo Paratha (₹78)
        val alooParatha = items.find { it.id == "breakfast_aloo_paratha" }
        assertNotNull(alooParatha)
        assertEquals(78.0, alooParatha!!.basePrice, 0.0)

        // Check Baba Special Biloni Tikki
        val biloniTikki = items.find { it.id == "starter_baba_biloni_tikki" }
        assertNotNull(biloniTikki)
        assertEquals(120.0, biloniTikki!!.basePrice, 0.0)
        assertTrue(biloniTikki.isBabaSpecial)

        // Check Noodles
        val singapuriNoodles = items.find { it.id == "noodles_singapuri" }
        assertNotNull(singapuriNoodles)
        assertEquals(169.0, singapuriNoodles!!.basePrice, 0.0)
    }
}
