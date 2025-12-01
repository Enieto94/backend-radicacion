package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import org.springframework.stereotype.Repository;

/**
 * Interfaz para el acceso a datos de los valores de secuencia generados.
 */
@Repository // O @Component si es una clase
public interface ValorSecuenciaDao {
    ValorSecuencia crear(ValorSecuencia valorSecuencia);

    ValorSecuencia crearQuery(String nombre, Long tipoSecuenciaId); // Metodo usado en GeneradorSecuenciaServiceImpl

}
