package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.Language
import com.wordlin.backendwordlin.entity.Translation
import org.springframework.data.jpa.repository.JpaRepository

interface WordTranslationRepository : JpaRepository<Translation, Long> {
    fun findAllByNativeLanguageAndTargetLanguage(
        nativeLanguage: Language, targetLanguage: Language
    ): List<Translation>

    fun findTranslationsByNativeLanguageAndTargetLanguage(
        nativeLanguage: Language,
        targetLanguage: Language
    ): List<Translation>
}
