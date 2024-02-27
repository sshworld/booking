package test.woodo.booking.user.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import test.woodo.booking.user.controller.data.SignUpRequest
import test.woodo.booking.user.controller.data.UserResponse
import test.woodo.booking.user.service.UserService

@RequestMapping("api/v1/users")
@RestController
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(@RequestBody request: SignUpRequest): UserResponse {
        return userService.signUp(request).let(::UserResponse)
    }
}
