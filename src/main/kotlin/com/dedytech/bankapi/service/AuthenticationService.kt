package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.WebRs
import com.dedytech.bankapi.dto.request.*
import com.dedytech.bankapi.dto.response.AuthenticationResponse
import com.dedytech.bankapi.dto.response.UsersRs
import jakarta.servlet.http.HttpServletRequest

interface AuthenticationService {
    fun getAllAccount(): WebRs<List<UsersRs>>
    fun register(registerRequest: RegisterRequest): WebRs<AuthenticationResponse>
    fun login(authenticationRequest: AuthenticationRequest): WebRs<AuthenticationResponse>
    fun profile(email: String): WebRs<UsersRs>
//    private fun generateAndAttemptToken(email: String, password: String): WebRs<AuthenticationResponse>
    fun grantAsAdmin(grantedRq: GrantedRq): WebRs<Nothing>
    fun unGrantAsAdmin(grantedRq: GrantedRq): WebRs<Nothing>
    fun forgetPassword(resetPasswordRq: ResetPasswordRq, request: HttpServletRequest): WebRs<String>
    fun resetPassword(resetToken: String, password: PasswordRq): WebRs<String>
}
