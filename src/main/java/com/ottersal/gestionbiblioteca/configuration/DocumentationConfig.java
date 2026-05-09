package com.ottersal.gestionbiblioteca.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("API de Gestión de Biblioteca")
                                .version("1.0.0")
                                .description("API REST para la gestión de usuarios, roles, permisos y reservas de una biblioteca")
                                .contact(new Contact()
                                        .name("Salomé Quiceno | Miguel Ángel Rojo | Rolfy Palomino")
                                        .email("salomequiceno1127173@correo.itm.edu.co"))

                );
    }
}
