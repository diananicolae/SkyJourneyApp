package com.pub.cc.skyjourney.auth.config

import com.pub.cc.skyjourney.auth.handler.CustomAuthenticationSuccessHandler
import com.pub.cc.skyjourney.auth.service.MongoUserDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${core.service.home.uri}")
    lateinit var coreServiceHomeUri: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
//            authorizeHttpRequests {
//                authorize(anyRequest, permitAll)
//            }
//            formLogin {
//                permitAll()
//                loginPage = "/login"
//                authenticationSuccessHandler = CustomAuthenticationSuccessHandler(coreServiceHomeUri)
//
//            }
//            httpBasic { }
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            csrf { disable() }
            httpBasic { }
        }

        return http.build()
    }

    @Bean
    fun authenticationManager(
        userDetailsService: MongoUserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}