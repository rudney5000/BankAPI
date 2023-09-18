package com.dedytech.bankapi.entity

import com.dedytech.bankapi.enums.TargetPriority
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "account_target")
data class Target(
    @Id
    @GeneratedValue
    var target_id: Long? = null,
    var icon_id: String = " ",
    var name: String = " ",
    var sum: Float,
    var amount: Float,
    @Enumerated(EnumType.ORDINAL)
    var targetPriority: TargetPriority,
    var isCompleted: Boolean,
    var creationDate: Date,

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    var account: Account,
    @OneToOne(
        targetEntity = SavingAccount::class,
        mappedBy = "target",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var savingAccount: SavingAccount? = null,
) {
}