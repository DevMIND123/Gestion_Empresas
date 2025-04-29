package com.gestioncontenido.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonoDescuentoDTO {
    private Long id;
    private String nombre;
    private double valor;
    private LocalDate fechaExpiracion;
}
