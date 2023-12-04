package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.*
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "_account")
data class Account(
    @Id
    @GeneratedValue
    var account_id: Long? = null,
    var name: String = "",
    var email: String = "",
    var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: Role,
//    var subtypes: SubType,
//    var types: Type,
//    var currencies: Currency,
//    var status: Status
)