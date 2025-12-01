package com.sgdea.ms_radicacion.service;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MiServicioDeNegocio {

    private final GenerarSecuenciaService generarSecuenciaService;

    public String crearAlgoConSecuencia() {
        String radicado = generarSecuenciaService.generarSecuencia("MTR");
        // ... lógica de negocio
        return radicado;
    }
}
