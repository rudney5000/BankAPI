package com.dedytech.bankapi.config

import com.dedytech.bankapi.config.jwt.JwtAuthenticationFilter
import com.dedytech.bankapi.repository.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = false
)
class SecurityConfiguration(
    val accountRepository: AccountRepository,
    val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    @Throws(Exception::class)
    fun authenticationManager(
        authConfig: AuthenticationConfiguration
    ): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .cors { cors -> cors.disable() }
            .sessionManagement { sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/auth/**",
                        "/public/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    )
                    .permitAll()
            }
            .authorizeHttpRequests { requests ->
                requests.anyRequest().permitAll() // authenticated() // TODO: remove this when i'll finish
            }
            .exceptionHandling { exception -> exception.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService {
            val account = accountRepository.findByEmail(email = it)
            return@UserDetailsService if (account.isPresent) {
                account.get().toAccountAuth()
            }else{
                throw UsernameNotFoundException("User email $it not found")
            }
        }
    }
}