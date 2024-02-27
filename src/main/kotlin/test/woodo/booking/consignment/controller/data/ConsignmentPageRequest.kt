package test.woodo.booking.consignment.controller.data

import test.woodo.booking.consignment.domain.SortMethod
import test.woodo.booking.consignment.domain.Status

class ConsignmentPageRequest(
    val limit: Int,

    val offset: Int,

    val sortMethod: SortMethod,

    val status: Status?,
)
