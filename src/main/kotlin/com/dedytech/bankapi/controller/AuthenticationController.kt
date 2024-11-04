package com.dedytech.bankapi.controller

import com.dedytech.bankapi.dto.request.auth.AuthenticationRequest
import com.dedytech.bankapi.dto.request.auth.RegisterRequest
import com.dedytech.bankapi.dto.response.auth.AuthenticationResponse
import com.dedytech.bankapi.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest ): ResponseEntity<AuthenticationResponse> {
        val response = authenticationService.register(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/access-token")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        val response = authenticationService.authenticate(request)
        return ResponseEntity.ok(response)
    }
}