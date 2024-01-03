package com.pub.cc.skyjourney.core.payment.api

import com.pub.cc.skyjourney.core.payment.model.PaymentMethod

data class PaymentRequest(
    val userId: String,
    val amount: Double,
    val method: PaymentMethod
)
