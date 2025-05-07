package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.BonoDescuentoDTO;
import com.gestioncontenido.entity.BonoDescuento;
import com.gestioncontenido.exception.BadRequestException;
import com.gestioncontenido.exception.ResourceNotFoundException;
import com.gestioncontenido.repository.BonoDescuentoRepository;
import com.gestioncontenido.service.BonoDescuentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BonoDescuentoServiceImpl implements BonoDescuentoService {

    private final BonoDescuentoRepository bonoRepository;

    @Override
    public List<BonoDescuentoDTO> listar() {
        return bonoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BonoDescuentoDTO crear(BonoDescuentoDTO dto) {
        validar(dto);
        BonoDescuento bono = toEntity(dto);
        return toDTO(bonoRepository.save(bono));
    }

    @Override
    public BonoDescuentoDTO editar(Long id, BonoDescuentoDTO dto) {
        validar(dto);
        BonoDescuento bono = bonoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bono no encontrado"));
        bono.setNombre(dto.getNombre());
        bono.setValor(dto.getValor());
        bono.setFechaExpiracion(dto.getFechaExpiracion());
        return toDTO(bonoRepository.save(bono));
    }

    @Override
    public void eliminar(Long id) {
        bonoRepository.deleteById(id);
    }

    private void validar(BonoDescuentoDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre no puede estar vacío.");
        }
        if (dto.getValor() == null || dto.getValor() <= 0) {
            throw new BadRequestException("El valor debe ser mayor que cero.");
        }
        if (dto.getFechaExpiracion() == null) {
            throw new BadRequestException("La fecha de expiración es obligatoria.");
        }
    }

    private BonoDescuentoDTO toDTO(BonoDescuento bono) {
        return BonoDescuentoDTO.builder()
                .id(bono.getId())
                .nombre(bono.getNombre())
                .valor(bono.getValor())
                .fechaExpiracion(bono.getFechaExpiracion())
                .build();
    }

    private BonoDescuento toEntity(BonoDescuentoDTO dto) {
        return BonoDescuento.builder()
                .nombre(dto.getNombre())
                .valor(dto.getValor())
                .fechaExpiracion(dto.getFechaExpiracion())
                .build();
    }
}
