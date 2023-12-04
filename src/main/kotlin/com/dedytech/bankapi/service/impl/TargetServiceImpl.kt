package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.dto.target.TargetDto
import com.dedytech.bankapi.dto.target.request.TargetRequest
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.entity.Target
import com.dedytech.bankapi.repository.AccountRepository
import com.dedytech.bankapi.repository.TargetRepository
import com.dedytech.bankapi.service.TargetService
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp


@Slf4j
@Service
class TargetServiceImpl(
    private val targetRepository: TargetRepository,
    private val accountRepository: AccountRepository
): TargetService {

    override fun addTarget(targetRequest: TargetRequest): ResponseEntity<TargetDto> {
        val existingAccount = accountRepository.findById(targetRequest.accountId).orElse(null)
            ?: return ResponseEntity.ok(null)

        val target = Target(
            id = 0,
            icon_id = targetRequest.icon_id,
            name = targetRequest.name,
            sum = targetRequest.sum,
            amount = targetRequest.amount,
            isCompleted = targetRequest.isCompleted,
            priority = targetRequest.priority,
            created = Timestamp(System.currentTimeMillis()),
            accountId = targetRequest.accountId,
            accounts = existingAccount
        )

        val savedTarget = targetRepository.save(target)
        logger.info("savegarde reussi: {}", savedTarget)

        val targetDto = TargetDto(
            icon_id = savedTarget.icon_id,
            name = savedTarget.name,
            sum = savedTarget.sum,
            amount = savedTarget.amount,
            isCompleted = savedTarget.isCompleted,
            priority = savedTarget.priority,
            accountId = savedTarget.accountId
        )
        return ResponseEntity.ok(targetDto)
    }


    override fun updateTarget(account: Account, target: Target, targetRequest: TargetRequest): ResponseEntity<TargetDto> {
        TODO("Not yet implemented")
    }
}

private val logger = LoggerFactory.getLogger(TargetServiceImpl::class.java)