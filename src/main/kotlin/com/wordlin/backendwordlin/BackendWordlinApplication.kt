package com.wordlin.backendwordlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendWordlinApplication

fun main(args: Array<String>) {
    runApplication<BackendWordlinApplication>(*args)
}
