package com.dedytech.bankapi.service

import com.dedytech.bankapi.dto.auth.request.RegisterRequest
import com.dedytech.bankapi.dto.target.TargetDto
import com.dedytech.bankapi.dto.target.request.TargetRequest
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.entity.Target
import org.springframework.http.ResponseEntity

interface TargetService {

    fun addTarget(targetRequest: TargetRequest): ResponseEntity<TargetDto>

    fun updateTarget(account: Account, target: Target, targetRequest: TargetRequest): ResponseEntity<TargetDto>
}