package com.wordlin.backendwordlin.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(user: UserEntity) : UserDetails {
    private val username: String
    private val password: String
    private val rolesAndAuthorities: List<GrantedAuthority>

    init {
        username = user.email
        password = user.password
        rolesAndAuthorities = listOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return rolesAndAuthorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    // 4 remaining methods that just return true
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
