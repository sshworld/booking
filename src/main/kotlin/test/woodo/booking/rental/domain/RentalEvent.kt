package test.woodo.booking.rental.domain

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "rental_event", timeToLive = 10)
class RentalEvent(
    @Id
    val id: Long,
)
