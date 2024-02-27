package test.woodo.booking.auth.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.woodo.booking.auth.controller.data.CreateTokenRequest
import test.woodo.booking.auth.controller.data.RefreshTokenRequest
import test.woodo.booking.auth.controller.data.TokenResponse
import test.woodo.booking.auth.service.AuthService

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("login")
    fun login(@RequestBody request: CreateTokenRequest) = authService.login(request).let(::TokenResponse)

    @PostMapping("refresh")
    fun refresh(@RequestBody request: RefreshTokenRequest) = authService.refreshToken(request).let(::TokenResponse)
}
