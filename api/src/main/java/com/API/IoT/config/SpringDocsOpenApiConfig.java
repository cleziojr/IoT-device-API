package com.API.IoT.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                            .title("Rest Api - Para coleta de dados da agricultura")
                            .description("Api para o armazenamento de dados referente aos sistema de controle de horta hidropônica por meio de seus sensores")
                            .version("v1")
                            .license(new License().name("MIT").url("https://mit-license.org/"))
                );
    }
}
