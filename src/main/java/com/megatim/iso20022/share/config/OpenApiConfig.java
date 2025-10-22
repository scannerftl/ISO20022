package com.megatim.iso20022.share.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI consoleLogOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Serveur local");

        Contact contact = new Contact();
        contact.setName("Équipe ISO20022");
        contact.setEmail("support@megatim.com");

        License license = new License()
                .name("Propriétaire")
                .url("https://www.megatim.com");

        Info info = new Info()
                .title("API Console Log ISO20022")
                .version("1.0.0")
                .description("API REST pour la gestion des logs de console ISO20022. " +
                        "Cette API permet de créer, lire, mettre à jour et supprimer des logs de messages " +
                        "avec toutes leurs relations (détails, identifications, informations complémentaires).")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(Arrays.asList(localServer));
    }
}
