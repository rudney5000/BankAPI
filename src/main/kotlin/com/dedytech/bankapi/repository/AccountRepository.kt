package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface AccountRepository: JpaRepository<Account, Long> {

    fun findByEmail(email: String): Optional<Account>
    fun findByResetToken(resetToken: String): Optional<Account>
}