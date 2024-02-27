package test.woodo.booking.user.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import test.woodo.booking.user.domain.fixtures.signUp
import test.woodo.booking.user.domain.fixtures.user

class UserTest : BehaviorSpec({
    Given("유저 정보가 주어졌을 때") {
        val name = "홍길동"

        When("이메일 정보가 올바르지 않으면") {
            val email = "hong.naver.com"
            val mobileNumber = "010-2123-1233"
            val password = "asdf1234"

            val signUp = signUp(
                name = name,
                email = email,
                mobileNumber = mobileNumber,
                password = password,
            )

            Then("IllegalStateException 을 리턴한다.") {
                shouldThrow<IllegalStateException> {
                    user(signUp)
                }
            }
        }

        When("전화번호 정보가 올바르지 않으면") {
            val email = "hong@naver.com"
            val mobileNumber = "01021231233"
            val password = "asdf1234"

            val signUp = signUp(
                name = name,
                email = email,
                mobileNumber = mobileNumber,
                password = password,
            )

            Then("IllegalStateException 을 리턴한다.") {
                shouldThrow<IllegalStateException> {
                    user(signUp)
                }
            }
        }

        When("패스워드 정보가 올바르지 않으면") {
            val email = "hong@naver.com"
            val mobileNumber = "010-2123-1233"
            val password = "1234"

            val signUp = signUp(
                name = name,
                email = email,
                mobileNumber = mobileNumber,
                password = password,
            )

            Then("IllegalStateException 을 리턴한다.") {
                shouldThrow<IllegalStateException> {
                    user(signUp)
                }
            }
        }

        When("모두 규칙에 맞게 입력한다면") {
            val email = "hong@naver.com"
            val mobileNumber = "010-2123-1233"
            val password = "asdf1234"

            val signUp = signUp(
                name = name,
                email = email,
                mobileNumber = mobileNumber,
                password = password,
            )

            Then("유저가 정상적으로 생성된다.") {
                val user = user(signUp)

                user.name shouldBe "홍길동"
                user.getEmail() shouldBe "hong@naver.com"
                user.getMobileNumber() shouldBe "010-2123-1233"
            }
        }
    }
})