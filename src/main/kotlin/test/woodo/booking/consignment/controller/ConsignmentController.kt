package test.woodo.booking.consignment.controller

import java.security.Principal
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import test.woodo.booking.consignment.controller.data.ConsignmentCreateRequest
import test.woodo.booking.consignment.controller.data.ConsignmentPageRequest
import test.woodo.booking.consignment.controller.data.ConsignmentResponse
import test.woodo.booking.consignment.service.ConsignmentService

@RequestMapping("api/v1/consignments")
@RestController
class ConsignmentController(
    private val consignmentService: ConsignmentService,
) {
    @GetMapping
    @ResponseStatus(OK)
    fun getConsignments(
        consignmentPageRequest: ConsignmentPageRequest,
    ): Page<ConsignmentResponse> {
        return consignmentService.getConsignments(consignmentPageRequest).map(::ConsignmentResponse)
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    fun getConsignment(@PathVariable id: Long): ConsignmentResponse {
        return consignmentService.getConsignment(id).let(::ConsignmentResponse)
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(
        principal: Principal,
        @RequestBody request: ConsignmentCreateRequest,
    ): ConsignmentResponse {
        return consignmentService.create(principal.name.toLong(), request).let(::ConsignmentResponse)
    }
}
