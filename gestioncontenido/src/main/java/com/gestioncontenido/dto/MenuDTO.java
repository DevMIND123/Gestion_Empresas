package com.gestioncontenido.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean visible;
}
