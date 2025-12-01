package com.sgdea.ms_radicacion.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Map;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {

    private final WebClient webClient;

    public TokenController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://ms-sgdea-uat-711792583187.us-east1.run.app")
                .build();
    }

    @PostMapping(path = "/all-platforms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPlatformsToken(@RequestBody Map<String, String> credentials) {
        // Extraemos username y password por si quieres validarlos o loguearlos
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Llamada al servicio remoto enviando el mismo body
        Object body = webClient
                .post()
                .uri("/api/v1/autenticacion/token/all/platforms")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(credentials)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        return ResponseEntity.ok(body);
    }
}
