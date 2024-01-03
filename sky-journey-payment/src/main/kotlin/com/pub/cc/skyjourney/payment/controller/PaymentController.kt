package com.pub.cc.skyjourney.payment.controller

import com.pub.cc.skyjourney.payment.model.PaymentRequest
import com.pub.cc.skyjourney.payment.model.PaymentResponse
import com.pub.cc.skyjourney.payment.service.PaymentService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    path = ["/payment"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping
    fun makePayment(
        @RequestBody request: PaymentRequest
    ): ResponseEntity<PaymentResponse> {
        val paymentResponse = paymentService.makePayment(request)

        return ResponseEntity.ok(paymentResponse)
    }
}