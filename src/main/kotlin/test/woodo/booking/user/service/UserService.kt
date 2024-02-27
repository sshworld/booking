package test.woodo.booking.user.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import test.woodo.booking.user.controller.data.SignUpRequest
import test.woodo.booking.user.domain.User
import test.woodo.booking.user.domain.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun signUp(request: SignUpRequest): User {
        val user = User(request)

        return userRepository.save(user)
    }
}
