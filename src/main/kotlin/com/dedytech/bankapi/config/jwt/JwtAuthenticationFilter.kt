package com.dedytech.bankapi.config.jwt

import com.dedytech.bankapi.dto.response.AccountAuth
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.Servlet
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import kotlin.jvm.Throws

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!hasAuthorizationBearer(request)){
            filterChain.doFilter(request, response)
            return
        }

        val token: String = getAccessToken(request)

        if (!jwtService.isTokenValid(token = token)){
            filterChain.doFilter(request, response)
            return
        }
        setAuthenticationContext(token, request)
        filterChain.doFilter(request, response)
    }

    private fun setAuthenticationContext(token: String, request: HttpServletRequest) {
        val userDetails: UserDetails = getUserDetails(token)
    }

    private fun getUserDetails(token: String): UserDetails {
        val claims = jwtService.parseClaimsJws(token = token)
        val subject = claims[Claims.SUBJECT] as String

        val textRoles = claims["roles"] as String
        val roles = textRoles.split(",")

        return AccountAuth(email = subject, roles = roles)
    }

    private fun getAccessToken(request: HttpServletRequest): String {
        val header = request.getHeader("Authorization")
        return header.split(" ")[1].trim()
    }

    private fun hasAuthorizationBearer(request: HttpServletRequest): Boolean {
        val header: String? = request.getHeader("Authorization")
        if (header == null || ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false
        }
        return true
    }
}