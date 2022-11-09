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
    @Query("select (count(r) > 0) from RecentlyUsedWord r where r.user = ?1 and r.translation = ?2")
    fun existsByUserAndTranslation(user: User, translation: Translation): Boolean
    @Query("select r from RecentlyUsedWord r where r.user = ?1 and r.level = ?2")
    fun findAllByUserAndLevel(user: User, level: Long): List<RecentlyUsedWord>
    @Query("select r from RecentlyUsedWord r where r.user = ?1")
    fun findAllByUser(user: User): List<RecentlyUsedWord>
    @Query("select r from RecentlyUsedWord r where r.id = ?1")
    fun findRecentlyUsedWordById(id: Long): RecentlyUsedWord?
    @Modifying
    @Transactional
    @Query("delete from RecentlyUsedWord r where r.user = ?1 and r.translation = ?2")
    fun deleteByUserAndTranslation(user: User, translation: Translation): Int
}
