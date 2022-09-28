package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.dto.WordDTO
import com.wordlin.backendwordlin.service.WordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/word")
class WordController @Autowired constructor(
    private var service: WordService
) {

    @PostMapping
    fun addUser(word: WordDTO): WordDTO? {
        return service.addWord(word)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): WordDTO? {
        return service.getWord(id)

    }
}
