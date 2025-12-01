package com.sgdea.ms_radicacion.controller;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/generar-secuencia")
@RequiredArgsConstructor
public class RadicationControlller {

    // Inyección del servicio
    private final GenerarSecuenciaService generarSecuenciaService;

    // CAMBIO: Endpoint para generar secuencia con año
    @GetMapping("/{nombreCortoTipo}")
    public Mono<ResponseEntity<String>> generarSecuencia(@PathVariable String nombreCortoTipo) {
        Mono<String> resultado = generarSecuenciaService.generarSecuencia(nombreCortoTipo);
        if (resultado == null) {
            resultado = Mono.empty();
        }
        return resultado
                .map(valor -> ResponseEntity.ok(valor)) // 2. Si todo va bien, crea una respuesta 200 OK
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build())) // 3. Si no se encuentra la secuencia, devuelve 404 Not Found
                .onErrorResume(ex -> Mono.just(ResponseEntity.internalServerError().body("Error: " + ex.getMessage()))); // 4. Manejo de errores
    }

    // CAMBIO: Endpoint para generar secuencia sin año
    @GetMapping("/sin-year/{nombreCortoTipo}")
    public Mono<ResponseEntity<String>> generarSecuenciaSinYear(@PathVariable String nombreCortoTipo) {
        Mono<String> resultado = generarSecuenciaService.generarSecuenciaSinYear(nombreCortoTipo);
        if (resultado == null) {
            resultado = Mono.empty();
        }
        return resultado
                .map(valor -> ResponseEntity.ok(valor))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .onErrorResume(ex -> Mono.just(ResponseEntity.internalServerError().body("Error: " + ex.getMessage())));
    }
}
