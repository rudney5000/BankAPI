package com.dedytech.bankapi.dto.request.auth

data class AuthenticationRequest(
    var email: String,
    var password: String
)
