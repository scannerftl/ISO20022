package com.megatim.iso20022.share;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ConsoleLogApplication {

    @Value("${app.base-url}")
    private static String baseUrl;
    @Value("${server.servlet.context-path:}")
    private static String contextPath;
    @Value("${app.swagger-ui}")
    private static String swaggerUiPath;
    @Value("${app.actuator}")
    private static String actuatorPath;
    @Value("${app.h2-console}")
    private static String h2ConsolePath;
    @Value("${app.api-docs}")
    private static String apiDocsPath;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsoleLogApplication.class, args);
        
        // Récupérer les propriétés du contexte
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port", "8080");
        String host = env.getProperty("server.address", "localhost");
        
        // Construire l'URL de base
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        
        String fullBaseUrl = env.getProperty("app.base-url", 
            String.format("%s://%s:%s", protocol, host, port));
            
        // Afficher les informations de démarrage
        String separator = String.join("", Collections.nCopies(40, "="));
        System.out.println("\n" + separator);
        System.out.println("✅ Console Log API démarrée avec succès!");
        System.out.println(separator);
        System.out.println(String.format("🌍 Environnement: %s", 
            String.join(", ", env.getActiveProfiles().length == 0 ? 
                new String[]{"default"} : env.getActiveProfiles())));
        System.out.println(String.format("📍 URL de l'API: %s/api/log-console-items", fullBaseUrl));
        System.out.println(String.format("📖 Swagger UI: %s%s", fullBaseUrl, 
            env.getProperty("app.swagger-ui", "/swagger-ui.html")));
        System.out.println(String.format("📚 API Docs: %s%s", fullBaseUrl, 
            env.getProperty("app.api-docs", "/v3/api-docs")));
        System.out.println(String.format("📊 Actuator: %s%s/health", fullBaseUrl, 
            env.getProperty("app.actuator", "/actuator")));
            
        // Afficher la console H2 uniquement si H2 est activé
        if (Arrays.stream(env.getActiveProfiles()).anyMatch(profile -> profile.equalsIgnoreCase("dev"))) {
            System.out.println(String.format("🗄️  Console H2: %s%s", fullBaseUrl, 
                env.getProperty("app.h2-console", "/h2-console")));
        }
        
        System.out.println(separator + "\n");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}
