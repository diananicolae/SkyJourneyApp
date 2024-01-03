package com.pub.cc.skyjourney.core.service

import com.pub.cc.skyjourney.core.model.booking.Booking
import com.pub.cc.skyjourney.core.model.user.User
import com.pub.cc.skyjourney.core.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bookingService: BookingService
) {

    fun getUserById(userId: String): User? {
        return userRepository.findById(userId).getOrNull()
    }

    fun getUserBookings(userId: String): List<Booking> {
        return bookingService.getBookingsByUserId(userId)
    }
}