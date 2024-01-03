package com.pub.cc.skyjourney.core.payment.api

import com.pub.cc.skyjourney.core.payment.model.PaymentStatus

data class PaymentResponse(
    val paymentId: String,
    val userId: String,
    val status: PaymentStatus
)