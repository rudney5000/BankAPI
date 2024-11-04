package com.dedytech.bankapi.dto.request.auth

import com.dedytech.bankapi.entity.User
import com.dedytech.bankapi.enums.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserAuth(
    var name: String,
    private var password: String,
    var email: String,
    var roles: Role
): UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority(roles.name))

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}

fun UserAuth.toUserEntity(): User = User(
    name = this.name,
    email = this.email,
    password = this.password,
    roles = this.roles
)

fun User.toUserAuth(): UserAuth = UserAuth(
    name = this.name,
    email = this.email,
    password = this.password,
    roles = this.roles
)
