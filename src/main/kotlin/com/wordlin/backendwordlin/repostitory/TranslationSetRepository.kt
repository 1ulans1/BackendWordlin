package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.TranslationSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TranslationSetRepository : JpaRepository<TranslationSet, Long> {
    fun findAllByPublicSetIsTrue(): List<TranslationSet>
}
