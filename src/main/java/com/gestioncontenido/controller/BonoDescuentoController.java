package com.gestioncontenido.controller;

import com.gestioncontenido.dto.BonoDescuentoDTO;
import com.gestioncontenido.service.BonoDescuentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bonos")
@RequiredArgsConstructor
public class BonoDescuentoController {

    private final BonoDescuentoService bonoService;

    @GetMapping
    public ResponseEntity<List<BonoDescuentoDTO>> listar() {
        return ResponseEntity.ok(bonoService.listar());
    }

    @PostMapping
    public ResponseEntity<BonoDescuentoDTO> crear(@RequestBody BonoDescuentoDTO dto) {
        return ResponseEntity.ok(bonoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonoDescuentoDTO> editar(@PathVariable Long id, @RequestBody BonoDescuentoDTO dto) {
        return ResponseEntity.ok(bonoService.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bonoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
