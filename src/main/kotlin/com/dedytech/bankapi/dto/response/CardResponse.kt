package com.dedytech.bankapi.dto.response

import com.dedytech.bankapi.enums.CardRoundingStep

data class CardResponse(
    var active: Boolean,
    var roundingStep: CardRoundingStep
)
