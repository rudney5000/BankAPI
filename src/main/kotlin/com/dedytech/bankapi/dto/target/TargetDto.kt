package com.dedytech.bankapi.dto.target

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.enums.TargetPriority

data class TargetDto(
    var icon_id: Long,
    var name: String,
    var sum:Float,
    var amount: Float,
    var isCompleted:Boolean,
    var priority: TargetPriority,
    var accountId: Long,
)
