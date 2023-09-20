package com.dedytech.bankapi.dto.response

import com.dedytech.bankapi.utils.CustomPage

data class PageableResponse<T>(
    val pageable: CustomPage.CustomPageable? = null,
    val data: List<T>? = null
)