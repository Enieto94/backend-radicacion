package com.sgdea.ms_radicacion.commons.sequence.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "secuencias_config") // Nombre de tabla para evitar conflicto con 'secuencias' de la discusión anterior
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secuencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_corto", unique = true, nullable = false)
    private String nombreCorto; // Ej: "TUT", "RAD"

    @Column(name = "prefijo", nullable = false)
    private String prefijo; // Ej: "TUT-"

    @Column(name = "digitos_secuencia", nullable = false)
    private Integer digitosSecuencia; // Ej: 5 (para 00001)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_secuencia_id", nullable = false)
    private TipoSecuencia tipoSecuencia; // Relación con el tipo de secuencia

    // Constructor, getters y setters generados por Lombok
}