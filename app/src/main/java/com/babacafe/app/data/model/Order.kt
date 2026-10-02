package com.babacafe.app.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class OrderType(val label: String, val subtitle: String) {
    DINE_IN("Dine-In", "Direct to your table"),
    TAKEAWAY("Takeaway", "Quick counter pickup"),
    DELIVERY("Delivery", "Order & Deliver")
}

enum class PaymentOption(val title: String, val subtitle: String) {
    PAY_AT_COUNTER("Pay at Counter", "Cash, Card or QR upon pickup/service"),
    UPI_QR("Pay via UPI / QR", "Scan cafe QR code on billing"),
    CASH_ON_DELIVERY("Cash on Delivery / Table", "Pay cash directly to server")
}

enum class OrderStatus(val title: String) {
    RECEIVED("Order Received"),
    PREPARING("Kitchen Preparing"),
    READY("Ready for Serving/Pickup"),
    COMPLETED("Completed")
}

data class Order(
    val orderId: String,
    val tokenNumber: String,
    val items: List<CartItem>,
    val orderType: OrderType,
    val tableNumber: String? = null,
    val customerName: String,
    val customerPhone: String,
    val notes: String = "",
    val paymentOption: PaymentOption,
    val subtotal: Double,
    val gst: Double,
    val total: Double,
    val timestamp: Long = System.currentTimeMillis(),
    val status: OrderStatus = OrderStatus.RECEIVED
) {
    val formattedTime: String
        get() {
            val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
}
