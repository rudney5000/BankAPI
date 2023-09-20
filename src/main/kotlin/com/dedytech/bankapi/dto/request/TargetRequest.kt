package com.dedytech.bankapi.dto.request

import com.dedytech.bankapi.enums.TargetPriority

data class TargetRequest(
    var icon_id: String = " ",
    var name: String = " ",
    var amount: Float,
    var targetPriority: TargetPriority = TargetPriority.HIGH,
    var isSuperPriority: Boolean = false
)
