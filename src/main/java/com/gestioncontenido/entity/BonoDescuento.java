package com.gestioncontenido.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bonos_descuento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private double valor;

    private LocalDate fechaExpiracion;

    private boolean isActive;
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
