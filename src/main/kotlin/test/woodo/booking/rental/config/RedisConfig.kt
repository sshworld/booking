package test.woodo.booking.rental.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import test.woodo.booking.rental.handler.RentalHandler


@Configuration
class RedisConfig {
    @Bean
    fun redisMessageListenerContainer(
        redisConnectionFactory: RedisConnectionFactory,
        rentalHandler: RentalHandler,
    ): RedisMessageListenerContainer {
        val redisMessageListenerContainer = RedisMessageListenerContainer()

        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory)
        redisMessageListenerContainer.addMessageListener(rentalHandler, PatternTopic(EXPIRED_EVENT_PATTERN))

        return redisMessageListenerContainer
    }

    companion object {
        private const val EXPIRED_EVENT_PATTERN = "__keyevent@*__:expired"
    }
}
