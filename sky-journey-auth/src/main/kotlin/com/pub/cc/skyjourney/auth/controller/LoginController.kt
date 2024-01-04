package com.pub.cc.skyjourney.auth.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@Controller
class LoginController(val authenticationManager: AuthenticationManager) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Authentication> {
        val authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username, loginRequest.password)
        val authenticationResponse =
            authenticationManager.authenticate(authenticationRequest)

        return ResponseEntity.ok(authenticationResponse)
    }

    data class LoginRequest(val username: String, val password: String)
}