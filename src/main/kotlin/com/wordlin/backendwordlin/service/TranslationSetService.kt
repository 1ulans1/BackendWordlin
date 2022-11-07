package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.exeption.WordSetNotFoundException
import com.wordlin.backendwordlin.repostitory.TranslationSetRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.jvm.optionals.getOrElse

@Service
class TranslationSetService(
    private val translationSetRepository: TranslationSetRepository,
    private val userService: UserService,
    private val translationService: TranslateService
) {

    fun addTranslationSet(translationSet: TranslationSet): TranslationSet {
        translationSet.translations = translationSet.translations.onEach { translationService.addWordTranslation(it) }
        return translationSetRepository.save(translationSet)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun get(id: Long): TranslationSet {
        return translationSetRepository.findById(id).getOrElse { throw WordSetNotFoundException() }
    }

    fun getPublicTranslationSet(): List<TranslationSet> {
        return translationSetRepository.findAllByPublicSetIsTrue()
    }

    fun getUserTranslation(email: String): List<TranslationSet> {
        return userService.getUserByEmail(email).translationSet
    }

    @Transactional
    fun addUserTranslation(name: String, set2: List<TranslationSet>): User {
        val user = userService.getUserByEmail(name)

        val userTranslations = mutableListOf<TranslationSet>()

        val union: MutableList<TranslationSet> = user.translationSet as MutableList<TranslationSet>
        set2.forEach { (id) -> union.removeIf { (id1): TranslationSet -> id1 == id } }
        union.addAll(set2)

        println(userTranslations)
        user.translationSet = union.onEach { translationSetRepository.save(it) }
        return userService.addUser(user)
    }

    @Transactional
    fun removeUserTranslationSet(name: String, translationSets: List<TranslationSet>): User {
        val user = userService.getUserByEmail(name)

        val userTranslations = user.translationSet as MutableList

        val setId = translationSets.map { it.id!! }

        userTranslations.removeIf { setId.contains(it.id) }

        return userService.addUser(user)
    }

    fun removeUserTranslation(id: Long, translations: List<Translation>): TranslationSet {

        val translationSet = translationSetRepository.findById(id).orElseThrow()

        val translationsOld = translationSet.translations as MutableList

        val setId = translations.map { it.id!! }

        translationsOld.removeIf { setId.contains(it.id) }

        return translationSetRepository.save(translationSet)

    }

    fun editUserTranslation(id: Long, translations: List<Translation>): TranslationSet {

        val translationSet = translationSetRepository.findById(id).orElseThrow()

        val translationsOld = translationSet.translations as MutableList

        translationSet.translations = translationsOld.map { it1 ->
            translations.find {
                it1.id == it.id
            } ?: it1
        }

        return translationSetRepository.save(translationSet)
    }
}
