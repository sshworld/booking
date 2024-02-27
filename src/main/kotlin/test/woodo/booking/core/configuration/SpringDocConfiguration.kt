package test.woodo.booking.core.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "authorization",
    type = HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
)
@OpenAPIDefinition(security = [SecurityRequirement(name = "authorization")])
class SpringDocConfiguration
