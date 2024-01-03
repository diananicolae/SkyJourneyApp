package com.pub.cc.skyjourney.payment.service

import com.pub.cc.skyjourney.payment.model.PaymentRequest
import com.pub.cc.skyjourney.payment.model.PaymentResponse
import com.pub.cc.skyjourney.payment.model.PaymentStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PaymentService {

    fun makePayment(request: PaymentRequest): PaymentResponse {
        return PaymentResponse(
            paymentId = UUID.randomUUID().toString(),
            userId = request.userId,
            status = PaymentStatus.SUCCESS
        )
    }
}