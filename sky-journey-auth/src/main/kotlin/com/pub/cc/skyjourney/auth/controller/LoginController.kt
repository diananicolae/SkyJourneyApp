package com.pub.cc.skyjourney.auth.controller

import com.pub.cc.skyjourney.auth.model.LoginRequest
import com.pub.cc.skyjourney.auth.model.LoginResponse
import com.pub.cc.skyjourney.auth.service.TokenService
import com.pub.cc.skyjourney.auth.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(
    path = ["/login"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CrossOrigin(origins = ["*", "http://sky-journey-ui-service"])
class LoginController(
    val authenticationManager: AuthenticationManager,
    val userService: UserService,
    val tokenService: TokenService
) {

    @PostMapping
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val authenticationRequest = UsernamePasswordAuthenticationToken
            .unauthenticated(loginRequest.username, loginRequest.password)

        val authenticationResponse = authenticationManager.authenticate(authenticationRequest)

        if (authenticationResponse.isAuthenticated) {
            val token = tokenService.generateToken(loginRequest.username)
            val user = userService.getUserByUsername(loginRequest.username)

            return ResponseEntity.ok(LoginResponse(token, user.id!!, user.name))
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
}