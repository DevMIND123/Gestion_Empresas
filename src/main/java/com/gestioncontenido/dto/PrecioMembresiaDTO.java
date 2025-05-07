package com.gestioncontenido.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecioMembresiaDTO {
    private Long id;
    private String tipo;
    private Double precio;
    private String descripcion;
    private LocalDateTime createdAt;
}
