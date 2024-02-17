package com.dedytech.bankapi.entity

import com.dedytech.bankapi.dto.response.AccountAuth
import com.dedytech.bankapi.dto.response.UserRs
import com.dedytech.bankapi.dto.response.UsersRs
import com.dedytech.bankapi.enums.*
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "_account")
data class Account(
    @Id
    @GeneratedValue
    var id: Long = 0,
    @Column(unique = true, nullable = false)
    var name: String,
    @Column(unique = true, nullable = false)
    var email: String,
//    var surname: String,
    @JsonIgnore
    var password: String? = null,
//    var usedService: Boolean,
//    var isPercentageOnBalance: Boolean,
//    var isUseCashBack: Boolean,
//    var isEvenDistribution: Boolean,
//    var superPriorityTarget_id: Long,
//    @OneToMany(
//        mappedBy = "accounts",
//        cascade = [CascadeType.ALL],
//        fetch = FetchType.EAGER
//    )
//    var targets: MutableSet<Target> = mutableSetOf(),
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: MutableSet<Role> = mutableSetOf(),
    @CreationTimestamp
    var created: Timestamp? = null,
    @UpdateTimestamp
    var updated: Timestamp? = null
){
    fun toAccountAuth(): AccountAuth = AccountAuth(
        email = this.email,
        password = this.password,
        roles = this.roles.map { it.name }
    )

    fun toUsersRs(): UsersRs = UsersRs(
        id = this.id,
        name = this.name,
        email = this.email,
        roles = roles.map { it.name }.toSet()
    )
}