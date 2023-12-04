package com.dedytech.bankapi.dto.request

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.enums.*

data class RegisterRequest(
    var name: String = "",
    var email: String = "",
    var password: String = "",
//    var subtypes: SubType,
//    var types: Type,
//    var currencies: Currency,
//    var status: Status
)

fun RegisterRequest.toAccountEntity():Account = Account(
    name = this.name,
    email = this.email,
    password = this.password,
    role = Role.USER
)