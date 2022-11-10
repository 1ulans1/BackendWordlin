package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    fun getByEmail(email: String): User?

    fun existsByEmailAndTranslationSet(email: String, translationSet: TranslationSet): Boolean
}
