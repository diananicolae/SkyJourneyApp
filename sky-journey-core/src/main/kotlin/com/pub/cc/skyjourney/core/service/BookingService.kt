package com.pub.cc.skyjourney.core.service

import com.pub.cc.skyjourney.core.model.booking.Booking
import com.pub.cc.skyjourney.core.model.booking.BookingStatus
import com.pub.cc.skyjourney.core.model.booking.CreateBookingRequest
import com.pub.cc.skyjourney.core.payment.api.PaymentServiceClient
import com.pub.cc.skyjourney.core.payment.model.PaymentStatus
import com.pub.cc.skyjourney.core.repository.BookingRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val paymentService: PaymentServiceClient
) {

    fun createBooking(request: CreateBookingRequest): Booking {
        val paymentResponse = paymentService
            .makePayment(request.paymentRequest)
            .block() ?: throw RuntimeException("Payment service is unavailable!")

        if (paymentResponse.status == PaymentStatus.FAILED) {
            throw RuntimeException("Payment failed!")
        }

        val booking = Booking(
            UUID.randomUUID().toString(),
            request.userId,
            request.flightId,
            request.seat,
            paymentResponse.paymentId,
            BookingStatus.CONFIRMED
        )

        bookingRepository.save(booking)
        return booking
    }

    fun getAllBookings(): List<Booking> {
        return bookingRepository.findAll()
    }

    fun getBookingById(bookingId: String): Booking? {
        return bookingRepository.findById(bookingId).getOrNull()
    }

    fun getBookingsByUserId(userId: String): List<Booking> {
        return bookingRepository.findByUserId(userId)
    }

    fun cancelBooking(bookingId: String): Booking {
        val booking = bookingRepository.findById(bookingId).getOrNull() ?: throw RuntimeException("Booking not found")

        if (booking.status == BookingStatus.CHECKED_IN) {
            throw RuntimeException("Canceling is unavailable! Check-in has been completed.")
        }

        return bookingRepository.findById(bookingId).map {
            val updated = it.copy(status = BookingStatus.CANCELLED)
            bookingRepository.save(updated)
        }.get()
    }

    fun checkInBooking(bookingId: String): Booking {
        val booking = bookingRepository.findById(bookingId).getOrNull() ?: throw RuntimeException("Booking not found")

        if (booking.status == BookingStatus.CANCELLED) {
            throw RuntimeException("Check-in is unavailable! Booking has been cancelled.")
        }

        return bookingRepository.findById(bookingId).map {
            val updated = it.copy(status = BookingStatus.CHECKED_IN)
            bookingRepository.save(updated)
        }.get()
    }

    fun deleteAllBookings() {
        bookingRepository.deleteAll()
    }
}