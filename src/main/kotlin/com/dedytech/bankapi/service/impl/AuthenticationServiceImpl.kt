package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.config.jwt.JwtService
import com.dedytech.bankapi.dto.WebRs
import com.dedytech.bankapi.dto.request.*
import com.dedytech.bankapi.dto.response.AccountAuth
import com.dedytech.bankapi.dto.response.AuthenticationResponse
import com.dedytech.bankapi.dto.response.UsersRs
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.exception.ErrorException
import com.dedytech.bankapi.repository.AccountRepository
import com.dedytech.bankapi.repository.RoleRepository
import com.dedytech.bankapi.service.AuthenticationService
import com.dedytech.bankapi.service.EmailService
import com.dedytech.bankapi.utils.Constant.ROLE_USER
import com.dedytech.bankapi.utils.ValidationUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthenticationServiceImpl(
    val repository: AccountRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager,
    val validationUtil: ValidationUtil,
    val emailService: EmailService
):AuthenticationService {
    override fun getAllAccount(): WebRs<List<UsersRs>> {
        return WebRs(data = repository.findAll().stream().map { it.toUsersRs() }.collect(Collectors.toList()))
    }

    override fun register(registerRequest: RegisterRequest): WebRs<AuthenticationResponse> {
        validationUtil.validate(registerRequest)
        if (repository.findByEmail(email = registerRequest.email).isPresent){
            throw ErrorException("Email address is already registered!")
        }
        val encodePwdAccount = registerRequest.copy(
            password = passwordEncoder.encode(registerRequest.password)
        )
        val account = encodePwdAccount.toAccountEntity()
        account.roles.add(roleRepository.findByName(name = ROLE_USER).get())
        repository.save(account)
        return generateAndAttemptToken(email = registerRequest.email, password = registerRequest.password)
    }



    override fun login(authenticationRequest: AuthenticationRequest): WebRs<AuthenticationResponse> {
        if (!repository.findByEmail(email = authenticationRequest.email).isPresent) {
            throw ErrorException("Email and password are not correctly!")
        }
        return generateAndAttemptToken(email = authenticationRequest.email, password = authenticationRequest.password)
    }

    override fun profile(email: String): WebRs<UsersRs> {
        if (!repository.findByEmail(email = email).isPresent) {
            throw ErrorException("Access token is not valid!")
        }
        return  WebRs(data = repository.findByEmail(email = email).get().toUsersRs())
    }

    override fun grantAsAdmin(grantedRq: GrantedRq): WebRs<Nothing> {
        TODO("Not yet implemented")
    }

    override fun unGrantAsAdmin(grantedRq: GrantedRq): WebRs<Nothing> {
        TODO("Not yet implemented")
    }

    override fun forgetPassword(resetPasswordRq: ResetPasswordRq, request: HttpServletRequest): WebRs<String> {
        TODO("Not yet implemented")
    }

    override fun resetPassword(resetToken: String, password: PasswordRq): WebRs<String> {
        TODO("Not yet implemented")
    }


    private fun generateAndAttemptToken(email: String, password: String): WebRs<AuthenticationResponse> {
        try {
            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(email, password)
            )
            val accountAuth: AccountAuth = authentication.principal as AccountAuth
            val token = jwtService.generateTokenAccessToken(accountAuth = accountAuth)
            return WebRs(data = AuthenticationResponse(token = token))
        }catch (e: BadCredentialsException) {
            throw ErrorException("Email address or password is incorrect!")
        }
    }
}