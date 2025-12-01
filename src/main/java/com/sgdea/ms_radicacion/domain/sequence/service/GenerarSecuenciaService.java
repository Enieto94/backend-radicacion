package com.sgdea.ms_radicacion.domain.sequence.service;

/**
 * Interfaz para la generación de números de secuencia únicos.
 */
public interface GenerarSecuenciaService {

    String generarSecuencia(String nombreCortoTipo);

    String generarSecuenciaSinYear(String nombreCortoTipo);
}
