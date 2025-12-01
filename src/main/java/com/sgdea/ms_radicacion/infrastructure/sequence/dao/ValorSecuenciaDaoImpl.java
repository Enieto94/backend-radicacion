package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ValorSecuenciaDaoImpl implements ValorSecuenciaDao {

    private final ValorSecuenciaRepository valorSecuenciaRepository;

    @Override
    public Mono<ValorSecuencia> crear(ValorSecuencia valorSecuencia) {
        return valorSecuenciaRepository.save(valorSecuencia);
    }

    @Override
    public Mono<ValorSecuencia> crearQuery(String nombre, Long tipoSecuenciaId) {
        return valorSecuenciaRepository.crearValorSecuencia(nombre, tipoSecuenciaId);
    }
}
