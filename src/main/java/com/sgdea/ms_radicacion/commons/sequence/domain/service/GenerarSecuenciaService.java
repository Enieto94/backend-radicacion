package com.prolinktic.sgdea.commons.sequence.domain.service;

/**
 * Interfaz para la generación de números de secuencia únicos.
 */
public interface GenerarSecuenciaService {

    String generarSecuencia(String nombreCortoTipo);

    String generarSecuenciaSinYear(String nombreCortoTipo);
}