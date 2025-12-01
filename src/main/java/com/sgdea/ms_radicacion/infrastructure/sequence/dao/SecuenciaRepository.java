package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.Secuencia;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface SecuenciaRepository extends R2dbcRepository<Secuencia, Long> {
    Mono<Secuencia> findByNombreCorto(String nombreCorto);
}
