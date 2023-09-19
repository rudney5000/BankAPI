package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.dto.request.ProfileRequest
import com.dedytech.bankapi.dto.response.MessageResponse
import com.dedytech.bankapi.dto.response.ProfileResponse
import com.dedytech.bankapi.dto.response.ResponseDto
import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.repository.AccountRepository
import com.dedytech.bankapi.service.ProfileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfileServiceImpl(
//    @Value("12")
//    private var pageSize: Int = 0,
    private val accountRepository: AccountRepository
): ProfileService {
    override fun get(
        account: Account
    ): ResponseDto<ProfileResponse> {
        val profileResponse = ProfileResponse(
            account_id = account.account_id,
            name = account.name,
            email = account.email,
            superPriorityTarget = account.superPriorityTarget_id ?: 0L,
            isUseCashback = account.isUseCashback,
            isEvenDistribution = account.isEvenDistribution,
            isPercentageOnBalance = account.isPercentageOnBalance
        )
        return ResponseDto(
            code = 200,
            message = "OK",
            data = profileResponse
        )
    }

    override fun update(
        account: Account,
        profileRequest: ProfileRequest
    ): ResponseEntity<MessageResponse> {

        var needToUpdate = false

        if (account.name != profileRequest.name) {
            account.name = profileRequest.name
            needToUpdate = true
        }

        if (account.official_name != profileRequest.official_name) {
            account.official_name = profileRequest.official_name
            needToUpdate = true
        }

        if (account.isUseCashback != profileRequest.isUseCashBack) {
            account.isUseCashback = profileRequest.isUseCashBack
            needToUpdate = true
        }

        if (account.isEvenDistribution != profileRequest.isEvenDistribution) {
            account.isEvenDistribution = profileRequest.isEvenDistribution
            needToUpdate = true
        }

        if (account.isPercentageOnBalance != profileRequest.isPercentageOnBalance) {
            account.isPercentageOnBalance = profileRequest.isPercentageOnBalance
            needToUpdate = true
        }

        if (needToUpdate) {
            accountRepository.save(account)
            return ResponseEntity.ok(MessageResponse("Profile updated!"))
        }

        return ResponseEntity.ok(MessageResponse("Nothing to update!"))
    }
}