package com.dedytech.bankapi.controller

import com.dedytech.bankapi.dto.target.TargetDto
import com.dedytech.bankapi.dto.target.request.TargetRequest
import com.dedytech.bankapi.service.TargetService
import com.dedytech.bankapi.service.impl.TargetServiceImpl
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/targets")
class TargetController(
    private val targetService: TargetService
) {
    @PostMapping
    fun addTarget(@Valid @RequestBody targetRequest: TargetRequest): ResponseEntity<TargetDto>{
        logger.info("Target ajouter: {} ", targetRequest)
        return targetService.addTarget(targetRequest)
    }
}

private val logger = LoggerFactory.getLogger(TargetController::class.java)