package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.config.jwt.JwtService
import com.dedytech.bankapi.dto.request.AuthenticationRequest
import com.dedytech.bankapi.dto.request.RegisterRequest
import com.dedytech.bankapi.dto.request.toAccountAuth
import com.dedytech.bankapi.dto.request.toAccountEntity
import com.dedytech.bankapi.dto.response.AuthenticationResponse
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.repository.AccountRepository
import com.dedytech.bankapi.service.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private var repository: AccountRepository,
    private var passwordEncoder: PasswordEncoder,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager
):AuthenticationService {
    override fun register(request: RegisterRequest): AuthenticationResponse {
        val account:Account = request.toAccountEntity().also {
            accountObj:Account -> accountObj.password = passwordEncoder.encode(request.password)
        }
        repository.save(account)
        val jwtToken = jwtService.generateToken(account.toAccountAuth())
        return AuthenticationResponse(jwtToken)
    }

    override fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val account = repository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateToken(account.toAccountAuth())
        return AuthenticationResponse(jwtToken)
    }
}
