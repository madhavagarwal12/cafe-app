package com.babacafe.app

import com.babacafe.app.data.datasource.MenuDataSource
import com.babacafe.app.data.model.OrderType
import com.babacafe.app.data.model.PaymentOption
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.data.model.SizeOption
import com.babacafe.app.data.repository.CartRepository
import com.babacafe.app.data.repository.OrderRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CartAndOrderTest {

    @Test
    fun testCartCalculationsAndOrderPlacement() {
        val cartRepo = CartRepository()
        val orderRepo = OrderRepository()

        val kulhadTea = MenuDataSource.items.first { it.id == "tea_kulhad" }
        val biloniTikki = MenuDataSource.items.first { it.id == "starter_baba_biloni_tikki" }
        val doubleCheesePizza = MenuDataSource.items.first { it.id == "pizza_double_cheese" }

        // Add 2 Kulhad Teas (₹25 each = ₹50)
        cartRepo.addToCart(kulhadTea, quantity = 2)

        // Add 1 Baba Special Biloni Tikki (₹120)
        cartRepo.addToCart(biloniTikki, quantity = 1)

        // Add 1 Double Cheese Pizza Large (₹200)
        val largePizzaOption = doubleCheesePizza.sizeOptions.first { it.label == "Large" }
        cartRepo.addToCart(doubleCheesePizza, selectedSize = largePizzaOption, quantity = 1)

        val items = cartRepo.cartItems.value
        assertEquals(3, items.size)

        // Total expected = 50 + 120 + 200 = 370
        val subtotal = items.sumOf { it.totalPrice }
        assertEquals(370.0, subtotal, 0.0)

        // Place Order
        val order = orderRepo.placeOrder(
            items = items,
            orderType = OrderType.DINE_IN,
            tableNumber = "Table 04",
            customerName = "Aman Verma",
            customerPhone = "9876543210",
            notes = "Serve chai hot",
            paymentOption = PaymentOption.PAY_AT_COUNTER
        )

        assertNotNull(order)
        assertTrue(order.tokenNumber.startsWith("BABA-"))
        assertEquals(370.0, order.subtotal, 0.0)
        assertEquals(370.0 * 0.05, order.gst, 0.01)
        assertEquals(370.0 * 1.05, order.total, 0.01)
        assertEquals("Table 04", order.tableNumber)
    }
}
