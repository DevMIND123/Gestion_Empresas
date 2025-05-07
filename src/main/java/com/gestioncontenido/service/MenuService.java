package com.gestioncontenido.service;

import com.gestioncontenido.dto.MenuDTO;

import java.util.List;

public interface MenuService {
    List<MenuDTO> listarMenus();
    MenuDTO actualizarMenu(Long id, MenuDTO dto);
}
