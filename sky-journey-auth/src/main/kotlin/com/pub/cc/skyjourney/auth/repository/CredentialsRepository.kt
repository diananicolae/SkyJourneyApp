package com.pub.cc.skyjourney.auth.repository

import com.pub.cc.skyjourney.auth.model.ApplicationUser
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CredentialsRepository : MongoRepository<ApplicationUser, String> {
    fun findByUsername(username: String): ApplicationUser?
}