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
                // 1. Si el servicio devuelve un radicado, lo mapeamos a una respuesta 200 OK.
                .map(radicado -> ResponseEntity.ok(Collections.singletonMap("radicado", radicado)))
                // 2. Si el Mono llega aquí vacío (ya sea porque no se encontró o porque un error fue manejado y convertido a vacío),
                //    se devuelve una respuesta 404 Not Found.
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
