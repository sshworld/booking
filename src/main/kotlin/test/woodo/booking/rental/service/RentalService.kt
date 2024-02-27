package test.woodo.booking.rental.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
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
        val user = userRepository.findByIdOrNull(userId) ?: throw NoSuchElementException()

        val rentals = mutableListOf<Rental>().also {
            request.consignmentIds.forEach { id ->
                val consignment = consignmentRepository.findByIdOrNull(id) ?: throw NoSuchElementException()

                it.add(
                    Rental(
                        user = user,
                        consignment = consignment,
                        status = RENTING,
                    )
                )

                consignment.rent()
            }
        }

        rentalRepository.saveAll(rentals).also {
            rentalEvent(it)
        }
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
        val consignment = consignmentRepository.findByIdOrNull(rental.consignment.id) ?: throw NoSuchElementException()

        rental.returnBook()
        consignment.returnBook()
    }
}
