package com.wordlin.backendwordlin.entity

import javax.persistence.*

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var email: String,
    var password: String,

    var nativeLanguage: String,
    var targetLanguage: String,

    @ManyToMany(mappedBy = "userEntity")
    var wordSet: Set<UserWords>
)
