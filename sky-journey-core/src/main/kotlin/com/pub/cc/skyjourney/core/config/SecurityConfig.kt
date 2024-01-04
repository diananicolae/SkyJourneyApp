package com.pub.cc.skyjourney.core.config

import com.pub.cc.skyjourney.core.security.JwtAuthorizationManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${auth.service.uri}")
    lateinit var authServiceUri: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
//            authorizeHttpRequests {
//                authorize(anyRequest, JwtAuthorizationManager())
//            }
//            exceptionHandling {
//                authenticationEntryPoint = LoginUrlAuthenticationEntryPoint(authServiceUri)
//            }
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            csrf { disable() }
            httpBasic { }
        }

        return http.build()
    }
}

