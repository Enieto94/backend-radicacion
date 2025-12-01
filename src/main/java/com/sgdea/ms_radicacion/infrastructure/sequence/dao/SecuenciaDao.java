package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import reactor.core.publisher.Mono;

/**
 * Interfaz para el acceso a datos de la configuración de secuencias.
 * REACTIVO: retorna Mono<SecuenciaDto>.
 */
public interface SecuenciaDao {
    Mono<SecuenciaDto> encontrarPorNombreCorto(String nombreCortoTipo);
}
