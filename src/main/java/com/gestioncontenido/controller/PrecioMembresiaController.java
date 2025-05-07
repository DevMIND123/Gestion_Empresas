package com.gestioncontenido.controller;

import com.gestioncontenido.dto.PrecioMembresiaDTO;
import com.gestioncontenido.service.PrecioMembresiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/precios")
@RequiredArgsConstructor
public class PrecioMembresiaController {

    private final PrecioMembresiaService service;

    @GetMapping
    public ResponseEntity<List<PrecioMembresiaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<PrecioMembresiaDTO> crear(@RequestBody PrecioMembresiaDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrecioMembresiaDTO> editar(@PathVariable Long id, @RequestBody PrecioMembresiaDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
