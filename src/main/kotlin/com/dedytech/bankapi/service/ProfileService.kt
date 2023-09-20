package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.request.ProfileRequest
import com.dedytech.bankapi.dto.response.MessageResponse
import com.dedytech.bankapi.dto.response.PageableResponse
import com.dedytech.bankapi.dto.response.ProfileResponse
import com.dedytech.bankapi.dto.response.ResponseDto
import com.dedytech.bankapi.entity.Account
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import java.util.*

interface ProfileService {

    fun get(account: Account): ResponseDto<ProfileResponse>

    fun update(account: Account, profileRequest: ProfileRequest): ResponseEntity<MessageResponse>
}