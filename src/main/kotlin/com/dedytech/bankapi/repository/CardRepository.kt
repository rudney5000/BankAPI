package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, Long> {
}