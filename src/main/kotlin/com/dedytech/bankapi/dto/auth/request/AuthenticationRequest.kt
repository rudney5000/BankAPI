package com.dedytech.bankapi.dto.auth.request

data class AuthenticationRequest(
    var email: String,
    var password: String
)