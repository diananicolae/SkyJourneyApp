package com.pub.cc.skyjourney.core.payment.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PaymentServiceClient(
    @Value("\${payment.service.url}")
    private val paymentServiceUrl: String
) {

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(paymentServiceUrl)
        .build()

    fun makePayment(paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return webClient.post()
            .uri("/payment")
            .bodyValue(paymentRequest)
            .retrieve()
            .bodyToMono(PaymentResponse::class.java)
    }



}