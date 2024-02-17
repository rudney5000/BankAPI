package com.dedytech.bankapi.dto.response

data class UsersRs(
    val id: Long,
    val name: String,
    val email: String,
    val roles: Set<String>?
)
