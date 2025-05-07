package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.FaqDTO;
import com.gestioncontenido.entity.Faq;
import com.gestioncontenido.exception.BadRequestException;
import com.gestioncontenido.exception.ResourceNotFoundException;
import com.gestioncontenido.repository.FaqRepository;
import com.gestioncontenido.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    @Override
    public List<FaqDTO> obtenerTodas() {
        return faqRepository.findByVisibleTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FaqDTO crear(FaqDTO dto) {
        if (dto.getPregunta() == null || dto.getPregunta().isBlank()) {
            throw new BadRequestException("La pregunta no puede estar vacía");
        }
        if (dto.getRespuesta() == null || dto.getRespuesta().isBlank()) {
            throw new BadRequestException("La respuesta no puede estar vacía");
        }

        Faq faq = Faq.builder()
                .pregunta(dto.getPregunta())
                .respuesta(dto.getRespuesta())
                .visible(true)
                .build();
        return convertToDTO(faqRepository.save(faq));
    }

    @Override
    public FaqDTO editar(Long id, FaqDTO dto) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FAQ no encontrada"));
        faq.setPregunta(dto.getPregunta());
        faq.setRespuesta(dto.getRespuesta());
        return convertToDTO(faqRepository.save(faq));
    }

    @Override
    public void eliminar(Long id) {
        Faq faq = faqRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FAQ no encontrada"));
        faq.setVisible(false); // borrado lógico
        faqRepository.save(faq);
    }

    private FaqDTO convertToDTO(Faq faq) {
        return FaqDTO.builder()
                .id(faq.getId())
                .pregunta(faq.getPregunta())
                .respuesta(faq.getRespuesta())
                .visible(faq.isVisible())
                .build();
    }
}
