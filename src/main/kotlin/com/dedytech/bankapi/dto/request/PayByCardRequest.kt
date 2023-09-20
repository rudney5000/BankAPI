package com.dedytech.bankapi.dto.request

data class PayByCardRequest(
    var amount: Float,
    var name: String = " ",
    var category: String = " ",
) {
}