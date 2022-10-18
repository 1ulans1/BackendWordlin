package com.wordlin.backendwordlin.entity

import javax.persistence.*

@Entity
data class WordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var word: String,

    @ManyToMany(mappedBy = "translation")
    var wordTranslations: Set<WordTranslationEntity>
)
