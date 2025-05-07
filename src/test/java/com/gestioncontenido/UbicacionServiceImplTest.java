package com.gestioncontenido;

import com.gestioncontenido.dto.UbicacionDTO;
import com.gestioncontenido.entity.Ubicacion;
import com.gestioncontenido.repository.UbicacionRepository;
import com.gestioncontenido.service.impl.UbicacionServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UbicacionServiceImplTest {

    private UbicacionRepository ubicacionRepository;
    private UbicacionServiceImpl ubicacionService;

    @BeforeEach
    void setUp() {
        ubicacionRepository = mock(UbicacionRepository.class);
        ubicacionService = new UbicacionServiceImpl(ubicacionRepository);
    }

    @Test
    void testListarUbicaciones() {
        LocalDateTime now = LocalDateTime.now();
        Ubicacion ubicacion1 = new Ubicacion(1L, "Zona Norte", "Cobertura parcial", "Norte", now);
        Ubicacion ubicacion2 = new Ubicacion(2L, "Zona Sur", "Cobertura completa", "Sur", now);
        when(ubicacionRepository.findAll()).thenReturn(Arrays.asList(ubicacion1, ubicacion2));

        List<UbicacionDTO> resultado = ubicacionService.listarUbicaciones();

        assertEquals(2, resultado.size());
        assertEquals("Zona Norte", resultado.get(0).getNombre());
        assertEquals("Zona Sur", resultado.get(1).getNombre());
        verify(ubicacionRepository, times(1)).findAll();
    }

    @Test
    void testActualizarUbicacion_Exitosa() {
        Long id = 1L;
        LocalDateTime createdAt = LocalDateTime.of(2023, 1, 1, 0, 0);
        Ubicacion ubicacion = new Ubicacion(id, "Zona Original", "Descripción original", "Centro", createdAt);

        UbicacionDTO dtoActualizado = UbicacionDTO.builder()
                .id(id)
                .nombre("Zona Actualizada")
                .descripcion("Nueva descripción")
                .cobertura("Norte")
                .build();

        when(ubicacionRepository.findById(id)).thenReturn(Optional.of(ubicacion));
        when(ubicacionRepository.save(Mockito.any(Ubicacion.class))).thenAnswer(invoc -> invoc.getArgument(0));

        UbicacionDTO resultado = ubicacionService.actualizarUbicacion(id, dtoActualizado);

        assertEquals("Zona Actualizada", resultado.getNombre());
        assertEquals("Nueva descripción", resultado.getDescripcion());
        assertEquals("Norte", resultado.getCobertura());

        verify(ubicacionRepository).findById(id);
        verify(ubicacionRepository).save(Mockito.any(Ubicacion.class));
    }

    @Test
    void testActualizarUbicacion_NoEncontrada() {
        Long id = 999L;
        UbicacionDTO dto = UbicacionDTO.builder()
                .id(id)
                .nombre("X")
                .descripcion("Y")
                .cobertura("Z")
                .build();

        when(ubicacionRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> ubicacionService.actualizarUbicacion(id, dto));

        assertEquals("Ubicación no encontrada", ex.getMessage());
        verify(ubicacionRepository).findById(id);
    }
}
