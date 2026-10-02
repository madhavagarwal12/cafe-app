package com.babacafe.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babacafe.app.data.model.CartItem
import com.babacafe.app.data.model.Order
import com.babacafe.app.data.model.OrderType
import com.babacafe.app.data.model.PaymentOption
import com.babacafe.app.data.repository.CartRepository
import com.babacafe.app.data.repository.OrderRepository
import com.babacafe.app.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class CartViewModel(
    private val cartRepository: CartRepository = AppContainer.cartRepository,
    private val orderRepository: OrderRepository = AppContainer.orderRepository
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = cartRepository.cartItems

    private val _orderType = MutableStateFlow(OrderType.DINE_IN)
    val orderType: StateFlow<OrderType> = _orderType.asStateFlow()

    private val _tableNumber = MutableStateFlow("Table 04")
    val tableNumber: StateFlow<String> = _tableNumber.asStateFlow()

    private val _customerName = MutableStateFlow("")
    val customerName: StateFlow<String> = _customerName.asStateFlow()

    private val _customerPhone = MutableStateFlow("")
    val customerPhone: StateFlow<String> = _customerPhone.asStateFlow()

    private val _orderNotes = MutableStateFlow("")
    val orderNotes: StateFlow<String> = _orderNotes.asStateFlow()

    private val _paymentOption = MutableStateFlow(PaymentOption.PAY_AT_COUNTER)
    val paymentOption: StateFlow<PaymentOption> = _paymentOption.asStateFlow()

    fun onOrderTypeChange(type: OrderType) {
        _orderType.value = type
    }

    fun onTableNumberChange(num: String) {
        _tableNumber.value = num
    }

    fun onCustomerNameChange(name: String) {
        _customerName.value = name
    }

    fun onCustomerPhoneChange(phone: String) {
        _customerPhone.value = phone
    }

    fun onOrderNotesChange(notes: String) {
        _orderNotes.value = notes
    }

    fun onPaymentOptionChange(option: PaymentOption) {
        _paymentOption.value = option
    }

    fun incrementQuantity(item: CartItem) {
        cartRepository.updateQuantity(item.id, +1)
    }

    fun decrementQuantity(item: CartItem) {
        cartRepository.updateQuantity(item.id, -1)
    }

    fun removeItem(item: CartItem) {
        cartRepository.removeItem(item.id)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }

    fun placeOrder(): Order? {
        val items = cartItems.value
        if (items.isEmpty()) return null

        val order = orderRepository.placeOrder(
            items = items,
            orderType = _orderType.value,
            tableNumber = if (_orderType.value == OrderType.DINE_IN) _tableNumber.value else null,
            customerName = _customerName.value,
            customerPhone = _customerPhone.value,
            notes = _orderNotes.value,
            paymentOption = _paymentOption.value
        )
        cartRepository.clearCart()
        return order
    }
}
