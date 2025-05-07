package com.gestioncontenido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.gestioncontenido.dto.MenuDTO;
import com.gestioncontenido.entity.Menu;
import com.gestioncontenido.repository.MenuRepository;
import com.gestioncontenido.service.impl.MenuServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    private Menu menu;
    private MenuDTO menuDTO;

    @BeforeEach
    void setUp() {
        menu = Menu.builder()
                .id(1L)
                .titulo("Menú Principal")
                .descripcion("Menú de navegación principal")
                .build();

        menuDTO = MenuDTO.builder()
                .titulo("Menú Actualizado")
                .descripcion("Descripción actualizada")
                .build();
    }

    @Test
    void testListar() {
        // Arrange
        when(menuRepository.findAll()).thenReturn(Arrays.asList(menu));

        // Act
        List<MenuDTO> result = menuService.listarMenus();

        // Assert
        assertEquals(1, result.size());
        assertEquals(menu.getTitulo(), result.get(0).getTitulo());
        verify(menuRepository, times(1)).findAll();
    }

    @Test
    void testActualizar() {
        // Arrange
        Long id = 1L;
        when(menuRepository.findById(id)).thenReturn(Optional.of(menu));
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        // Act
        MenuDTO result = menuService.actualizarMenu(id, menuDTO);

        // Assert
        assertEquals(menuDTO.getTitulo(), result.getTitulo());
        verify(menuRepository, times(1)).findById(id);
        verify(menuRepository, times(1)).save(menu);
    }

    @Test
    void testActualizar_NotFound() {
        // Arrange
        Long id = 99L;
        when(menuRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> menuService.actualizarMenu(id, menuDTO));
        verify(menuRepository, never()).save(any());
    }

}