package com.dedytech.bankapi.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class DemoController {

    @GetMapping
    fun sayHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello from secured endpoint")
    }
}