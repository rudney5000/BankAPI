package com.dedytech.bankapi.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {
    @Value("\${security.jwt.token.secret-key}")
    lateinit var plainSecretKey: String
    @Value("\${security.jwt.token-length}")
    lateinit var expireLength: String
}