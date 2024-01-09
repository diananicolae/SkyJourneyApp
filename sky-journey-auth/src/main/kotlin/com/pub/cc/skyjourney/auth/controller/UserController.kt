package com.pub.cc.skyjourney.auth.controller

import com.pub.cc.skyjourney.auth.model.ApplicationUser
import com.pub.cc.skyjourney.auth.model.CreateUserRequest
import com.pub.cc.skyjourney.auth.service.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    path = ["/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CrossOrigin(origins = ["*", "http://sky-journey-ui-service"])
class UserController(
    private val userService: UserService
) {

    @PostMapping("/create-account")
    fun createAccount(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<ApplicationUser> {
        val user = userService.createUser(createUserRequest)
        return ResponseEntity.ok(user)
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<ApplicationUser>> {
        val users = userService.getAllUsers()

        return ResponseEntity.ok(users)
    }
}