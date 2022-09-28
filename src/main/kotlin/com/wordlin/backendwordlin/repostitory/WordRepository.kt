package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.WordEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : JpaRepository<WordEntity, Long> {
}