package com.pub.cc.skyjourney.core.security

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import java.util.function.Supplier

class JwtAuthorizationManager : AuthorizationManager<RequestAuthorizationContext> {

    override fun check(
        authentication: Supplier<Authentication>,
        context: RequestAuthorizationContext
    ): AuthorizationDecision {
//        val auth = authentication.get()
//
//        if (validateToken(auth, context)) {
//            return AuthorizationDecision(true)
//        }
//
//        return AuthorizationDecision(false)
        return AuthorizationDecision(true)
    }

    private fun validateToken(authentication: Authentication, context: RequestAuthorizationContext): Boolean {
        if (context.request.queryString == null) {
            return false
        }

        val queryParams = context.request.queryString.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }

        if (queryParams["token"] == "generated-token") {
            authentication.isAuthenticated = true
            return true
        }

        return false
    }
}