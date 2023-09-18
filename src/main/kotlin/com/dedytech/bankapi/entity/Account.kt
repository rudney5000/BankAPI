package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.*
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp

@Entity
@Table(name = "_account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var account_id: Long? = null,
    var name: String = "",
    var official_name: String = "",
    var email: String = "",
    private var password: String = "",
    var superPriorityTarget_id: Long? = null,
    @OneToMany(
        targetEntity = Target::class,
        mappedBy = "account",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var targets: MutableList<Target> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var role: Role,
    @Enumerated(EnumType.STRING)
    var subtypes: SubType,
//    var types: Type,
    @Enumerated(EnumType.STRING)
    var currencies: Currency,
    @Enumerated(EnumType.STRING)
    var status: Status,
    var isUseCashback: Boolean,
    var isEvenDistribution: Boolean,
    var isPercentageOnBalance: Boolean,
    var usedService: Boolean,
    @CreationTimestamp
    var createdAt: Timestamp?=null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
): UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority(role.name))

    override fun getPassword() = password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}
