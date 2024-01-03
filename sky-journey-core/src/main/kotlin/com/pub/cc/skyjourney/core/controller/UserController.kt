package com.pub.cc.skyjourney.core.controller

import com.pub.cc.skyjourney.core.model.booking.Booking
import com.pub.cc.skyjourney.core.model.user.User
import com.pub.cc.skyjourney.core.service.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = ["/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{userId}")
    fun getUser(
        @PathVariable userId: String
    ): ResponseEntity<User> {
        val user = userService.getUserById(userId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user)
    }

    @GetMapping("/{userId}/bookings")
    fun getUserBookings(
        @PathVariable userId: String
    ): ResponseEntity<List<Booking>> {
        userService.getUserById(userId) ?: return ResponseEntity.notFound().build()

        val bookings = userService.getUserBookings(userId)

        return ResponseEntity.ok(bookings)
    }
}