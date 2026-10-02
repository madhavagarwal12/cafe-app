package com.babacafe.app.ui.screens.reservation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomCard
import com.babacafe.app.ui.components.BloomCardVariant
import com.babacafe.app.ui.components.BloomHeader
import com.babacafe.app.ui.components.BloomInput
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.components.BloomTopBar
import com.babacafe.app.ui.navigation.Screen
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomInk900
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun TableReservationScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: ReservationViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val guestName by viewModel.guestName.collectAsState()
    val guestPhone by viewModel.guestPhone.collectAsState()
    val partySize by viewModel.partySize.collectAsState()
    val bookingDate by viewModel.bookingDate.collectAsState()
    val timeSlot by viewModel.timeSlot.collectAsState()
    val seatingPreference by viewModel.seatingPreference.collectAsState()
    val specialRequests by viewModel.specialRequests.collectAsState()
    val confirmedReservation by viewModel.confirmedReservation.collectAsState()

    val colors = BloomTheme.colors
    val typography = BloomTheme.typography
    val spacing = BloomTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
    ) {
        BloomTopBar(
            title = "DINE-IN RESERVATION",
            showBack = false,
            cartItemCount = 0,
            onCartClick = { onNavigate(Screen.Cart) },
            onInfoClick = { onNavigate(Screen.Info) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (confirmedReservation != null) {
                // Confirmed Screen Banner
                BloomCard(
                    variant = BloomCardVariant.CREAM,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = BloomPollen500,
                            modifier = Modifier.size(54.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "TABLE RESERVED!",
                            style = typography.h1,
                            color = BloomInk900
                        )

                        Text(
                            text = "PASS CODE: ${confirmedReservation!!.tokenCode}",
                            style = typography.display,
                            color = colors.bg
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Reserved for ${confirmedReservation!!.guestName} (${confirmedReservation!!.partySize} Guests)\n${confirmedReservation!!.bookingDate} at ${confirmedReservation!!.timeSlot}",
                            style = typography.body,
                            color = BloomInk900,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }

                BloomButton(
                    text = "RESERVE ANOTHER TABLE",
                    onClick = { viewModel.resetForm() },
                    variant = BloomButtonVariant.OUTLINE,
                    modifier = Modifier.fillMaxWidth()
                )

                BloomButton(
                    text = "BROWSE MENU FOR ORDER",
                    onClick = { onNavigate(Screen.Menu) },
                    variant = BloomButtonVariant.PRIMARY,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                // Section 1: Party Size
                BloomHeader(
                    number = "01",
                    title = "PARTY SIZE",
                    subtitle = "Solo dining, friends hangouts or large family groups",
                    showDivider = false
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(1, 2, 4, 6, 8, 12).forEach { size ->
                        val label = if (size == 1) "1 Solo" else "$size Guests"
                        BloomTag(
                            text = label,
                            isSelected = partySize == size,
                            onClick = { viewModel.onPartySizeChange(size) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Section 2: Date & Time
                BloomHeader(
                    number = "02",
                    title = "DATE & TIME SLOT",
                    subtitle = "Open 5:00 AM – 12:00 AM daily"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Today", "Tomorrow", "Weekend").forEach { d ->
                        BloomTag(
                            text = d,
                            isSelected = bookingDate == d,
                            onClick = { viewModel.onBookingDateChange(d) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val slots = listOf(
                        "08:00 AM (Breakfast)",
                        "11:00 AM",
                        "01:00 PM (Lunch)",
                        "04:00 PM (Chai Time)",
                        "06:00 PM",
                        "08:00 PM (Dinner)",
                        "10:00 PM (Late Night)"
                    )
                    items(slots) { slot ->
                        BloomTag(
                            text = slot,
                            isSelected = timeSlot == slot,
                            onClick = { viewModel.onTimeSlotChange(slot) }
                        )
                    }
                }

                // Section 3: Seating Preference
                BloomHeader(
                    number = "03",
                    title = "VIBE & SEATING",
                    subtitle = "Select desired cafe spot"
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        "Calm / Conversation Corner",
                        "Casual Hangout Table",
                        "Work / Study / Charging Station",
                        "Outdoor Street-side Seating"
                    ).forEach { pref ->
                        BloomTag(
                            text = pref,
                            isSelected = seatingPreference == pref,
                            onClick = { viewModel.onSeatingPreferenceChange(pref) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                // Section 4: Contact & Guest Info
                BloomHeader(
                    number = "04",
                    title = "GUEST INFORMATION",
                    subtitle = "For booking notification & table hold"
                )

                BloomInput(
                    value = guestName,
                    onValueChange = { viewModel.onGuestNameChange(it) },
                    label = "Full Name",
                    placeholder = "e.g. Priya Sharma"
                )

                BloomInput(
                    value = guestPhone,
                    onValueChange = { viewModel.onGuestPhoneChange(it) },
                    label = "Phone Number",
                    placeholder = "e.g. 9876543210",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                BloomInput(
                    value = specialRequests,
                    onValueChange = { viewModel.onSpecialRequestsChange(it) },
                    label = "Special Occasion / Request",
                    placeholder = "e.g. Birthday celebration, window seat"
                )

                Spacer(modifier = Modifier.height(8.dp))

                BloomButton(
                    text = "CONFIRM TABLE RESERVATION",
                    onClick = { viewModel.reserveTable() },
                    variant = BloomButtonVariant.ACCENT,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
