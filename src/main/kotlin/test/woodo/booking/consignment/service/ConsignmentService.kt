package test.woodo.booking.consignment.service

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import test.woodo.booking.consignment.controller.data.ConsignmentCreateRequest
import test.woodo.booking.consignment.controller.data.ConsignmentPageRequest
import test.woodo.booking.consignment.domain.Consignment
import test.woodo.booking.consignment.domain.ConsignmentRepository
import test.woodo.booking.consignment.domain.ConsignmentSpecification
import test.woodo.booking.user.domain.UserRepository

@Service
class ConsignmentService(
    private val consignmentRepository: ConsignmentRepository,

    private val userRepository: UserRepository,
) {
    fun getConsignments(consignmentPageRequest: ConsignmentPageRequest): Page<Consignment> {
        return consignmentRepository.findAll(
            ConsignmentSpecification.of(consignmentPageRequest.status),
            PageRequest.of(
                consignmentPageRequest.offset,
                consignmentPageRequest.limit,
                consignmentPageRequest.sortMethod.sort,
            ),
        )
    }

    fun getConsignment(id: Long): Consignment {
        return consignmentRepository.findByIdOrNull(id) ?: throw NoSuchElementException("해당 위탁 건이 존재하지 않습니다.")
    }

    @Transactional
    fun create(userId: Long, request: ConsignmentCreateRequest): Consignment {
        val user = userRepository.findByIdOrNull(userId) ?: throw NoSuchElementException()
        val consignment = Consignment(user, request)

        return consignmentRepository.save(consignment)
    }
}
