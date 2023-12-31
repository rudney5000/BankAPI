package com.dedytech.bankapi.dto.response

import com.dedytech.bankapi.enums.Status

data class ProfileResponse(
    var account_id: Long? = null,
    var name: String = "",
    var email: String = "",
//    var status: Status,
    var superPriorityTarget: Long,
    var isUseCashback: Boolean,
    var isEvenDistribution: Boolean,
    var isPercentageOnBalance: Boolean,
)
