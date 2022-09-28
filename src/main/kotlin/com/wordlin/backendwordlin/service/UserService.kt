package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.dto.UserDTO
import com.wordlin.backendwordlin.mapper.Mapper
import com.wordlin.backendwordlin.repostitory.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    fun addUser(user: UserDTO): UserDTO {
        repository.save(Mapper.userDtoToEntity(user))
        return user
    }

    fun getUser(id: Long): UserDTO {
        return Mapper.userEntityToDto(repository.findById(id).get())
    }
}
