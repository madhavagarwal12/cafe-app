package com.babacafe.app.ui.screens.reservation

import androidx.lifecycle.ViewModel
import com.babacafe.app.data.model.TableReservation
import com.babacafe.app.data.repository.ReservationRepository
import com.babacafe.app.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReservationViewModel(
    private val reservationRepository: ReservationRepository = AppContainer.reservationRepository
) : ViewModel() {

    private val _guestName = MutableStateFlow("")
    val guestName: StateFlow<String> = _guestName.asStateFlow()

    private val _guestPhone = MutableStateFlow("")
    val guestPhone: StateFlow<String> = _guestPhone.asStateFlow()

    private val _partySize = MutableStateFlow(2)
    val partySize: StateFlow<Int> = _partySize.asStateFlow()

    private val _bookingDate = MutableStateFlow("Today")
    val bookingDate: StateFlow<String> = _bookingDate.asStateFlow()

    private val _timeSlot = MutableStateFlow("06:00 PM")
    val timeSlot: StateFlow<String> = _timeSlot.asStateFlow()

    private val _seatingPreference = MutableStateFlow("Calm / Conversation Table")
    val seatingPreference: StateFlow<String> = _seatingPreference.asStateFlow()

    private val _specialRequests = MutableStateFlow("")
    val specialRequests: StateFlow<String> = _specialRequests.asStateFlow()

    private val _confirmedReservation = MutableStateFlow<TableReservation?>(null)
    val confirmedReservation: StateFlow<TableReservation?> = _confirmedReservation.asStateFlow()

    fun onGuestNameChange(name: String) { _guestName.value = name }
    fun onGuestPhoneChange(phone: String) { _guestPhone.value = phone }
    fun onPartySizeChange(size: Int) { _partySize.value = size }
    fun onBookingDateChange(date: String) { _bookingDate.value = date }
    fun onTimeSlotChange(slot: String) { _timeSlot.value = slot }
    fun onSeatingPreferenceChange(pref: String) { _seatingPreference.value = pref }
    fun onSpecialRequestsChange(req: String) { _specialRequests.value = req }

    fun reserveTable(): TableReservation {
        val reservation = reservationRepository.createReservation(
            guestName = _guestName.value.ifBlank { "Guest" },
            guestPhone = _guestPhone.value.ifBlank { "N/A" },
            partySize = _partySize.value,
            bookingDate = _bookingDate.value,
            timeSlot = _timeSlot.value,
            seatingPreference = _seatingPreference.value,
            specialRequests = _specialRequests.value
        )
        _confirmedReservation.value = reservation
        return reservation
    }

    fun resetForm() {
        _confirmedReservation.value = null
    }
}
