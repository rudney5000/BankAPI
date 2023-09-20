package com.dedytech.bankapi.dto.request

data class TargetReplenishmentRequest(
    var card_id: Long,
    var amount: Float
)
