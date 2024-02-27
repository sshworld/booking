package test.woodo.booking.user.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import test.woodo.booking.core.exception.ConflictException
import test.woodo.booking.user.controller.data.SignUpRequest
import test.woodo.booking.user.domain.Email
import test.woodo.booking.user.domain.User
import test.woodo.booking.user.domain.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun signUp(request: SignUpRequest): User {
        val user = User(request)

        checkConflict(request)

        return userRepository.save(user)
    }

    private fun checkConflict(request: SignUpRequest) {
        if (userRepository.findByEmail(Email(request.email)) != null) {
            throw ConflictException("이미 존재하는 유저입니다.")
        }
    }
}
