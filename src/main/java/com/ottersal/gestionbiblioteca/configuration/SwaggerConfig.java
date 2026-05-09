package com.ottersal.gestionbiblioteca.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Biblioteca ITM")
                        .version("1.0")
                        .description("Documentación de las rutas para Préstamos, Libros y Usuarios"));
    }
}