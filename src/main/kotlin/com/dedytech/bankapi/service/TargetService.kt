package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.request.TargetReplenishmentRequest
import com.dedytech.bankapi.dto.request.TargetRequest
import com.dedytech.bankapi.dto.request.TargetWithdrawRequest
import com.dedytech.bankapi.dto.response.ResponseDto
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.entity.CardTransaction
import com.dedytech.bankapi.entity.Target
import com.dedytech.bankapi.enums.TargetAllocateMoneyType
import org.springframework.http.ResponseEntity

interface TargetService {

    fun getNameOfTransactionByTargetAllocateMoneyType(
        transaction: CardTransaction,
        targetAllocateMoneyType: TargetAllocateMoneyType
    ): String

    fun allocateMoney(
        account: Account,
        transaction: CardTransaction,
        targetAllocateMoneyType: TargetAllocateMoneyType
    )

    fun operationByCard(
        account: Account,
        cardId: Long,
        name: String,
        category: String,
        amount: Float,
        positive: Boolean
    ): Boolean

    fun replenishment(
        account: Account,
        target: Target,
        targetReplenishmentRequest: TargetReplenishmentRequest,
    ): ResponseEntity<*>

    fun withdraw(
        account: Account,
        target: Target,
        targetWithdrawRequest: TargetWithdrawRequest
    ): ResponseEntity<*>

    fun add(
        account: Account,
        targetRequest: TargetRequest
    ): ResponseEntity<*>

    fun processingSuperPriority(
        account: Account,
        target: Target,
        targetRequest: TargetRequest
    ): Void
}