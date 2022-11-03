package com.wordlin.backendwordlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HomeController {
    @GetMapping
    fun home(principal: Principal): String {
        return "hello " + principal.name
    }
}
