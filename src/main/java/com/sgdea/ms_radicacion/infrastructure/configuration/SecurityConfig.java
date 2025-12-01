package com.sgdea.ms_radicacion.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import java.time.Duration;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
            // Endpoint de login/token
            "/api/v1/tokens/all-platforms",
            // Endpoints de documentación (Swagger)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/swagger-ui/**",
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .cors(Customizer.withDefaults())
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .headers(headers -> headers
                //.frameOptions(spec -> spec.mode(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec.Mode.SAMEORIGIN)) // Descomentado
                .hsts(hstsSpec -> hstsSpec.maxAge(Duration.ofDays(365)).includeSubdomains(true))
                .contentSecurityPolicy(cspSpec -> cspSpec.policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data: https:; font-src 'self' data:; connect-src 'self'; frame-ancestors 'self'; report-uri /api/v1/csp-report;"))
            )
            .authorizeExchange(exchange -> exchange
                // Se permite el acceso a los endpoints públicos
                .pathMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
