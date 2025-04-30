package com.gestioncontenido.controller;

import com.gestioncontenido.dto.UbicacionDTO;
import com.gestioncontenido.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> listar() {
        return ResponseEntity.ok(ubicacionService.listarUbicaciones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> actualizar(@PathVariable Long id, @RequestBody UbicacionDTO dto) {
        return ResponseEntity.ok(ubicacionService.actualizarUbicacion(id, dto));
    }
}
