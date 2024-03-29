package test.woodo.booking.rental.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import test.woodo.booking.rental.domain.Status.RETURN

@DynamicUpdate
@Entity(name = "rental")
class Rental(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val userId: Long,

    val consignmentId: Long,

    @Enumerated(STRING)
    var status: Status,

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,
) {
    fun returnBook() {
        status = RETURN
    }
}
