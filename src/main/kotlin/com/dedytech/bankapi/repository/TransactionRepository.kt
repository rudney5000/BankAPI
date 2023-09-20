package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.CardTransaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<CardTransaction, Long> {
}