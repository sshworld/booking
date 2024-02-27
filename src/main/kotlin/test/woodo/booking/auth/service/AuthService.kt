package test.woodo.booking.auth.service

import org.springframework.stereotype.Service
import test.woodo.booking.auth.configuration.JwtConfiguration
import test.woodo.booking.auth.controller.data.CreateTokenRequest
import test.woodo.booking.auth.controller.data.RefreshTokenRequest
import test.woodo.booking.auth.jwt.Claims
import test.woodo.booking.auth.jwt.Role
import test.woodo.booking.auth.jwt.Token
import test.woodo.booking.user.domain.Email
import test.woodo.booking.user.domain.Password
import test.woodo.booking.user.domain.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,

    private val jwtConfiguration: JwtConfiguration,
) {
    fun login(request: CreateTokenRequest): Token {
        val user = userRepository.findByEmailAndPassword(
            email = Email(request.email),
            password = Password(request.password),
        ) ?: throw NoSuchElementException()

        return Claims(
            userId = user.id,
            role = Role.USER.toString(),
        ).let(jwtConfiguration::createToken)
    }

    fun refreshToken(request: RefreshTokenRequest) = jwtConfiguration.refreshToken(request.refreshToken)
}
