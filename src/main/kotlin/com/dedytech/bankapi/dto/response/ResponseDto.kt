package com.dedytech.bankapi.dto.response

data class ResponseDto <T>(

    val success: Boolean = true,
    val code: Int = 200,
    val message: String = "OK",
    val error: ResponseError? = null,
    val data: T? = null
)