package com.sgdea.ms_radicacion.controller;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mtr")
@RequiredArgsConstructor
public class MtrSequenceController {

    private final GenerarSecuenciaService generarSecuenciaService;

    /**
     * Endpoint para generar una nueva secuencia con el prefijo "MTR".
     * Este endpoint es público y no requiere autenticación.
     */
    @PostMapping("/generar-secuencia")
    public Mono<ResponseEntity<Map<String, String>>> generarSecuenciaMtr() {
        // Llama al servicio de generación de secuencias con el prefijo "MTR"
        return generarSecuenciaService.generarSecuencia("MTR")
                .map(secuenciaGenerada -> ResponseEntity.ok(Collections.singletonMap("secuencia", secuenciaGenerada)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
