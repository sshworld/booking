package test.woodo.booking.rental.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import test.woodo.booking.consignment.domain.Consignment
import test.woodo.booking.consignment.domain.ConsignmentRepository
import test.woodo.booking.rental.controller.data.RentalRequest
import test.woodo.booking.rental.domain.Rental
import test.woodo.booking.rental.domain.RentalEvent
import test.woodo.booking.rental.domain.RentalEventRepository
import test.woodo.booking.rental.domain.RentalRepository
import test.woodo.booking.rental.domain.Status.RENTING
import test.woodo.booking.user.domain.UserRepository

@Service
class RentalService(
    private val userRepository: UserRepository,

    private val consignmentRepository: ConsignmentRepository,

    private val rentalRepository: RentalRepository,

    private val rentalEventRepository: RentalEventRepository,
) {
    @Transactional
    fun rental(userId: Long, request: RentalRequest) {
        val rentals = mutableListOf<Rental>().also {
            request.consignmentIds.forEach { consignmentId ->
                val consignment = consignmentRepository.findByIdOrNull(consignmentId)
                    ?: throw NoSuchElementException("해당 위탁 건이 존재하지 않습니다.")

                checkCanRent(consignment, userId)
                it.add(Rental(userId = userId, consignmentId = consignmentId, status = RENTING))

                consignment.rent()
            }
        }

        rentalRepository.saveAll(rentals).also {
            rentalEvent(it)
        }
    }

    private fun checkCanRent(consignment: Consignment, userId: Long) {
        check(consignment.canRent()) { "현재 대여중인 도서입니다." }

        check(consignment.userId != userId) { "본인의 책은 대여할 수 없습니다." }
    }

    private fun rentalEvent(rentals: List<Rental>) {
        rentals.forEach {
            val rentalEvent = RentalEvent(it.id)

            rentalEventRepository.save(rentalEvent)
        }
    }

    @Transactional
    fun returnBook(rentalId: Long) {
        val rental = rentalRepository.findByIdOrNull(rentalId) ?: throw NoSuchElementException()
        val consignment = consignmentRepository.findByIdOrNull(rental.consignmentId) ?: throw NoSuchElementException()

        rental.returnBook()
        consignment.returnBook()
    }
}
