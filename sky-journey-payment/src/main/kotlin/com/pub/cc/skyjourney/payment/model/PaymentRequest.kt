package com.pub.cc.skyjourney.payment.model

data class PaymentRequest(
    val userId: String,
    val amount: Double,
    val method: PaymentMethod
)