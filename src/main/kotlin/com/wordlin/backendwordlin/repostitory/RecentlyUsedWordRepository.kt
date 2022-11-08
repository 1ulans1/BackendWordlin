package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface RecentlyUsedWordRepository : JpaRepository<RecentlyUsedWord, Long> {

    fun existsByUserAndTranslation(user: User, translation: Translation): Boolean
    fun findAllByUserAndLevel(user: User, level: Long): List<RecentlyUsedWord>
    fun findAllByUser(user: User): List<RecentlyUsedWord>
    fun findRecentlyUsedWordById(id: Long): RecentlyUsedWord?

    fun deleteByUserAndTranslation(user: User, translation: Translation): Int
}
