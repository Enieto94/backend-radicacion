package com.sgdea.ms_radicacion.controller;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/radicacion") // Base path del controlador
@RequiredArgsConstructor // Para inyectar GenerarSecuenciaService
public class RadicationControlller {

    private final GenerarSecuenciaService generarSecuenciaService;

    @PostMapping("/generar-radicado") // Endpoint para generar radicado
    public Mono<ResponseEntity<Map<String, String>>> generarRadicado(@RequestParam String tipoRadicado) {
        return generarSecuenciaService.generarSecuencia(tipoRadicado)
                .map(radicado -> ResponseEntity.ok(Collections.singletonMap("radicado", radicado)))
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Maneja el caso donde el Mono es vacío (ej. secuencia no encontrada)
    }
}
