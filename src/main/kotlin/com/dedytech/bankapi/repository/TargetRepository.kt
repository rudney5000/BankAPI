package com.dedytech.bankapi.repository

import com.dedytech.bankapi.entity.Target
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TargetRepository: JpaRepository<Target, Long> {
}