package com.pub.cc.skyjourney.core.model.booking

import com.pub.cc.skyjourney.core.payment.api.PaymentRequest

data class CreateBookingRequest(
    val userId: String,
    val flightId: String,
    val seat: String,
    val paymentRequest: PaymentRequest,
)