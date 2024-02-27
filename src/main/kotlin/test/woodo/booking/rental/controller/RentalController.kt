package test.woodo.booking.rental.controller

import org.springframework.http.HttpStatus.CREATED
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import test.woodo.booking.rental.controller.data.RentalRequest
import test.woodo.booking.rental.controller.data.RentalResponse
import test.woodo.booking.rental.service.RentalService

@RequestMapping("api/v1/rentals")
@RestController
class RentalController(
    private val rentalService: RentalService,
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun rental(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody request: RentalRequest,
    ) = rentalService.rental(userDetails.username.toLong(), request)
}
