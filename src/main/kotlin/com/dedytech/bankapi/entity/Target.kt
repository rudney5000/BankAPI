package com.dedytech.bankapi.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "account_targets")
data class Target(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var icon_id: Long,
    var name: String,
    var sum:Float,
    var amount: Float,
    var isCompleted:Boolean,

    @CreationTimestamp
    var created: Timestamp,

//    @Enumerated(EnumType.ORDINAL)
//    var priority: TargetPriority,

//    @Column(name = "account-id")
//    var accountId: Long,

//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    @JsonIgnore
//    var accounts: Account
)
