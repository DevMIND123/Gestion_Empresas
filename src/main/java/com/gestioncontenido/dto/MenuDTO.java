package com.gestioncontenido.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime createdAt;
}
