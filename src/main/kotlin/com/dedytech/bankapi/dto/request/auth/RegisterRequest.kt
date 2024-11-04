package com.dedytech.bankapi.dto.request.auth

import com.dedytech.bankapi.entity.User
import com.dedytech.bankapi.enums.Role

data class RegisterRequest (
    var name: String,
    var password: String,
    var email: String,
//    var companies: MutableList<Company> = mutableListOf(),
)

fun RegisterRequest.toUserEntity(): User = User(
    name = this.name,
    password = this.password,
    email = this.email,
//    companies = this.companies,
    roles = Role.USER
)
