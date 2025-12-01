package com.sgdea.ms_radicacion.service;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MiServicioDeNegocio {

    private final GenerarSecuenciaService generarSecuenciaService;

    public Mono<String> crearAlgoConSecuencia() {
        return generarSecuenciaService.generarSecuencia("MTR")
            .map(radicado -> {
                // ... lógica de negocio con radicado
                return radicado;
            });
    }
}
