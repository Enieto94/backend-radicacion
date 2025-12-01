package com.sgdea.ms_radicacion.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://frontend-uat-711792583187.us-east1.run.app",
                        "https://gcpfront.positivasgdea.com",
                        "https://docu.positiva.gov.co",
                        "http://localhost:3000",
                        "http://localhost:4200"
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("X-Filename", "Content-Disposition");
    }
}
