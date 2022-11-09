package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.TranslationSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TranslationSetRepository : JpaRepository<TranslationSet, Long> {
    @Query("select t from TranslationSet t where t.publicSet = true")
    fun findAllByPublicSetIsTrue(): List<TranslationSet>
}
