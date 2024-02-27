package test.woodo.booking.core.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {
    @Bean
    fun springDocApi(): OpenAPI = OpenAPI()
        .components(
            Components()
                .addSecuritySchemes(
                    "Authorization", SecurityScheme()
                        .name("authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .addSecurityItem(SecurityRequirement().addList("authorization"))
}
