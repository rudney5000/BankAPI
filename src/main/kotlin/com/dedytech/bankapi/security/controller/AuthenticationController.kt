package com.dedytech.bankapi.security.controller

import com.dedytech.bankapi.security.auth.AuthenticationRequest
import com.dedytech.bankapi.security.auth.AuthenticationResponse
import com.dedytech.bankapi.security.auth.RegisterRequest
import com.dedytech.bankapi.security.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private var authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthenticationResponse> {
        val response = authenticationService.register(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        val response = authenticationService.authenticate(request)
        return ResponseEntity.ok(response)
    }
}