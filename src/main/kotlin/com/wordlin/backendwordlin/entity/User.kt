package com.wordlin.backendwordlin.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    @Column(unique = true)
    var email: String,
    var password: String,

    @ManyToMany
    @JoinTable(
        name = "user_translationSet",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "translation_set_id")]
    )
    var translationSet: List<TranslationSet?>? = emptyList(),
    var nativeLanguage: Language? = null,
    var targetLanguage: Language? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(username = $username , email = $email , password = $password , nativeLanguage = $nativeLanguage , targetLanguage = $targetLanguage )"
    }


}
