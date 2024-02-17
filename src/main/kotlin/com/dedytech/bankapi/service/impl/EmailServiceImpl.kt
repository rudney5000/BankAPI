package com.dedytech.bankapi.service.impl

import com.dedytech.bankapi.entity.Account
import com.dedytech.bankapi.service.EmailService
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    val javaMailSender: JavaMailSender
): EmailService {
    override fun sendRegistrationConfirmation(account: Account, appUrl: String) {
        val mailMessage = SimpleMailMessage()
        mailMessage.setTo(account.email)
        mailMessage.subject="Reinitialisation de mot de passe "
        mailMessage.text = "Veuillez cliquer sur le lien pour réinitialiser  votre mot de passe : $appUrl"
        javaMailSender.send(mailMessage)
    }
}