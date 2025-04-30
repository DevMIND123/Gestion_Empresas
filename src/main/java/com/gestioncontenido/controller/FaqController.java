package com.gestioncontenido.controller;

import com.gestioncontenido.dto.FaqDTO;
import com.gestioncontenido.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    public ResponseEntity<List<FaqDTO>> obtenerTodas() {
        return ResponseEntity.ok(faqService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<FaqDTO> crear(@RequestBody FaqDTO dto) {
        return ResponseEntity.ok(faqService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaqDTO> editar(@PathVariable Long id, @RequestBody FaqDTO dto) {
        return ResponseEntity.ok(faqService.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        faqService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
