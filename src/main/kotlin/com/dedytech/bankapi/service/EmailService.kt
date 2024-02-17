package com.dedytech.bankapi.service

import com.dedytech.bankapi.entity.Account

interface EmailService {
    fun sendRegistrationConfirmation(account: Account, appUrl: String)
}