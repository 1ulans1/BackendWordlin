package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.RecentlyUsedWord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecentlyUsedWordRepository : JpaRepository<RecentlyUsedWord, Long> {
}
