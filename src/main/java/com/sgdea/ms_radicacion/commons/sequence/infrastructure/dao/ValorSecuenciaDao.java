package com.prolinktic.sgdea.commons.sequence.infrastructure.dao;

import com.prolinktic.sgdea.commons.sequence.domain.model.ValorSecuencia;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de los valores de secuencia generados.
 */
@Repository // O @Component si es una clase
public interface ValorSecuenciaDao {
    ValorSecuencia crear(ValorSecuencia valorSecuencia);

    ValorSecuencia crearQuery(String nombre, Long tipoSecuenciaId); // Método usado en GeneradorSecuenciaServiceImpl
}