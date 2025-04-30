package com.gestioncontenido.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UbicacionDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String cobertura;
    private LocalDateTime createdAt;
}
