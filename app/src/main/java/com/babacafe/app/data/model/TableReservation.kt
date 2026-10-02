package com.babacafe.app.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class TableReservation(
    val reservationId: String,
    val tokenCode: String,
    val guestName: String,
    val guestPhone: String,
    val partySize: Int,
    val bookingDate: String,
    val timeSlot: String,
    val seatingPreference: String,
    val specialRequests: String = "",
    val createdAt: Long = System.currentTimeMillis()
) {
    val formattedCreatedDate: String
        get() {
            val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            return sdf.format(Date(createdAt))
        }
}
