package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ValorSecuenciaDaoImpl implements ValorSecuenciaDao {

    private final ValorSecuenciaRepository valorSecuenciaRepository;

    @Override
    public ValorSecuencia crear(ValorSecuencia valorSecuencia) {
        // En un contexto reactivo, esto debería devolver un Mono<ValorSecuencia>
        // Para mantener la compatibilidad con la interfaz, bloqueamos el Mono aquí.
        return valorSecuenciaRepository.save(valorSecuencia).block();
    }

    @Override
    public ValorSecuencia crearQuery(String nombre, Long tipoSecuenciaId) {
        // En un contexto reactivo, esto debería devolver un Mono<ValorSecuencia>
        // Para mantener la compatibilidad con la interfaz, bloqueamos el Mono aquí.
        return valorSecuenciaRepository.crearValorSecuencia(nombre, tipoSecuenciaId).block();
    }
}
