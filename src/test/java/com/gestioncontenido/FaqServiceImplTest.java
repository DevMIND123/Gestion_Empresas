package com.gestioncontenido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.gestioncontenido.dto.FaqDTO;
import com.gestioncontenido.entity.Faq;
import com.gestioncontenido.repository.FaqRepository;
import com.gestioncontenido.service.impl.FaqServiceImpl;
import com.gestioncontenido.exception.ResourceNotFoundException;
import com.gestioncontenido.exception.BadRequestException;

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
public class FaqServiceImplTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqServiceImpl faqService;

    private Faq faq;
    private FaqDTO faqDTO;

    @BeforeEach
    void setUp() {
        faq = Faq.builder()
                .id(1L)
                .pregunta("¿Cómo restablecer mi contraseña?")
                .respuesta("Usa el enlace 'Olvidé mi contraseña' en el login.")
                .visible(true)
                .build();

        faqDTO = FaqDTO.builder()
                .pregunta("¿Cómo actualizar mi perfil?")
                .respuesta("Ve a Configuración > Perfil.")
                .visible(true)
                .build();
    }

    @Test
    void testObtenerTodas() {
        // Arrange
        Faq faqInvisible = Faq.builder().visible(false).build();
        when(faqRepository.findByVisibleTrue()).thenReturn(Arrays.asList(faq));

        // Act
        List<FaqDTO> result = faqService.obtenerTodas();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).isVisible());
        verify(faqRepository, times(1)).findByVisibleTrue();
    }

    @Test
    void testCrear() {
        // Arrange
        when(faqRepository.save(any(Faq.class))).thenReturn(faq);

        // Act
        FaqDTO result = faqService.crear(faqDTO);

        // Assert
        assertEquals(faq.getPregunta(), result.getPregunta());
        assertTrue(result.isVisible()); // Por defecto debe ser visible
        verify(faqRepository, times(1)).save(any(Faq.class));
    }

    @Test
    void testCrear_empty() {
        // Arrange
        faqDTO.setPregunta("");

        // Act & Assert
        assertThrows(BadRequestException.class, () -> faqService.crear(faqDTO));
        verify(faqRepository, never()).save(any());
    }

    @Test
    void testEditar() {
        // Arrange
        when(faqRepository.findById(1L)).thenReturn(Optional.of(faq));
        when(faqRepository.save(any(Faq.class))).thenReturn(faq);

        // Act
        FaqDTO result = faqService.editar(1L, faqDTO);

        // Assert
        assertEquals(faqDTO.getPregunta(), result.getPregunta());
        verify(faqRepository, times(1)).findById(1L);
        verify(faqRepository, times(1)).save(faq);
    }

    @Test
    void testEditar_NotFound() {
        // Arrange
        when(faqRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> faqService.editar(99L, faqDTO));
        verify(faqRepository, never()).save(any());
    }

    @Test
    void testEliminar() {
        // Arrange
        when(faqRepository.findById(1L)).thenReturn(Optional.of(faq));
        when(faqRepository.save(faq)).thenReturn(faq);

        // Act
        faqService.eliminar(1L);

        // Assert
        assertFalse(faq.isVisible());
        verify(faqRepository, times(1)).save(faq);
    }
}