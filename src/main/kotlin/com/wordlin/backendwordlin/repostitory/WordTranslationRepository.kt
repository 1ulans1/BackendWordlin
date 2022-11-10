package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.Language
import com.wordlin.backendwordlin.entity.Translation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface WordTranslationRepository : JpaRepository<Translation, Long> {
    @Query("select t from Translation t where t.nativeLanguage = ?1 and t.targetLanguage = ?2")
    fun findAllByNativeLanguageAndTargetLanguage(
        nativeLanguage: Language, targetLanguage: Language
    ): List<Translation>

    @Query("select t from Translation t where t.nativeLanguage = ?1 and t.targetLanguage = ?2")
    fun findTranslationsByNativeLanguageAndTargetLanguage(
        nativeLanguage: Language,
        targetLanguage: Language
    ): List<Translation>
}
