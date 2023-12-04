package com.dedytech.bankapi.controller

import com.dedytech.bankapi.dto.target.TargetDto
import com.dedytech.bankapi.dto.target.request.TargetRequest
import com.dedytech.bankapi.service.TargetService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/targets")
class TargetController(
    private val targetService: TargetService
) {
    @PostMapping
    fun addTarget(@Valid targetRequest: TargetRequest): ResponseEntity<TargetDto>{
        return targetService.addTarget(targetRequest)
    }
}