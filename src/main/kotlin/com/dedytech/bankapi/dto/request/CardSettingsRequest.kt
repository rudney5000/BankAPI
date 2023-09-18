package com.dedytech.bankapi.dto.request

import com.dedytech.bankapi.enums.CardRoundingStep

data class CardSettingsRequest(
    var active: Boolean,
    var roundingStep: CardRoundingStep
)
