package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.Translation
import com.wordlin.backendwordlin.service.TranslateService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/wordSet")
class TranslationController(
    private val translateService: TranslateService
) {
    @PostMapping("/add")
    fun addWordTranslate(@RequestBody translation: Translation): Translation {
        return translateService.addWordTranslation(translation)
    }

    @GetMapping("/getAllByUserLanguages")
    fun getAllByUserLanguages(principal: Principal): List<Translation> {
        return translateService.getAllByLanguage(principal.name)
    }

    @GetMapping("/getAll")
    fun getAllByUserLanguages(): List<Translation> {
        return translateService.getAll()
    }
}
