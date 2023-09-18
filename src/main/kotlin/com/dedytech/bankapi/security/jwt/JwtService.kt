package com.dedytech.bankapi.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService {

    private val SECRET_KEY = "4E645267556B58703273357638792F423F4528482B4D6251655368566D597133"

    fun extractUsername(token: String): String {
        return extractClaims(token, Claims::getSubject)
    }

    fun <T> extractClaims(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

//    fun <T> extractClaims(token: String, claimsResolver: Function<Claims, T>): T {
//        val claims = extractAllClaims(token)
//        return claimsResolver.apply(claims)
//    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(mapOf(), userDetails)
    }

    fun generateToken(extractClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .setClaims(extractClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaims(token, Claims::getExpiration)
    }


    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}