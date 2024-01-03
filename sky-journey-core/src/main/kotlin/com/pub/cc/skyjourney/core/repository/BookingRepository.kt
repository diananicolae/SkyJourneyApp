package com.pub.cc.skyjourney.core.repository

import com.pub.cc.skyjourney.core.model.booking.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository: MongoRepository<Booking, String> {

    fun findByUserId(userId: String): List<Booking>
}