package com.wordlin.backendwordlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfigurerImpl(
    @Autowired private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService) // user store 1
            .passwordEncoder(encoder)
        auth
            .inMemoryAuthentication() // user store 2
            .withUser("Admin").password("hardcoded").roles("USER")
            .and().passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/test").hasAnyRole("USER")
            .anyRequest().permitAll() // make remaining endpoints public (including POST /register)
            .and()
            .csrf().disable() // disabling CSRF will allow sending POST request using Postman
            .httpBasic() // enables basic auth.
    }

    @get:Bean
    val encoder: PasswordEncoder
        get() = BCryptPasswordEncoder()
}