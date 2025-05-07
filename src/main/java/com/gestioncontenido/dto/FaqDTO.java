package com.gestioncontenido.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDTO {
    private Long id;
    private String pregunta;
    private String respuesta;
    private boolean visible;
    private LocalDateTime createdAt;
}
