package com.sgdea.ms_radicacion.commons.sequence.infrastructure.dao;

import com.prolinktic.sgdea.commons.sequence.infrastructure.dto.SecuenciaDto;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de la configuración de secuencias.
 */
@Repository // O @Component si es una clase
public interface SecuenciaDao {
    SecuenciaDto encontrarPorNombreCorto(String nombreCortoTipo);
}