package com.gestioncontenido.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "precios_membresia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecioMembresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ej: Básica, Premium, Familiar

    private double precio;

    private String descripcion;
  
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
