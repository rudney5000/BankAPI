package com.dedytech.bankapi.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "saving_account_transaction")
data class SavingAccountTransaction(
    @Id
    @GeneratedValue
    @JsonIgnore
    var saving_account_transaction_id: Long? = null,
    var amount: Float,
    var date: Date,
    var name: String = " ",
    var category: String = " ",
    var operationSecurityCode: String = " ",
    @ManyToOne
    @JoinColumn(name = "saving_account_id")
    var savingAccount: SavingAccount? = null
)
