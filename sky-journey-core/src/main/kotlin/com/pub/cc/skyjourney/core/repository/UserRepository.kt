package com.pub.cc.skyjourney.core.repository

import com.pub.cc.skyjourney.core.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String>