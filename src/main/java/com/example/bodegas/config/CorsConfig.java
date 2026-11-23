package com.example.bodegas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Permite que el frontend (en su servidor de desarrollo) llame a la API
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/bodegas")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:4200",
                        "http://127.0.0.1:4200",
                        "http://localhost:5173"
                )
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*");
    }
}
