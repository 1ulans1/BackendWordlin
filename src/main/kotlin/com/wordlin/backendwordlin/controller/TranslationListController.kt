package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.Translation
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

@RestController
@RequestMapping("/translationLists")
class TranslationListController(
    private val translationSetService: TranslationSetService
) {

    @PostMapping("/add")
    fun addWordSet(principal: Principal, @RequestBody translationSet: TranslationSet): TranslationSet {
        return translationSetService.addTranslationSet(principal.name, translationSet)
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

    @PostMapping("/removeTranslationSet")
    fun removeUserWords(principal: Principal, @RequestBody words: List<TranslationSet>): User {
        return translationSetService.removeUserTranslationSet(principal.name, words)
    }

    @PostMapping("/removeTranslation/{id}")
    fun removeUserWords(
        principal: Principal,
        @PathVariable id: Long,
        @RequestBody words: List<Translation>
    ): TranslationSet {
        return translationSetService.removeUserTranslation(principal.name, id, words)
    }

    @PostMapping("/editTranslation/{id}")
    fun editUserWords(@PathVariable id: Long, @RequestBody words: List<Translation>): TranslationSet {
        return translationSetService.editUserTranslation(id, words)
    }

    @GetMapping("/allPublic")
    fun getPublicTranslationSet(): List<TranslationSet> {
        return translationSetService.getPublicTranslationSet()
    }

}
