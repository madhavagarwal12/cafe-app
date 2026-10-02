package com.babacafe.app.di

import com.babacafe.app.data.repository.CartRepository
import com.babacafe.app.data.repository.MenuRepository
import com.babacafe.app.data.repository.OrderRepository
import com.babacafe.app.data.repository.ReservationRepository

object AppContainer {
    val menuRepository by lazy { MenuRepository() }
    val cartRepository by lazy { CartRepository() }
    val orderRepository by lazy { OrderRepository() }
    val reservationRepository by lazy { ReservationRepository() }
}
