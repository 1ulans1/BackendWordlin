package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.exeption.UserNotAssignLanguagesException
import com.wordlin.backendwordlin.exeption.UserNotFoundException
import com.wordlin.backendwordlin.repostitory.UserRepository
import com.wordlin.backendwordlin.repostitory.WordRepository
import com.wordlin.backendwordlin.repostitory.WordTranslationRepository
import org.springframework.stereotype.Service

@Service
class TranslateService(
    private val wordRepository: WordRepository,
    private val userRepository: UserRepository,
    private val wordTranslationRepository: WordTranslationRepository
) {

    fun addWordTranslation(translation: Translation): Translation {

        translation.word = wordRepository.findWordEntityByWord(
            translation.word.word
        ) ?: wordRepository.save(
            translation.word
        )

        translation.translation = translation.translation.map {
            wordRepository.findWordEntityByWord(it.word) ?: wordRepository.save(it)
        }

        return wordTranslationRepository.save(translation)
    }

    fun getAllByLanguage(email: String): List<Translation> {
        val user = userRepository.getByEmail(email) ?: throw UserNotFoundException()

        return wordTranslationRepository.findAllByNativeLanguageAndTargetLanguage(
            user.nativeLanguage ?: throw UserNotAssignLanguagesException(),
            user.targetLanguage ?: throw UserNotAssignLanguagesException()
        )
    }

    fun getAll(): List<Translation> {
        return wordTranslationRepository.findAll()
    }

    fun removeUserTranslation(id: Long, words: List<Translation>): User {
        TODO("Not yet implemented")
    }

    fun get(id: Long): Translation {
        return wordTranslationRepository.findById(id).orElseThrow()
    }
}
