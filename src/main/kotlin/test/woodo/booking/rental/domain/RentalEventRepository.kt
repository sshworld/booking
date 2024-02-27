package test.woodo.booking.rental.domain

import org.springframework.data.repository.CrudRepository

interface RentalEventRepository : CrudRepository<RentalEvent, Long>
