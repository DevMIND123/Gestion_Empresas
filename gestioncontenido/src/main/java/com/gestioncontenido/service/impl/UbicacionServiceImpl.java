package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.UbicacionDTO;
import com.gestioncontenido.entity.Ubicacion;
import com.gestioncontenido.repository.UbicacionRepository;
import com.gestioncontenido.service.UbicacionService;
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
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
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
