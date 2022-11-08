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
import java.security.Principal

@RestController
@RequestMapping("recentlyUsedWord")
class RecentlyUsedWordController(
    @Autowired val recentlyUsedWordService: RecentlyUsedWordService
) {

    @PostMapping("add")
    fun add(@RequestBody recentlyUsedWord: RecentlyUsedWord): RecentlyUsedWord {
        return recentlyUsedWordService.saveIfNotExist(recentlyUsedWord)!!
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): RecentlyUsedWord {
        return recentlyUsedWordService.get(id)
    }

    @GetMapping("/getOld")
    fun get(principal: Principal): RecentlyUsedWord {
        return recentlyUsedWordService.findWord(principal.name);
    }

    @PostMapping("/updateTime/{idWord}")
    fun updateTime(@PathVariable idWord: Long): RecentlyUsedWord {
        return recentlyUsedWordService.updateTime(idWord);
    }

    @PostMapping("/updateLevel/{id}")
    fun updateLevel(@PathVariable id: Long): RecentlyUsedWord {
        return recentlyUsedWordService.updateLevel(id)
    }
}
