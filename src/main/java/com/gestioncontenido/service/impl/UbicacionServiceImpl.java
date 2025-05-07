package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.UbicacionDTO;
import com.gestioncontenido.entity.Ubicacion;
import com.gestioncontenido.repository.UbicacionRepository;
import com.gestioncontenido.service.UbicacionService;
import com.gestioncontenido.exception.ResourceNotFoundException;
import com.gestioncontenido.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    @Override
    public List<UbicacionDTO> listarUbicaciones() {
        return ubicacionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UbicacionDTO actualizarUbicacion(Long id, UbicacionDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty() ||
            dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty() ||
            dto.getCobertura() == null || dto.getCobertura().trim().isEmpty()) {
            throw new BadRequestException("Todos los campos deben estar completos.");
        }

        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada"));

        ubicacion.setNombre(dto.getNombre());
        ubicacion.setDescripcion(dto.getDescripcion());
        ubicacion.setCobertura(dto.getCobertura());
        return toDTO(ubicacionRepository.save(ubicacion));
    }

    private UbicacionDTO toDTO(Ubicacion ubicacion) {
        return UbicacionDTO.builder()
                .id(ubicacion.getId())
                .nombre(ubicacion.getNombre())
                .descripcion(ubicacion.getDescripcion())
                .cobertura(ubicacion.getCobertura())
                .build();
    }
}
