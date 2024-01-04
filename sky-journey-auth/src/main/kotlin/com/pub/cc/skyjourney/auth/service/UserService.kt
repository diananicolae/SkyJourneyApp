package com.pub.cc.skyjourney.auth.service

import com.pub.cc.skyjourney.auth.model.ApplicationUser
import com.pub.cc.skyjourney.auth.repository.CredentialsRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val credentialsRepository: CredentialsRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(username: String, rawPassword: String): ApplicationUser {
        val encodedPassword = passwordEncoder.encode(rawPassword)
        val user = ApplicationUser(username = username, password = encodedPassword)
        return credentialsRepository.save(user)
    }
}