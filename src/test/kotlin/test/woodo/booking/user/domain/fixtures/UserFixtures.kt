package test.woodo.booking.user.domain.fixtures

import test.woodo.booking.user.controller.data.SignUpRequest
import test.woodo.booking.user.domain.User

fun signUp(
    name: String,
    email: String,
    mobileNumber: String,
    password: String,
): SignUpRequest {
    return SignUpRequest(
        name = name,
        email = email,
        mobileNumber = mobileNumber,
        password = password,
    )
}

fun user(signUpRequest: SignUpRequest): User {
    return User(signUpRequest)
}
