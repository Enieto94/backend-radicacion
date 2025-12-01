package com.sgdea.ms_radicacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/radicacion")

public class RadicationControlller {

    @PostMapping
    public ResponseEntity<Map<String, Object>> holaMundo() {
        // JSON vacío: {}
        Map<String, Object> body = Collections.emptyMap();

        // Cabecera con mensaje "hola mundo"
        return ResponseEntity
                .ok()
                .header("X-Mensaje", "hola mundo")
                .body(body);
    }
}
