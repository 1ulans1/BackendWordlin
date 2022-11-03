package com.wordlin.backendwordlin

import com.wordlin.backendwordlin.config.RsaKeyProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties::class)
class BackendWordlinApplication

fun main(args: Array<String>) {
    runApplication<BackendWordlinApplication>(*args)
}
