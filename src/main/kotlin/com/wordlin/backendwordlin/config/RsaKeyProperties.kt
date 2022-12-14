package com.wordlin.backendwordlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConstructorBinding
@ConfigurationProperties(prefix = "rsa")
data class RsaKeyProperties(
    var publicKey: RSAPublicKey, var privateKey: RSAPrivateKey
)
