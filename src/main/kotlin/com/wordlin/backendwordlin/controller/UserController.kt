package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.TranslationSet
import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController(
    @Autowired var userService: UserService
) {

    @GetMapping("/current")
    fun getUserWords(principal: Principal): User {
        return userService.getUserByEmail(principal.name)
    }

    @PostMapping("/existTranslationSet")
    fun existsByIdAndTranslationSet(principal: Principal, @RequestBody translationSet: TranslationSet): Boolean {
        return userService.existsByIdAndTranslationSet(principal.name, translationSet)
    }
}
