package com.gestioncontenido.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDTO {
    private Long id;
    private String pregunta;
    private String respuesta;
    private boolean visible;
}
