package com.pub.cc.skyjourney.auth.controller

import com.pub.cc.skyjourney.auth.model.ApplicationUser
import com.pub.cc.skyjourney.auth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/create-account")
    fun createAccount(@RequestBody userRequest: UserRequest): ResponseEntity<ApplicationUser> {
        val user = userService.createUser(userRequest.username, userRequest.password)
        return ResponseEntity.ok(user)
    }

    data class UserRequest(val username: String, val password: String)
}