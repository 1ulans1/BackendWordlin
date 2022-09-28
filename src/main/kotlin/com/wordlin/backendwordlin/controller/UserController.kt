package com.wordlin.backendwordlin.controller

import com.wordlin.backendwordlin.dto.UserDTO
import com.wordlin.backendwordlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController() {

    @Autowired
    lateinit var service: UserService

    @PostMapping
    fun addUser(user: UserDTO): UserDTO {
        return service.addUser(user)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): UserDTO {
        return service.getUser(id)
    }
}
