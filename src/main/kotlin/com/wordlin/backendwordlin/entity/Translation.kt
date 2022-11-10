package com.wordlin.backendwordlin.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Translation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @OneToOne(cascade = [CascadeType.PERSIST])
    var word: Word,
    var nativeLanguage: Language = Language.UK,
    var targetLanguage: Language = Language.EN,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "word_translate",
        joinColumns = [JoinColumn(name = "word_id")],
        inverseJoinColumns = [JoinColumn(name = "translation_id")]
    )
    var translation: List<Word>,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Translation

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(word = $word )"
    }
}
