package org.example.filieres;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI filieresOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Filieres Service API")
                        .version("v1")
                        .description("API CRUD pour la gestion des filieres."));
    }
}
