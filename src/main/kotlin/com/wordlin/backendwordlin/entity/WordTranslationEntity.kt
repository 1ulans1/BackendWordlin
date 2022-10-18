package com.wordlin.backendwordlin.entity

import javax.persistence.*

@Entity
data class WordTranslationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    var word: WordEntity,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "word_translate",
        joinColumns = [JoinColumn(name = "word_id")],
        inverseJoinColumns = [JoinColumn(name = "translation_id")]
    )
    var translation: Set<WordEntity>,

    @ManyToMany(mappedBy = "wordTranslation")
    var wordSet: List<WordSet>
)
