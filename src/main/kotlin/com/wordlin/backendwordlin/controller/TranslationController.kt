package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.service.TranslateService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/word")
class TranslationController(
    private val translateService: TranslateService
) {
    @PostMapping("/add")
    fun addWordTranslate(principal: Principal, @RequestBody translation: Translation): Translation {
        return translateService.addWordTranslation(principal.name, translation)
    }

    @GetMapping("/allByUserLanguages")
    fun getAllByUserLanguages(principal: Principal): List<Translation> {
        return translateService.getAllByLanguage(principal.name)
    }

    @GetMapping("/all")
    fun getAllByUserLanguages(): List<Translation> {
        return translateService.getAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Translation {
        return translateService.get(id)
    }
}
