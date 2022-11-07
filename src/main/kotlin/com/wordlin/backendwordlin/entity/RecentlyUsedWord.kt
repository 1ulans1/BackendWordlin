package com.wordlin.backendwordlin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class RecentlyUsedWord(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @OneToOne
    var translation: Translation,

    @OneToOne
    var user: User,

    var level: Long = 0L
)

