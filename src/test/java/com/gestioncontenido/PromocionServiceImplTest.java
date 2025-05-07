package com.gestioncontenido;

import com.gestioncontenido.dto.PromocionDTO;
import com.gestioncontenido.entity.Promocion;
import com.gestioncontenido.repository.PromocionRepository;
import com.gestioncontenido.service.impl.PromocionServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PromocionServiceImplTest {

    private PromocionRepository promocionRepository;
    private PromocionServiceImpl promocionService;

    @BeforeEach
    public void setUp() {
        promocionRepository = mock(PromocionRepository.class);
        promocionService = new PromocionServiceImpl(promocionRepository);
    }

    @Test
    public void testListarTodas() {
        Promocion promo = Promocion.builder()
                .id(1L)
                .nombre("Promo 1")
                .descripcion("Descuento")
                .porcentajeDescuento(20.0)
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusDays(10))
                .build();
        when(promocionRepository.findAll()).thenReturn(Arrays.asList(promo));

        List<PromocionDTO> result = promocionService.listarTodas();

        assertEquals(1, result.size());
        assertEquals("Promo 1", result.get(0).getNombre());
    }

    @Test
    public void testCrear() {
        PromocionDTO dto = PromocionDTO.builder()
                .nombre("Nueva Promo")
                .descripcion("Descripción")
                .porcentajeDescuento(15.0)
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusDays(5))
                .build();

        Promocion saved = Promocion.builder()
                .id(1L)
                .nombre("Nueva Promo")
                .descripcion("Descripción")
                .porcentajeDescuento(15.0)
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .build();

        when(promocionRepository.save(ArgumentMatchers.any())).thenReturn(saved);

        PromocionDTO result = promocionService.crear(dto);

        assertNotNull(result.getId());
        assertEquals("Nueva Promo", result.getNombre());
    }

    @Test
    public void testEditar() {
        Long id = 1L;
        Promocion existente = Promocion.builder()
                .id(id)
                .nombre("Vieja")
                .descripcion("Vieja desc")
                .porcentajeDescuento(10.0)
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusDays(5))
                .build();

        PromocionDTO dtoEditado = PromocionDTO.builder()
                .nombre("Editado")
                .descripcion("Nueva desc")
                .porcentajeDescuento(25.0)
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusDays(7))
                .build();

        when(promocionRepository.findById(id)).thenReturn(Optional.of(existente));
        when(promocionRepository.save(ArgumentMatchers.any())).thenAnswer(inv -> inv.getArgument(0));

        PromocionDTO result = promocionService.editar(id, dtoEditado);

        assertEquals("Editado", result.getNombre());
        assertEquals(25, result.getPorcentajeDescuento());
    }

    @Test
    public void testEditarPromocion_NotFound() {
        Long id = 999L;
        PromocionDTO dto = PromocionDTO.builder()
                .nombre("No importa")
                .descripcion("Tampoco")
                .porcentajeDescuento(5.0)
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusDays(1))
                .build();

        when(promocionRepository.findById(id)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            promocionService.editar(id, dto);
        });

        assertEquals("Promoción no encontrada", thrown.getMessage());
    }
}
