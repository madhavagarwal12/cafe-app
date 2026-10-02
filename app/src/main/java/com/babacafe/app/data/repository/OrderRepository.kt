package com.babacafe.app.data.repository

import com.babacafe.app.data.model.CartItem
import com.babacafe.app.data.model.Order
import com.babacafe.app.data.model.OrderStatus
import com.babacafe.app.data.model.OrderType
import com.babacafe.app.data.model.PaymentOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import kotlin.random.Random

class OrderRepository {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    private val _currentActiveOrder = MutableStateFlow<Order?>(null)
    val currentActiveOrder: StateFlow<Order?> = _currentActiveOrder.asStateFlow()

    fun placeOrder(
        items: List<CartItem>,
        orderType: OrderType,
        tableNumber: String?,
        customerName: String,
        customerPhone: String,
        notes: String,
        paymentOption: PaymentOption
    ): Order {
        val subtotal = items.sumOf { it.totalPrice }
        val gst = subtotal * 0.05 // 5% GST
        val total = subtotal + gst
        val token = "BABA-" + (1000 + Random.nextInt(9000)).toString()

        val order = Order(
            orderId = UUID.randomUUID().toString(),
            tokenNumber = token,
            items = items,
            orderType = orderType,
            tableNumber = if (orderType == OrderType.DINE_IN) tableNumber else null,
            customerName = customerName.ifBlank { "Guest" },
            customerPhone = customerPhone.ifBlank { "N/A" },
            notes = notes,
            paymentOption = paymentOption,
            subtotal = subtotal,
            gst = gst,
            total = total,
            status = OrderStatus.RECEIVED
        )

        _orders.value = listOf(order) + _orders.value
        _currentActiveOrder.value = order
        return order
    }

    fun clearActiveOrder() {
        _currentActiveOrder.value = null
    }
}
