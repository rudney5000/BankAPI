package com.dedytech.bankapi.dto.request

import com.dedytech.bankapi.entity.Card
import com.dedytech.bankapi.enums.Status

data class ProfileRequest(
    var name: String = "",
    var official_name: String = "",
//    var status: Status,
    var isUseCashback: Boolean,
    var isEvenDistribution: Boolean,
    var isPercentageOnBalance: Boolean,
    var targets: MutableList<Target> = mutableListOf(),
    var cards: MutableList<Card> = mutableListOf(),
    )
