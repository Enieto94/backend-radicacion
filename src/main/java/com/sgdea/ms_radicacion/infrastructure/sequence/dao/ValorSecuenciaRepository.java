package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ValorSecuenciaRepository extends R2dbcRepository<ValorSecuencia, Long> {

    // Método para crear un ValorSecuencia usando una consulta nativa o personalizada
    // Esto es un ejemplo, la implementación real podría variar dependiendo de la base de datos y la lógica.
    @Query("INSERT INTO valores_secuencia (valor_generado, tipo_secuencia_id) VALUES (:valor, :tipoSecuenciaId) RETURNING id, valor_generado, tipo_secuencia_id")
    Mono<ValorSecuencia> crearValorSecuencia(String valor, Long tipoSecuenciaId);
}
