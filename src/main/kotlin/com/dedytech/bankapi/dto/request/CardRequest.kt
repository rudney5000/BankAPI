package com.dedytech.bankapi.dto.request

import com.dedytech.bankapi.enums.CardPaymentSystem
import com.dedytech.bankapi.enums.Type

data class CardRequest(
    var type: Type,
    var cardPaymentSystem: CardPaymentSystem,
)