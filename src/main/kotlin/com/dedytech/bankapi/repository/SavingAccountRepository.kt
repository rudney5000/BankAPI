package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.SavingAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SavingAccountRepository: JpaRepository<SavingAccount, Long> {
}