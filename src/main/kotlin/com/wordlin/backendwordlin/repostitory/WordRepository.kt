package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.Word
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : JpaRepository<Word, Long> {
    //    fun findByWord(word: String): WordEntity?
//    fun findWordEntityByWordEntity(word: WordEntity): WordEntity?
    fun findWordEntityByWord(word: String): Word?
}
