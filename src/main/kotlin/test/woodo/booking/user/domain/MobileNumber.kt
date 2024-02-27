package test.woodo.booking.user.domain

import jakarta.persistence.Access
import jakarta.persistence.AccessType.FIELD
import jakarta.persistence.Embeddable
import java.util.regex.Pattern

@Access(FIELD)
@Embeddable
class MobileNumber(mobileNumber: String) {
    val mobileNumber: String

    init {
        val mobileNumberPattern = Pattern.compile(MOBILE_NUMBER_REGEX)
        val mobileNumberMatcher = mobileNumberPattern.matcher(mobileNumber)

        check(mobileNumberMatcher.matches()) { "전화번호 패턴이 맞지 않습니다." }

        this.mobileNumber = mobileNumber
    }

    companion object {
        const val MOBILE_NUMBER_REGEX = "^01(?:0|1)-(\\d{4})-(\\d{4})"
    }
}
