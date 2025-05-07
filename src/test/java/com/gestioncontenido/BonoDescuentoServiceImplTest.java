package com.gestioncontenido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.gestioncontenido.dto.BonoDescuentoDTO;
import com.gestioncontenido.entity.BonoDescuento;
import com.gestioncontenido.repository.BonoDescuentoRepository;
import com.gestioncontenido.service.impl.BonoDescuentoServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BonoDescuentoServiceImplTest {

    @Mock
    private BonoDescuentoRepository bonoRepository;

    @InjectMocks
    private BonoDescuentoServiceImpl bonoService;

    private BonoDescuento bono;
    private BonoDescuentoDTO dto;

    @BeforeEach
    void setUp() {
        bono = BonoDescuento.builder()
                .id(1L)
                .nombre("Descuento Black Friday")
                .valor(20.0)
                .fechaExpiracion(LocalDate.of(2024, 12, 31))
                .build();

        dto = BonoDescuentoDTO.builder()
                .nombre("Descuento Black Friday")
                .valor(20.0)
                .fechaExpiracion(LocalDate.of(2024, 12, 31))
                .build();
    }

    @Test
    void testListar() {
        // Arrange
        when(bonoRepository.findAll()).thenReturn(Arrays.asList(bono));

        // Act
        List<BonoDescuentoDTO> result = bonoService.listar();

        // Assert
        assertEquals(1, result.size());
        assertEquals(bono.getNombre(), result.get(0).getNombre());
        verify(bonoRepository, times(1)).findAll();
    }

    @Test
    void testCrear() {
        // Arrange
        when(bonoRepository.save(any(BonoDescuento.class))).thenReturn(bono);

        // Act
        BonoDescuentoDTO result = bonoService.crear(dto);

        // Assert
        assertNotNull(result.getId());
        assertEquals(bono.getNombre(), result.getNombre());
        verify(bonoRepository, times(1)).save(any(BonoDescuento.class));
    }

    @Test
    void testEditar() {
        // Arrange
        Long id = 1L;
        when(bonoRepository.findById(id)).thenReturn(Optional.of(bono));
        when(bonoRepository.save(any(BonoDescuento.class))).thenReturn(bono);

        // Act
        BonoDescuentoDTO result = bonoService.editar(id, dto);

        // Assert
        assertEquals(bono.getNombre(), result.getNombre());
        verify(bonoRepository, times(1)).findById(id);
        verify(bonoRepository, times(1)).save(bono);
    }

    @Test
    void testEditar_NotFound() {
        // Arrange
        Long id = 99L;
        when(bonoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> bonoService.editar(id, dto));
        verify(bonoRepository, times(1)).findById(id);
        verify(bonoRepository, never()).save(any());
    }

    @Test
    void testEliminar() {
        // Arrange
        Long id = 1L;
        doNothing().when(bonoRepository).deleteById(id);

        // Act
        bonoService.eliminar(id);

        // Assert
        verify(bonoRepository, times(1)).deleteById(id);
    }
}