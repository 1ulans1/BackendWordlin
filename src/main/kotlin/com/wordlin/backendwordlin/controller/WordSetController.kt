package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.service.TranslationSetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/word")
class WordSetController(
    private val translationSetService: TranslationSetService
) {

    @PostMapping("/add")
    fun addWordSet(@RequestBody translationSet: TranslationSet): TranslationSet {
        return translationSetService.addWordSet(translationSet)
    }

    @GetMapping("/{id}")
    fun addWordSet(@PathVariable id: Long): TranslationSet {
        return translationSetService.get(id)
    }
}
