package com.pub.cc.skyjourney.core.model.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val password: String
)
