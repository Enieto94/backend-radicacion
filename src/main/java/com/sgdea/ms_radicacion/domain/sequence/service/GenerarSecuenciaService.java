package com.sgdea.ms_radicacion.domain.sequence.service;

import reactor.core.publisher.Mono;

/**
 * Interfaz para la generación de números de secuencia únicos.
 */
public interface GenerarSecuenciaService {

    // CAMBIO: Devuelve Mono<String> para ser reactivo
    Mono<String> generarSecuencia(String nombreCortoTipo);

    // CAMBIO: Devuelve Mono<String> para ser reactivo
    Mono<String> generarSecuenciaSinYear(String nombreCortoTipo);
}
