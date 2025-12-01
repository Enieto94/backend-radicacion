package com.sgdea.ms_radicacion.commons.sequence.infrastructure.dto;

import com.sgdea.ms_radicacion.commons.sequence.domain.model.TipoSecuencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la configuración de una secuencia.
 * Utilizado para transferir datos desde la capa de persistencia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecuenciaDto {
    private Long id;
    private String nombreCorto;
    private String prefijo;
    private Integer digitosSecuencia;
    private TipoSecuencia tipoSecuencia; // Puede ser un DTO también si no se quiere exponer la entidad

    // Constructor, getters y setters generados por Lombok
}