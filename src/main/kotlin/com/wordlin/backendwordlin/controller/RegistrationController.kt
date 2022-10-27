package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.UserEntity
import com.wordlin.backendwordlin.repostitory.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(var userRepo: UserRepository, var encoder: PasswordEncoder) {

    @PostMapping("/register")
    fun register(@RequestBody user: UserEntity) {
        user.password = encoder.encode(user.password)
        userRepo.save(user)
    }
}
