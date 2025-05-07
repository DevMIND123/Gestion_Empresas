package com.gestioncontenido.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromocionDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double porcentajeDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean isActive;
    private LocalDateTime createdAt;
}
