package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
import com.wordlin.backendwordlin.service.RecentlyUsedWordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("recentlyUsedWord")
class RecentlyUsedWordController(
    @Autowired val recentlyUsedWordService: RecentlyUsedWordService
) {

    @PostMapping("add")
    fun add(@RequestBody recentlyUsedWord: RecentlyUsedWord) {
        return recentlyUsedWordService.add(recentlyUsedWord)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): RecentlyUsedWord {
        return recentlyUsedWordService.get(id)
    }


}
