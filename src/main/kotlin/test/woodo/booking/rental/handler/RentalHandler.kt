package test.woodo.booking.rental.handler

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import test.woodo.booking.rental.service.RentalService

@Component
class RentalHandler(
    private val rentalService: RentalService,
) : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val rentalId = message.toString().split(":")[1].toLong()

        rentalService.returnBook(rentalId)
    }
}
