package test.woodo.booking.user.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import test.woodo.booking.user.controller.data.SignUpRequest

@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val name: String,

    @Embedded
    val email: Email,

    @Embedded
    val mobileNumber: MobileNumber,

    @Embedded
    val password: Password,

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,
) {
    constructor(signUpRequest: SignUpRequest) : this(
        name = signUpRequest.name,
        email = Email(signUpRequest.email),
        mobileNumber = MobileNumber(signUpRequest.mobileNumber),
        password = Password(signUpRequest.password),
    )
}
