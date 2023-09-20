package com.dedytech.bankapi.dto.request

data class TargetWithdrawRequest(
    var card_id: Long,
    var amount: Float
)
