package com.example.librarymanagermentservice.configrations;

import com.example.librarymanagermentservice.common.Constant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Information about open apu config.
 */
@Configuration
public class OpenApiConfig {
    /**
     * Custom open api.
     * @return OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = Constant.BEARER;
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(Constant.BEARER)
                                .bearerFormat(Constant.JWT)));
    }
}
