package com.bilyoner.livebettingapp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Live Betting API")
                        .version("1.0")
                        .description("Live Betting API documentation")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
