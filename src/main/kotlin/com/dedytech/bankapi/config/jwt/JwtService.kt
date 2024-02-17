package com.dedytech.bankapi.config.jwt

import com.dedytech.bankapi.dto.response.AccountAuth
import com.dedytech.bankapi.utils.AppProperties
import com.dedytech.bankapi.utils.Constant.JWT_TOKEN_EXPIRED
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.SecretKey
@Component
class JwtService(
    appProperties: AppProperties
) {
    private val base64EncodeKey: ByteArray = Base64.getEncoder().encode(appProperties.plainSecretKey.toByteArray())
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(base64EncodeKey)

    fun generateTokenAccessToken(accountAuth: AccountAuth): String {
        return Jwts.builder()
            .setSubject(accountAuth.email)
            .setIssuer("@luthfipun")
            .claim("roles", accountAuth.roles.joinToString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRED))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        }catch (e: JwtException) {
            false
        }
    }
    fun parseClaimsJws(token: String): Claims{
        return Jwts.parserBuilder()
           .setSigningKey(secretKey)
           .build()
           .parseClaimsJws(token)
            .body
    }

}