package test.woodo.booking.consignment.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import test.woodo.booking.consignment.domain.ConsignmentRepository
import test.woodo.booking.consignment.service.fixtures.consignmentCreateRequest
import test.woodo.booking.user.domain.UserRepository

@SpringBootTest
class ConsignmentServiceTest(
    private val consignmentRepository: ConsignmentRepository,

    private val userRepository: UserRepository,
) : BehaviorSpec({
    Given("인증된 사용자가 로그인된 상황에서") {
        val consignmentService = ConsignmentService(
            consignmentRepository,
            userRepository,
        )

        val userId = 1L

        When("위탁 도서의 정보를 입력하고 등록 버튼을 누르면") {
            val request = consignmentCreateRequest(
                bookName = "로봇다리 세진이",
                internationalStandardBookNumber = "9788993499070",
                rentalPrice = 1300,
            )

            val result = consignmentService.create(userId, request)

            Then("새로운 위탁 도서가 생성된다.") {
                result.bookName shouldBe "로봇다리 세진이"
                result.internationalStandardBookNumber shouldBe "9788993499070"
                result.rentalPrice shouldBe 1300
            }
        }
    }
})
