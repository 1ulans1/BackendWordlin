package com.wordlin.backendwordlin.entity

import javax.persistence.*

@Entity
data class UserWords(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var nameSet: String,

    @ManyToMany
    @JoinTable(
        name = "user_word_set",
        joinColumns = [JoinColumn(name = "user_set_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var userEntity: Set<UserEntity>,

    @ManyToMany
    @JoinTable(
        name = "user_words_set",
        joinColumns = [JoinColumn(name = "user_set_id")],
        inverseJoinColumns = [JoinColumn(name = "words_id")]
    )
    var wordSet: Set<WordSet>
)
