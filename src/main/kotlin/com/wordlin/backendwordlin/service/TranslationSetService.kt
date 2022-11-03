package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.exeption.WordSetNotFoundException
import com.wordlin.backendwordlin.repostitory.TranslationSetRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class TranslationSetService(
    private val translationSetRepository: TranslationSetRepository
) {
    fun addWordSet(translationSet: TranslationSet): TranslationSet {
        return translationSetRepository.save(translationSet)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun get(id: Long): TranslationSet {
        return translationSetRepository.findById(id).getOrElse { throw WordSetNotFoundException() }
    }

    fun getPublicTranslationSet(): List<TranslationSet> {
        return translationSetRepository.findAllByPublicSetIsTrue()
    }
}
