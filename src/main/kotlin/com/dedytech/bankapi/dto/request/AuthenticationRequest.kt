package com.dedytech.bankapi.dto.request

data class AuthenticationRequest(
    var email: String,
    var password: String
)