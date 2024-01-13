package com.pub.cc.skyjourney.auth.service

import com.pub.cc.skyjourney.auth.model.ApplicationUser
import com.pub.cc.skyjourney.auth.model.CreateUserRequest
import com.pub.cc.skyjourney.auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {

    fun createUser(request: CreateUserRequest): ApplicationUser {
        val encodedPassword = passwordEncoder.encode(request.password)
        val user = ApplicationUser(name = request.name, username = request.username, password = encodedPassword)
        return userRepository.save(user)
    }

    fun getAllUsers(): List<ApplicationUser> {
        return userRepository.findAll()
    }

    fun getUserById(userId: String): ApplicationUser? {
        return userRepository.findById(userId).getOrNull()
    }

    fun getUserByUsername(username: String): ApplicationUser? {
        return userRepository.findByUsername(username)
    }
}