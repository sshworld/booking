package test.woodo.booking.user.domain

import jakarta.persistence.Access
import jakarta.persistence.AccessType.FIELD
import jakarta.persistence.Embeddable
import java.util.regex.Pattern

@Access(FIELD)
@Embeddable
class Email(email: String) {
    val email: String

    init {
        val emailPattern = Pattern.compile(EMAIL_REGEX)
        val emailMatcher = emailPattern.matcher(email)

        check(emailMatcher.matches()) { "이메일 패턴이 맞지 않습니다." }

        this.email = email
    }

    companion object {
        const val EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
    }
}
