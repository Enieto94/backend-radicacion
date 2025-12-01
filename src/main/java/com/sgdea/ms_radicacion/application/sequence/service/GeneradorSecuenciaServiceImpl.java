package com.sgdea.ms_radicacion.application.sequence.service;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import com.sgdea.ms_radicacion.infrastructure.sequence.dao.SecuenciaDao;
import com.sgdea.ms_radicacion.infrastructure.sequence.dao.ValorSecuenciaDao;
import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class GeneradorSecuenciaServiceImpl implements GenerarSecuenciaService { // Implementa la interfaz del dominio

    private final SecuenciaDao secuenciaDao;
    private final ValorSecuenciaDao valorSecuenciaDao;

    public GeneradorSecuenciaServiceImpl(SecuenciaDao secuenciaDao, ValorSecuenciaDao valorSecuenciaDao) {
        this.secuenciaDao = secuenciaDao;
        this.valorSecuenciaDao = valorSecuenciaDao;
    }

    @Override
    public Mono<String> generarSecuencia(String nombreCortoTipo) {
        // CAMBIO: Se usa una cadena reactiva con flatMap y map
        return secuenciaDao.encontrarPorNombreCorto(nombreCortoTipo)
                .switchIfEmpty(Mono.error(new NullPointerException("No existe configuracion para este tipo de secuencia")))
                .flatMap(secuenciaConf -> {
                    final String nombre = new StringBuilder()
                            .append(secuenciaConf.getPrefijo())
                            .append(LocalDate.now().getYear())
                            .toString();

                    Mono<ValorSecuencia> valorSecuenciaMono = valorSecuenciaDao.crearQuery(nombre, secuenciaConf.getTipoSecuencia().getId());

                    // Usamos zipWith para combinar el resultado de valorSecuenciaMono con la configuración que ya tenemos
                    return valorSecuenciaMono.map(valorSecuencia -> new StringBuilder()
                            .append(nombre)
                            .append(StringUtils.leftPad("" + valorSecuencia.getId(), secuenciaConf.getDigitosSecuencia().intValue(), "0"))
                            .toString());
                });
    }

    @Override
    public Mono<String> generarSecuenciaSinYear(String nombreCortoTipo) {
        // CAMBIO: Se usa una cadena reactiva con flatMap y map
        return secuenciaDao.encontrarPorNombreCorto(nombreCortoTipo)
                .switchIfEmpty(Mono.error(new NullPointerException("No existe configuracion para este tipo de secuencia")))
                .flatMap(secuenciaConf -> {
                    final String nombre = secuenciaConf.getPrefijo();

                    Mono<ValorSecuencia> valorSecuenciaMono = valorSecuenciaDao.crear(ValorSecuencia.builder()
                            .tipoSecuencia(secuenciaConf.getTipoSecuencia())
                            .valor(nombre)
                            .build());

                    return valorSecuenciaMono.map(valorSecuencia -> new StringBuilder()
                            .append(nombre)
                            .append(StringUtils.leftPad("" + valorSecuencia.getId(), secuenciaConf.getDigitosSecuencia().intValue(), "0"))
                            .toString());
                });
    }
}
