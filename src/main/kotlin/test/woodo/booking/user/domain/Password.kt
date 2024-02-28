package test.woodo.booking.user.domain

import jakarta.persistence.Access
import jakarta.persistence.AccessType.FIELD
import jakarta.persistence.Embeddable
import java.util.regex.Pattern

@Access(FIELD)
@Embeddable
class Password(password: String) {
    val password: String

    init {
        val passwordPattern = Pattern.compile(PASSWORD_REGEX)
        val passwordMatcher = passwordPattern.matcher(password)

        check(passwordMatcher.matches()) { "비밀번호 패턴이 맞지 않습니다." }

        this.password = password
    }

    companion object {
        const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
    }
}
