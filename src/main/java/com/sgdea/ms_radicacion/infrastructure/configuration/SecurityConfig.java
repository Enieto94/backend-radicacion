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

    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .cors(Customizer.withDefaults())
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .headers(headers -> headers
                // Usamos el nombre completamente cualificado para evitar problemas de resolución de símbolos
                //.frameOptions(spec -> spec.mode(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec.Mode.SAMEORIGIN))
                .hsts(hstsSpec -> hstsSpec.maxAge(Duration.ofDays(365)).includeSubdomains(true))
                .contentSecurityPolicy(cspSpec -> cspSpec.policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data: https:; font-src 'self' data:; connect-src 'self'; frame-ancestors 'self'; report-uri /api/v1/csp-report;"))
            )
            .authorizeExchange(exchange -> exchange
                .pathMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
