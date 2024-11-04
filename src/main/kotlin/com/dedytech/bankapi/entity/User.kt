package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "_users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    @JsonIgnore
    var password: String,

    var email: String,

    @Enumerated(EnumType.STRING)
    var roles: Role,

//    @OneToMany(
//        mappedBy = "users",
//        cascade =[CascadeType.ALL],
//        fetch = FetchType.EAGER
//    )
//    var companies: MutableList<Company> = mutableListOf(),
)
