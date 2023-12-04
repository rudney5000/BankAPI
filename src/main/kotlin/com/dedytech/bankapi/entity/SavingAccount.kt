package com.dedytech.bankapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "target_saving_accounts")
data class SavingAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    var saving_account_id: Long,
    var percent:Float,
    var profit: Float,
    var opened: Boolean
)
