package com.sgdea.ms_radicacion.domain.sequence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "valores_secuencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValorSecuencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Este ID es el número consecutivo que se usa en la secuencia

    @Column(name = "valor_generado", nullable = false)
    private String valor; // El valor completo generado (ej: "2023-TUT-00001")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_secuencia_id", nullable = false)
    private TipoSecuencia tipoSecuencia; // El tipo de secuencia al que pertenece

}
