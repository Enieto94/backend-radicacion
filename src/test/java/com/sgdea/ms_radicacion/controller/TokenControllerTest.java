package com.sgdea.ms_radicacion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TokenControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllPlatformsToken_shouldReturnOk() {
        Map<String, String> body = new HashMap<>();
        body.put("username", "cprada");
        body.put("password", "SGdea2024*");

        webTestClient.post()
                .uri("/api/v1/tokens/all-platforms")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk();
    }
}
