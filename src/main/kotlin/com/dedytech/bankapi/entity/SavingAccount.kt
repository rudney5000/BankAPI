package com.dedytech.bankapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "target_saving_account")
data class SavingAccount (
    @Id
    @GeneratedValue
    @JsonIgnore
    var saving_account_id: Long? = null,
    var percent: Float,
    var profit: Float,
    var date: Date,
    var opened: Boolean,

    @OneToOne
    @JoinColumn(name = "target_id")
    var target: Target? = null,

    @OneToMany(
        targetEntity = SavingAccountTransaction::class,
        cascade = [CascadeType.ALL],
        mappedBy = "savingAccount",
        fetch = FetchType.EAGER
    )
    var savingAccountTransactions: MutableList<SavingAccountTransaction> = mutableListOf()
)