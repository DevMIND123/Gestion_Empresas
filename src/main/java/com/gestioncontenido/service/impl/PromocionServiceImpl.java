package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.PromocionDTO;
import com.gestioncontenido.entity.Promocion;
import com.gestioncontenido.exception.BadRequestException;
import com.gestioncontenido.exception.ResourceNotFoundException;
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
        validar(dto);
        Promocion promo = toEntity(dto);
        return toDTO(promocionRepository.save(promo));
    }

    @Override
    public PromocionDTO editar(Long id, PromocionDTO dto) {
        validar(dto);
        Promocion promo = promocionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada"));
        promo.setNombre(dto.getNombre());
        promo.setDescripcion(dto.getDescripcion());
        promo.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        promo.setFechaInicio(dto.getFechaInicio());
        promo.setFechaFin(dto.getFechaFin());
        return toDTO(promocionRepository.save(promo));
    }

    private void validar(PromocionDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de la promoción es obligatorio.");
        }
        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
            throw new BadRequestException("La descripción de la promoción es obligatoria.");
        }
        if (dto.getPorcentajeDescuento() == null || dto.getPorcentajeDescuento() < 0) {
            throw new BadRequestException("El porcentaje de descuento debe ser mayor o igual a 0.");
        }
        if (dto.getFechaInicio() == null || dto.getFechaFin() == null || dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new BadRequestException("Fechas inválidas: asegúrese de que la fecha de inicio y fin sean válidas.");
        }
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
