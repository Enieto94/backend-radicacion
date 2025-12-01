package com.sgdea.ms_radicacion.commons.sequence.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos_secuencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoSecuencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre; // Ej: "Tutela", "Radicado General"

    // Constructor, getters y setters generados por Lombok
}