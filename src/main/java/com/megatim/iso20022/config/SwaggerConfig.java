package com.megatim.iso20022.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        // Nettoyer les URLs
        String cleanBaseUrl = baseUrl;
        if (cleanBaseUrl.endsWith("/")) {
            cleanBaseUrl = cleanBaseUrl.substring(0, cleanBaseUrl.length() - 1);
        }
        
        // S'assurer que le contextPath commence par /
        String cleanContextPath = contextPath;
        if (!cleanContextPath.startsWith("/")) {
            cleanContextPath = "/" + cleanContextPath;
        }
        if (cleanContextPath.endsWith("/")) {
            cleanContextPath = cleanContextPath.substring(0, cleanContextPath.length() - 1);
        }
        
        // Créer l'URL complète
        String serverUrl = cleanBaseUrl + cleanContextPath;
        
        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Serveur de production");
        
        // Serveur de développement
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Serveur local (développement)");
        
        return new OpenAPI()
                .servers(Arrays.asList(server, devServer));
    }
}
