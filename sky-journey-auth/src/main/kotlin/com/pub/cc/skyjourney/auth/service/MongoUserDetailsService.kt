package com.pub.cc.skyjourney.auth.service

import com.pub.cc.skyjourney.auth.repository.CredentialsRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MongoUserDetailsService(private val credentialsRepository: CredentialsRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = credentialsRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User '$username' not found")

        return User.builder()
            .username(applicationUser.username)
            .password(applicationUser.password)
            // .roles(...) // Define roles if necessary
            .build()
    }
}