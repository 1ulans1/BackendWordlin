package com.wordlin.backendwordlin.service

import com.wordlin.backendwordlin.repostitory.UserRepository
import com.wordlin.backendwordlin.entity.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    @Autowired private val userRepo: UserRepository
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.getByEmail(username) ?: throw UsernameNotFoundException("Not found: $username")
        return UserDetailsImpl(user)
    }
}
