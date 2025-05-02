package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.MenuDTO;
import com.gestioncontenido.entity.Menu;
import com.gestioncontenido.repository.MenuRepository;
import com.gestioncontenido.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuDTO> listarMenus() {
        return menuRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO actualizarMenu(Long id, MenuDTO dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado"));
        menu.setTitulo(dto.getTitulo());
        menu.setDescripcion(dto.getDescripcion());
        return toDTO(menuRepository.save(menu));
    }

    private MenuDTO toDTO(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .titulo(menu.getTitulo())
                .descripcion(menu.getDescripcion())
                .build();
    }
}
