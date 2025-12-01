package com.sgdea.ms_radicacion.application.sequence.service;

import com.sgdea.ms_radicacion.domain.sequence.service.GenerarSecuenciaService;
import com.sgdea.ms_radicacion.domain.sequence.model.ValorSecuencia;
import com.sgdea.ms_radicacion.infrastructure.sequence.dao.SecuenciaDao;
import com.sgdea.ms_radicacion.infrastructure.sequence.dao.ValorSecuenciaDao;
import com.sgdea.ms_radicacion.infrastructure.sequence.dto.SecuenciaDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public String generarSecuencia(String nombreCortoTipo) {

        SecuenciaDto secuenciaConf = secuenciaDao.encontrarPorNombreCorto(nombreCortoTipo);
        if(secuenciaConf == null)
            throw new NullPointerException("No existe configuracion para este tipo de secuencia");

        final String nombre = new StringBuilder()
                .append(secuenciaConf.getPrefijo())
                .append(LocalDate.now().getYear())
                .toString();

        ValorSecuencia valorSecuencia = valorSecuenciaDao.crearQuery(nombre,secuenciaConf.getTipoSecuencia().getId());
        return new StringBuilder()
                .append(nombre)
                .append(StringUtils.leftPad("" + valorSecuencia.getId(), secuenciaConf.getDigitosSecuencia().intValue(), "0"))
                .toString();
    }

    @Override
    public String generarSecuenciaSinYear(String nombreCortoTipo) {
        SecuenciaDto secuenciaConf = secuenciaDao.encontrarPorNombreCorto(nombreCortoTipo);
        if(secuenciaConf == null)
            throw new NullPointerException("No existe configuracion para este tipo de secuencia");

        final String nombre = new StringBuilder()
                .append(secuenciaConf.getPrefijo())
                .toString();

        ValorSecuencia valorSecuencia = valorSecuenciaDao.crear(ValorSecuencia.builder()
                .tipoSecuencia(secuenciaConf.getTipoSecuencia())
                .valor(nombre)
                .build());
        ;
        return new StringBuilder()
                .append(nombre)
                .append(StringUtils.leftPad("" + valorSecuencia.getId(), secuenciaConf.getDigitosSecuencia().intValue(), "0"))
                .toString();
    }
}
