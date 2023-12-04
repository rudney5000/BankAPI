package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.auth.request.AuthenticationRequest
import com.dedytech.bankapi.dto.auth.response.AuthenticationResponse
import com.dedytech.bankapi.dto.auth.request.RegisterRequest

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse

}
