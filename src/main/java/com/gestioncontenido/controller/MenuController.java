package com.gestioncontenido.controller;

import com.gestioncontenido.dto.MenuDTO;
import com.gestioncontenido.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuDTO>> listar() {
        return ResponseEntity.ok(menuService.listarMenus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> actualizar(@PathVariable Long id, @RequestBody MenuDTO dto) {
        return ResponseEntity.ok(menuService.actualizarMenu(id, dto));
    }
}
