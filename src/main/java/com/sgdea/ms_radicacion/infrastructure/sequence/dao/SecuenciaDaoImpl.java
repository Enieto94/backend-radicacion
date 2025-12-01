package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SecuenciaDaoImpl implements SecuenciaDao {

    private final SecuenciaRepository secuenciaRepository;

    @Override
    public Mono<SecuenciaDto> encontrarPorNombreCorto(String nombreCortoTipo) {
        return secuenciaRepository.findByNombreCorto(nombreCortoTipo)
                .map(secuencia -> new SecuenciaDto(
                        secuencia.getId(),
                        secuencia.getNombreCorto(),
                        secuencia.getPrefijo(),
                        secuencia.getDigitosSecuencia(),
                        secuencia.getTipoSecuencia()
                ));
    }
}
