package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de la configuración de secuencias.
 */
@Repository // O @Component si es una clase
public interface SecuenciaDao {
    Mono<SecuenciaDto> encontrarPorNombreCorto(String nombreCortoTipo);
}
