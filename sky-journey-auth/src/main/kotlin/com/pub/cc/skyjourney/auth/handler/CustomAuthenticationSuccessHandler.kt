package com.pub.cc.skyjourney.auth.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class CustomAuthenticationSuccessHandler(
    private val coreServiceHomeUri: String
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val token = generateToken(authentication)

        response.sendRedirect("$coreServiceHomeUri?token=$token")
    }

    private fun generateToken(authentication: Authentication?): String {
        // Implement your token generation logic here
        // For example, create a JWT token based on the user's details
        return "generated-token"
    }
}
