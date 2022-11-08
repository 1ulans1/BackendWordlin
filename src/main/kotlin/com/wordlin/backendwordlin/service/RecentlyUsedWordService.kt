package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.repostitory.RecentlyUsedWordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RecentlyUsedWordService(
    @Autowired var recentlyUsedWordRepository: RecentlyUsedWordRepository, @Autowired var userService: UserService
) {
    fun add(recentlyUsedWord: RecentlyUsedWord) {
        recentlyUsedWordRepository.save(recentlyUsedWord)
    }

    fun get(id: Long): RecentlyUsedWord {
        return recentlyUsedWordRepository.findById(id).orElseThrow()
    }

    fun findWord(email: String): RecentlyUsedWord {
        (0L..5L).forEach {
            val findOld = findOld(email, it)
            if (findOld != null) {
                return findOld
            }
        }

        throw RuntimeException()
    }

    fun findOld(email: String, level: Long): RecentlyUsedWord? {

        return findAllByLevel(userService.getUserByEmail(email), level).sortedWith() { it1, it2 ->
            it1.data.compareTo(
                it2.data
            )
        }.getOrNull(0)
    }

    fun findAllByLevel(user: User, id: Long): List<RecentlyUsedWord> {
        return recentlyUsedWordRepository.findAllByUserAndLevel(user, id)
    }

    fun saveIfNotExist(recentlyUsedWord: RecentlyUsedWord): RecentlyUsedWord? {
        return if (!recentlyUsedWordRepository.existsByUserAndTranslation(
                recentlyUsedWord.user, recentlyUsedWord.translation
            )
        ) {
            recentlyUsedWordRepository.save(recentlyUsedWord);
        } else {
            null
        }
    }

    fun updateTime(idWord: Long): RecentlyUsedWord {

        return recentlyUsedWordRepository.save(recentlyUsedWordRepository.findRecentlyUsedWordById(idWord)!!
            .also { it.data = LocalDateTime.now() })
    }

    fun updateLevel(id: Long): RecentlyUsedWord {
        return recentlyUsedWordRepository.save(updateTime(id).also {
            it.data = LocalDateTime.now()
            it.level++
        })
    }

    fun remove(user: User, translation: Translation): Int {
        return recentlyUsedWordRepository.deleteByUserAndTranslation(user, translation)
    }
}
