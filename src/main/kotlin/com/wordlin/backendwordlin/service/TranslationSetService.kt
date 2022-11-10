package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
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
    private val recentlyUsedWordService: RecentlyUsedWordService,
    private val translationService: TranslateService
) {

    fun addTranslationSet(name: String, translationSet: TranslationSet): TranslationSet {
        translationSet.translations =
            translationSet.translations.onEach { translationService.addWordTranslation(name, it) }
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

        set2.forEach {
            it.translations.forEach { translation ->
                recentlyUsedWordService.add(RecentlyUsedWord(user = user, translation = translation))
            }
        }

        println(userTranslations)
        user.translationSet = union.onEach { translationSetRepository.save(it) }
        return userService.addUser(user)
    }

    @Transactional
    fun removeUserTranslationSet(email: String, translationSets: List<TranslationSet>): User {
        val user = userService.getUserByEmail(email)

        val userTranslations = user.translationSet as MutableList

        val setId = translationSets.map { it.id!! }

        userTranslations.removeIf { translationSet ->
            (setId.contains(translationSet.id)).also {
                if (it) {
                    translationSet.translations.forEach { translation ->
                        recentlyUsedWordService.remove(user, translation)
                    }
                }
            }
        }

        return userService.addUser(user)
    }

    @Transactional
    fun removeUserTranslation(email: String, id: Long, translations: List<Translation>): TranslationSet {

        val user = userService.getUserByEmail(email)

        val translationSet = translationSetRepository.findById(id).orElseThrow()

        val translationsOld = translationSet.translations as MutableList

        val setId = translations.map { it.id!! }

        translationsOld.removeIf { translation ->
            setId.contains(translation.id).also {
                if (it) {
                    recentlyUsedWordService.remove(user, translation)
                }
            }
        }

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

    fun addPublicTranslationSet(translationSet: TranslationSet): TranslationSet {
        return translationSetRepository.save(translationSet)
    }
}
