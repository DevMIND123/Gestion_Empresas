package com.gestioncontenido.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonoDescuentoDTO {
    private Long id;
    private String nombre;
    private Double valor;
    private LocalDate fechaExpiracion;
    private boolean isActive;
    private LocalDateTime createdAt;
}
