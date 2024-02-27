package test.woodo.booking.consignment.controller.data

import java.time.LocalDateTime
import test.woodo.booking.consignment.domain.Consignment

class ConsignmentResponse(
    val id: Long,

    val bookName: String,

    val internationalStandardBookNumber: String,

    val rentalPrice: Int,

    val userName: String,

    val createdAt: LocalDateTime,
) {
    constructor(consignment: Consignment) : this(
        id = consignment.id,
        bookName = consignment.bookName,
        internationalStandardBookNumber = consignment.internationalStandardBookNumber,
        rentalPrice = consignment.rentalPrice,
        userName = consignment.userName,
        createdAt = consignment.createdAt,
    )
}
