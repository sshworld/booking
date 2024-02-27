package test.woodo.booking.auth.controller.data

import test.woodo.booking.auth.jwt.Token


class TokenResponse(
    val accessToken: String,

    val refreshToken: String,
) {
    constructor(token: Token) : this(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken,
    )
}
