package com.babacafe.app.data.repository

import com.babacafe.app.data.model.TableReservation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import kotlin.random.Random

class ReservationRepository {
    private val _reservations = MutableStateFlow<List<TableReservation>>(emptyList())
    val reservations: StateFlow<List<TableReservation>> = _reservations.asStateFlow()

    fun createReservation(
        guestName: String,
        guestPhone: String,
        partySize: Int,
        bookingDate: String,
        timeSlot: String,
        seatingPreference: String,
        specialRequests: String
    ): TableReservation {
        val token = "RES-" + (100 + Random.nextInt(900)).toString()
        val reservation = TableReservation(
            reservationId = UUID.randomUUID().toString(),
            tokenCode = token,
            guestName = guestName,
            guestPhone = guestPhone,
            partySize = partySize,
            bookingDate = bookingDate,
            timeSlot = timeSlot,
            seatingPreference = seatingPreference,
            specialRequests = specialRequests
        )
        _reservations.value = listOf(reservation) + _reservations.value
        return reservation
    }
}
