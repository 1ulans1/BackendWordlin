package com.wordlin.backendwordlin.repostitory

import com.wordlin.backendwordlin.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
}
