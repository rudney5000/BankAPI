package com.dedytech.bankapi.security.auth

import com.dedytech.bankapi.enums.*

data class RegisterRequest(
    var account_id: Long? = null,
    var name: String = "",
    var official_name: String = "",
    var email: String = "",
    var password: String = "",
//    var role: Role,
//    var subtypes: SubType,
//    var types: Type,
//    var currencies: Currency,
//    var status: Status
)