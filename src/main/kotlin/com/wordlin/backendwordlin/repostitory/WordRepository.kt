package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.Word
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : JpaRepository<Word, Long> {

    @Query("select w from Word w where w.word = ?1")
    fun findWordEntityByWord(word: String): Word?
}
