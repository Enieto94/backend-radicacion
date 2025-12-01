package com.sgdea.ms_radicacion.infrastructure.sequence.dao;

import com.sgdea.ms_radicacion.domain.sequence.model.Secuencia;
import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SecuenciaDaoImpl implements SecuenciaDao {

    private final SecuenciaRepository secuenciaRepository;

    @Override
    public SecuenciaDto encontrarPorNombreCorto(String nombreCortoTipo) {
        // En un contexto reactivo, esto debería devolver un Mono<SecuenciaDto>
        // Sin embargo, la interfaz SecuenciaDao devuelve directamente SecuenciaDto.
        // Para mantener la compatibilidad, bloqueamos el Mono aquí.
        // En una aplicación puramente reactiva, la interfaz SecuenciaDao
        // debería devolver Mono<SecuenciaDto>.
        Secuencia secuencia = secuenciaRepository.findByNombreCorto(nombreCortoTipo).block();
        if (secuencia == null) {
            return null;
        }
        // Convertir Secuencia a SecuenciaDto
        return new SecuenciaDto(
                secuencia.getId(),
                secuencia.getNombreCorto(),
                secuencia.getPrefijo(),
                secuencia.getDigitosSecuencia(),
                secuencia.getTipoSecuencia()
        );
    }
}
