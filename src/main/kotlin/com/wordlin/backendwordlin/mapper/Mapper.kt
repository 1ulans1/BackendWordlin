package com.wordlin.backendwordlin.mapper

import com.wordlin.backendwordlin.dto.UserDTO
import com.wordlin.backendwordlin.dto.WordDTO
import com.wordlin.backendwordlin.entity.UserEntity
import com.wordlin.backendwordlin.entity.WordEntity

object Mapper {
    fun userDtoToEntity(userDTO: UserDTO): UserEntity {
        return UserEntity(email = userDTO.email, password = userDTO.password)
    }

    fun userEntityToDto(userEntity: UserEntity): UserDTO {
        return UserDTO(userEntity.email, userEntity.password)
    }

    fun wordDtoToEntity(wordDTO: WordDTO): WordEntity {
        return WordEntity(word = wordDTO.word, translate = wordDTO.translate)
    }

    fun wordEntityToDto(wordEntity: WordEntity): WordDTO {
        return WordDTO(word = wordEntity.word, translate = wordEntity.translate)
    }
}