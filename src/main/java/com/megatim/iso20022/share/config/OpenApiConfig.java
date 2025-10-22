package com.megatim.iso20022.share.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${app.base-url}")
    private String baseUrl;

    @Bean
    public OpenAPI consoleLogOpenAPI() {
        Server server = new Server();
        server.setUrl(baseUrl);
        server.setDescription("Serveur de production");

        Contact contact = new Contact();
        contact.setName("Équipe ISO20022");
        contact.setEmail("leonelfofou98@gmail.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("API Console Log ISO20022")
                .version("1.0.0")
                .description("API REST pour la gestion des logs de console ISO20022. " +
                        "Cette API permet de créer, lire, mettre à jour et supprimer des logs de messages " +
                        "avec toutes leurs relations (détails, identifications, informations complémentaires).")
                .contact(contact)
                .license(license);

        // Si vous souhaitez conserver le serveur local en développement
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Serveur local (développement)");

        return new OpenAPI()
                .info(info)
                .servers(Arrays.asList(server, localServer));
    }
}
