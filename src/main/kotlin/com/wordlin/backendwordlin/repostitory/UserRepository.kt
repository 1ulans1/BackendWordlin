package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getByEmail(email: String): User?
}
