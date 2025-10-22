package com.megatim.iso20022.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        // Nettoyer l'URL de base pour éviter les doublons
        String cleanBaseUrl = baseUrl;
        if (cleanBaseUrl.endsWith("/")) {
            cleanBaseUrl = cleanBaseUrl.substring(0, cleanBaseUrl.length() - 1);
        }
        
        Server server = new Server();
        server.setUrl(cleanBaseUrl);
        server.setDescription("Serveur de production");
        
        // Ajouter le serveur de développement
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Serveur local (développement)");
        
        return new OpenAPI()
                .servers(Arrays.asList(server, devServer));
    }
}
