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
@Table(name = "card_transactions")
data class CardTransaction(
    @Id
    @GeneratedValue
    @JsonIgnore
    var transaction_id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    var card: Card? = null,
    var name: String = " ",
    var category: String = " ",
    var amount: Float,
    var date: Date,
    var cashback: Float,
    var roundingAmount: Float,
    var percentageOnBalance: Float,
    var operationSecurityCode: String = " ",

)
