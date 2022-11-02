package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.entity.UserEntity
import com.wordlin.backendwordlin.service.TokenService
import com.wordlin.backendwordlin.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    @Autowired private val tokenService: TokenService,
    @Autowired private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/token")
    fun token(authentication: Authentication): String {
        logger.debug("Token requested for user {}", authentication.name)
        val token = tokenService.generateToken(authentication)
        logger.debug("Token graned {}", token)
        return token
    }

    @PostMapping("/signup")
    fun signup(@RequestBody user: UserEntity): UserEntity {
        return userService.addUser(user)
    }
}
