package com.pub.cc.skyjourney.payment.model

data class PaymentResponse(
    val paymentId: String,
    val userId: String,
    val status: PaymentStatus
)