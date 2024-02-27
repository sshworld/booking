package test.woodo.booking.consignment.service.fixtures

import test.woodo.booking.consignment.controller.data.ConsignmentCreateRequest

fun consignmentCreateRequest(
    bookName: String,
    internationalStandardBookNumber: String,
    rentalPrice: Int,
): ConsignmentCreateRequest {
    return ConsignmentCreateRequest(
        bookName = bookName,
        internationalStandardBookNumber = internationalStandardBookNumber,
        rentalPrice = rentalPrice,
    )
}
