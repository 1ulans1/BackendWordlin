package com.wordlin.backendwordlin.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class RecentlyUsedWord(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToOne
    var translation: Translation,

    @OneToOne
    var user: User,

    var data: LocalDateTime = LocalDateTime.now(),

    var level: Long = 0L
)

