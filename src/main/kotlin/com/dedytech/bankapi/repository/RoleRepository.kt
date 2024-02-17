package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>
}