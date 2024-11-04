package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.request.auth.AuthenticationRequest
import com.dedytech.bankapi.dto.request.auth.RegisterRequest
import com.dedytech.bankapi.dto.response.auth.AuthenticationResponse

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse
}