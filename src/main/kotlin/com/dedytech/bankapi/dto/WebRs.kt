package com.dedytech.bankapi.dto

import org.springframework.http.HttpStatus

data class WebRs<T> (
    val code: Int = HttpStatus.OK.value(),
    val message: String = HttpStatus.OK.name,
    val data: T? = null
)