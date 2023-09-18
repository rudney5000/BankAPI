package com.dedytech.bankapi.security.service

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.enums.Role
import com.dedytech.bankapi.repository.AccountRepository
import com.dedytech.bankapi.security.auth.AuthenticationRequest
import com.dedytech.bankapi.security.auth.AuthenticationResponse
import com.dedytech.bankapi.security.auth.RegisterRequest
import com.dedytech.bankapi.security.jwt.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class AuthenticationService(
    private var repository: AccountRepository,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        val account = Account(
            name = request.name,
            official_name = request.official_name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = Role.USER
        )

        repository.save(account)
        val jwtToken = jwtService.generateToken(account)
        return AuthenticationResponse(jwtToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val account = repository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateToken(account)
        return AuthenticationResponse(jwtToken)
    }
}