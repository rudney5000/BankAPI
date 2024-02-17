package com.dedytech.bankapi.dto.response

import com.dedytech.bankapi.entity.Account

class UserRs (
    val id: Long,
    val name: String,
    val email: String,
)

fun UserRs.toAccount() = Account(
    id = id,
    name = name,
    email = email
)

fun Account.toUserRs() = UserRs(
    id = id,
    name = name,
    email = email,
)