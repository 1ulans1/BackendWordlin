package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.dto.WordDTO
import com.wordlin.backendwordlin.mapper.Mapper
import com.wordlin.backendwordlin.repostitory.WordRepository
import org.springframework.stereotype.Service

@Service
class WordService(
    private val repository: WordRepository
) {
    fun addWord(word: WordDTO): WordDTO {
        repository.save(Mapper.wordDtoToEntity(word))
        return word
    }

    fun getWord(id: Long): WordDTO {
        return Mapper.wordEntityToDto(repository.findById(id).orElseGet(null))
    }
}
