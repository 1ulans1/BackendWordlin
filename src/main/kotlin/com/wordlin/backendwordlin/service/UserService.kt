package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.entity.User
import com.wordlin.backendwordlin.exeption.UserStatusSetException
import com.wordlin.backendwordlin.repostitory.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    fun addUser(user: User): User {
        if (user.targetLanguage != null && user.nativeLanguage != null && user.targetLanguage!! == user.nativeLanguage!!) throw UserStatusSetException()
        return repository.save(user)
    }

    fun getUser(id: Long): User {
        return repository.getReferenceById(id)
    }

    fun getUserByEmail(email: String): User {
        return repository.getByEmail(email)!!
    }
}
