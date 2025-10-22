package com.megatim.iso20022.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConsoleLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleLogApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("✅ Console Log API démarrée avec succès!");
        System.out.println("========================================");
        System.out.println("📍 URL de l'API: http://localhost:8080/api/log-console-items");
        System.out.println("📖 Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("📊 Actuator: http://localhost:8080/actuator/health");
        System.out.println("🗄️  Console H2: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
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
