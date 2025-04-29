package com.gestioncontenido.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecioMembresiaDTO {
    private Long id;
    private String tipo;
    private double precio;
    private String descripcion;
}
