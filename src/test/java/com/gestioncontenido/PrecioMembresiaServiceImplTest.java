package com.gestioncontenido;

import com.gestioncontenido.dto.PrecioMembresiaDTO;
import com.gestioncontenido.entity.PrecioMembresia;
import com.gestioncontenido.repository.PrecioMembresiaRepository;
import com.gestioncontenido.service.impl.PrecioMembresiaServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrecioMembresiaServiceImplTest {

    @Mock
    private PrecioMembresiaRepository repository;

    @InjectMocks
    private PrecioMembresiaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        PrecioMembresia entidad = PrecioMembresia.builder()
                .id(1L).tipo("Premium").precio(29.99).descripcion("Acceso completo").build();

        when(repository.findAll()).thenReturn(List.of(entidad));

        List<PrecioMembresiaDTO> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Premium", resultado.get(0).getTipo());
    }

    @Test
    void testCrear() {
        PrecioMembresiaDTO dto = PrecioMembresiaDTO.builder()
                .tipo("Básico").precio(9.99).descripcion("Acceso limitado").build();

        PrecioMembresia entidadGuardada = PrecioMembresia.builder()
                .id(1L).tipo("Básico").precio(9.99).descripcion("Acceso limitado").build();


        when(repository.save(any(PrecioMembresia.class))).thenReturn(entidadGuardada);

        PrecioMembresiaDTO resultado = service.crear(dto);

        assertNotNull(resultado.getId());
        assertEquals("Básico", resultado.getTipo());
    }

    @Test
    void testEditar() {
        Long id = 1L;
        PrecioMembresia entidad = PrecioMembresia.builder()
                .id(id).tipo("Básico").precio(9.99).descripcion("Antiguo").build();

        PrecioMembresiaDTO dto = PrecioMembresiaDTO.builder()
                .tipo("Pro").precio(19.99).descripcion("Nuevo").build();

        when(repository.findById(id)).thenReturn(Optional.of(entidad));
        when(repository.save(any(PrecioMembresia.class))).thenAnswer(i -> i.getArgument(0));

        PrecioMembresiaDTO actualizado = service.editar(id, dto);

        assertEquals("Pro", actualizado.getTipo());
        assertEquals(19.99, actualizado.getPrecio());
        assertEquals("Nuevo", actualizado.getDescripcion());
    }

    @Test
    void testEditarNotFound() {
        Long id = 99L;
        PrecioMembresiaDTO dto = PrecioMembresiaDTO.builder()
                .tipo("X").precio(0.0).descripcion("X").build();

        when(repository.findById(id)).thenReturn(Optional.empty());


        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> service.editar(id, dto));
        assertEquals("Precio no encontrado", ex.getMessage());
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.eliminar(id);

        verify(repository, times(1)).deleteById(id);
    }

}

