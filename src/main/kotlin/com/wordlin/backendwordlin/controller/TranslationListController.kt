package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.service.TranslationSetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.LinkedList

@RestController
@RequestMapping("/translationLists")
class TranslationListController(
    private val translationSetService: TranslationSetService
) {

    @PostMapping("/add")
    fun addWordSet(@RequestBody translationSet: TranslationSet): TranslationSet {
        return translationSetService.addTranslationSet(translationSet)
    }

    @GetMapping("/{id}")
    fun addWordSet(@PathVariable id: Long): TranslationSet {
        return translationSetService.get(id)
    }

    @GetMapping("/userTranslationSet")
    fun getUserWords(principal: Principal): List<TranslationSet> {
        return translationSetService.getUserTranslation(principal.name)
    }

    @PostMapping("/addTranslationSet")
    fun addUserWords(principal: Principal, @RequestBody words: List<TranslationSet>): User {
        return translationSetService.addUserTranslation(principal.name, words)
    }

    @GetMapping("/allPublic")
    fun getPublicTranslationSet(): List<TranslationSet> {
        return translationSetService.getPublicTranslationSet()
    }

}
