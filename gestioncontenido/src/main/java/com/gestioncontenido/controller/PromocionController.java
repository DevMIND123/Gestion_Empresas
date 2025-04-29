package com.gestioncontenido.controller;

import com.gestioncontenido.dto.PromocionDTO;
import com.gestioncontenido.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @GetMapping
    public ResponseEntity<List<PromocionDTO>> listar() {
        return ResponseEntity.ok(promocionService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<PromocionDTO> crear(@RequestBody PromocionDTO dto) {
        return ResponseEntity.ok(promocionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocionDTO> editar(@PathVariable Long id, @RequestBody PromocionDTO dto) {
        return ResponseEntity.ok(promocionService.editar(id, dto));
    }
}
