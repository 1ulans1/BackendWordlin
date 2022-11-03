package com.wordlin.backendwordlin.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class TranslationSet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var publicName: String,

    @ManyToMany
    @JoinTable(
        name = "words_translation",
        joinColumns = [JoinColumn(name = "set_id")],
        inverseJoinColumns = [JoinColumn(name = "translation_id")]
    )
    var translations: List<Translation?>? = null,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var publicSet: Boolean = false,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TranslationSet

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(publicName = $publicName )"
    }
}
