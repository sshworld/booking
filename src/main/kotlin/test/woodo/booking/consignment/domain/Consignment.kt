package test.woodo.booking.consignment.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import test.woodo.booking.consignment.controller.data.ConsignmentCreateRequest
import test.woodo.booking.consignment.domain.Status.RENTING
import test.woodo.booking.consignment.domain.Status.RETURN
import test.woodo.booking.rental.domain.Rental
import test.woodo.booking.user.domain.User

@DynamicUpdate
@Entity(name = "consignment")
class Consignment(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val userId: Long,

    val userName: String,

    val bookName: String,

    val internationalStandardBookNumber: String,

    val rentalPrice: Int,

    @OneToMany(mappedBy = "consignmentId", fetch = LAZY)
    val rentals: List<Rental> = listOf(),

    @Enumerated(STRING)
    var status: Status,

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,
) {
    constructor(user: User, request: ConsignmentCreateRequest) : this(
        userId = user.id,
        userName = user.name,
        bookName = request.bookName,
        internationalStandardBookNumber = request.internationalStandardBookNumber,
        rentalPrice = request.rentalPrice,
        status = RETURN,
    )

    fun rent() {
        status = RENTING
    }

    fun returnBook() {
        status = RETURN
    }

    fun canRent() = status.canRent()
}
