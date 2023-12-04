package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.*
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "_account")
data class Account(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String,
    var email: String,
    var surname: String,
    @JsonIgnore
    var password: String,
    var usedService: Boolean,
    var isPercentageOnBalance: Boolean,
    var isUseCashBack: Boolean,
    var isEvenDistribution: Boolean,
//    var superPriorityTarget_id: Long,
    @OneToMany(
        mappedBy = "accounts",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var targets: MutableSet<Target> = mutableSetOf(),


    @Enumerated(EnumType.STRING)
    var role: Role,
    @CreationTimestamp
    var created: Timestamp? = null,
)