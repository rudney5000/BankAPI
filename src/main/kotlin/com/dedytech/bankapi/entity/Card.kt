package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.CardPaymentSystem
import com.dedytech.bankapi.enums.CardRoundingStep
import com.dedytech.bankapi.enums.Type
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "account_cards")
data class Card(
    @Id
    @GeneratedValue
    var card_id: Long? = null,
    @Enumerated(EnumType.ORDINAL)
    var type: Type,
    @Enumerated(EnumType.ORDINAL)
    var cardPaymentSystem: CardPaymentSystem,
    @Enumerated(EnumType.ORDINAL)
    var cardRoundingStep: CardRoundingStep = CardRoundingStep.STEP10,
    var amount: Float,
    var isActive: Boolean,
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    var account: Account? = null,
    @OneToMany(
        targetEntity = CardTransaction::class,
        mappedBy = "card",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var cardTransaction: MutableList<CardTransaction> = mutableListOf(),
    var cardExpiry: String = " ",
    var encryptedPan: String = " ",
    var cvv: String = " ",
    var cardNumber: String,
    var embossingName: String = " ",
    @CreationTimestamp
    var createdAt: Timestamp?=null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null,
)