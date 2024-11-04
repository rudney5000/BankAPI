package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.dto.request.auth.AuthenticationRequest
import com.dedytech.bankapi.dto.request.auth.RegisterRequest
import com.dedytech.bankapi.dto.request.auth.toUserAuth
import com.dedytech.bankapi.dto.request.auth.toUserEntity
import com.dedytech.bankapi.dto.response.auth.AuthenticationResponse
import com.dedytech.bankapi.entity.User
import com.dedytech.bankapi.repository.UserRepository
import com.dedytech.bankapi.service.AuthenticationService
import com.dedytech.bankapi.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private var repository: UserRepository,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager
): AuthenticationService {
    override fun register(request: RegisterRequest): AuthenticationResponse {
        val user: User = request.toUserEntity().also {
                userObj:User -> userObj.password = passwordEncoder.encode(request.password)
        }

        repository.save(user)
        val jwtToken = jwtService.generateToken(user.toUserAuth())
        return AuthenticationResponse(jwtToken)
    }

    override fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val user = repository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateToken(user.toUserAuth())
        return AuthenticationResponse(jwtToken)
    }

}