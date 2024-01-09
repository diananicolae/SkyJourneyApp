package com.pub.cc.skyjourney.core.config

import com.pub.cc.skyjourney.core.security.JwtAuthorizationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var jwtAuthorizationManager: JwtAuthorizationManager

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/flights/**", permitAll)
                authorize(anyRequest, jwtAuthorizationManager)
            }
            csrf { disable() }
            httpBasic { }
        }

        return http.build()
    }
}

