package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
import com.wordlin.backendwordlin.repostitory.RecentlyUsedWordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecentlyUsedWordService(
    @Autowired var recentlyUsedWordRepository: RecentlyUsedWordRepository
) {
    fun add(recentlyUsedWord: RecentlyUsedWord) {
        recentlyUsedWordRepository.save(recentlyUsedWord)
    }

    fun get(id: Long): RecentlyUsedWord {
        return recentlyUsedWordRepository.findById(id).orElseThrow()
    }
}
