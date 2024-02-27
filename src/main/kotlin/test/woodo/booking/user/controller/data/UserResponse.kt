package test.woodo.booking.user.controller.data

import java.time.LocalDateTime
import test.woodo.booking.user.domain.User

class UserResponse(
    val name: String,

    val email: String,

    val mobileNumber: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime?,
) {
    constructor(user: User) : this(
        name = user.name,
        email = user.getEmail(),
        mobileNumber = user.getMobileNumber(),
        createdAt = user.createdAt,
        updatedAt = user.updatedAt,
    )
}
