package com.dedytech.bankapi.dto.auth

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.enums.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AccountAuth(
    var name: String,
    var email: String,
    private var password: String,
    var role: Role,
): UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority(role.name))

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}

fun Account.toAccountAuth(): AccountAuth = AccountAuth(
    name = this.name,
    email = this.email,
    password = this.password,
    role = this.role
)
