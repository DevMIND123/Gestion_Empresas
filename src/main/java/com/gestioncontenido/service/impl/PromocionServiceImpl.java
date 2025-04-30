package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.PromocionDTO;
import com.gestioncontenido.entity.Promocion;
import com.gestioncontenido.repository.PromocionRepository;
import com.gestioncontenido.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository promocionRepository;

    @Override
    public List<PromocionDTO> listarTodas() {
        return promocionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PromocionDTO crear(PromocionDTO dto) {
        Promocion promo = toEntity(dto);
        return toDTO(promocionRepository.save(promo));
    }

    @Override
    public PromocionDTO editar(Long id, PromocionDTO dto) {
        Promocion promo = promocionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));
        promo.setNombre(dto.getNombre());
        promo.setDescripcion(dto.getDescripcion());
        promo.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        promo.setFechaInicio(dto.getFechaInicio());
        promo.setFechaFin(dto.getFechaFin());
        return toDTO(promocionRepository.save(promo));
    }

    private PromocionDTO toDTO(Promocion promo) {
        return PromocionDTO.builder()
                .id(promo.getId())
                .nombre(promo.getNombre())
                .descripcion(promo.getDescripcion())
                .porcentajeDescuento(promo.getPorcentajeDescuento())
                .fechaInicio(promo.getFechaInicio())
                .fechaFin(promo.getFechaFin())
                .build();
    }

    private Promocion toEntity(PromocionDTO dto) {
        return Promocion.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .porcentajeDescuento(dto.getPorcentajeDescuento())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .build();
    }
}
