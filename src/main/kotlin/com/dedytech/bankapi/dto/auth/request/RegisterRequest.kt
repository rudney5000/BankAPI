package com.dedytech.bankapi.dto.auth.request

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.entity.Target
import com.dedytech.bankapi.enums.*
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class RegisterRequest(
    var name: String,
    var email: String,
    var password: String,
    var surname: String,
    var usedService: Boolean,
    var isPercentageOnBalance: Boolean,
    var isUseCashBack: Boolean,
    var isEvenDistribution: Boolean,
    var targets: Set<Target>,
//    var superPriorityTarget_id: Long,


    @Enumerated(EnumType.STRING)
    var role: Role,
)

fun RegisterRequest.toAccountEntity():Account = Account(
    name = this.name,
    email = this.email,
    password = this.password,
    surname = this.surname,
    usedService = this.usedService,
    isPercentageOnBalance = this.isPercentageOnBalance,
    isUseCashBack = this.isUseCashBack,
    isEvenDistribution = this.isEvenDistribution,
    targets = this.targets,
    role = Role.USER
)