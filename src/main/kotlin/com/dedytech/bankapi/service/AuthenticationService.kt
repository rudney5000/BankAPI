package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.request.AuthenticationRequest
import com.dedytech.bankapi.dto.response.AuthenticationResponse
import com.dedytech.bankapi.dto.request.RegisterRequest

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse

}
