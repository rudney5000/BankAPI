package com.dedytech.bankapi.security.auth

data class AuthenticationRequest(
    var email: String,
    var password: String
)