package com.gestioncontenido.service.impl;

import com.gestioncontenido.dto.PrecioMembresiaDTO;
import com.gestioncontenido.entity.PrecioMembresia;
import com.gestioncontenido.repository.PrecioMembresiaRepository;
import com.gestioncontenido.service.PrecioMembresiaService;
import com.gestioncontenido.exception.BadRequestException;
import com.gestioncontenido.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrecioMembresiaServiceImpl implements PrecioMembresiaService {

    private final PrecioMembresiaRepository repository;

    @Override
    public List<PrecioMembresiaDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrecioMembresiaDTO crear(PrecioMembresiaDTO dto) {
        validarDTO(dto);
        PrecioMembresia entidad = toEntity(dto);
        return toDTO(repository.save(entidad));
    }

    @Override
    public PrecioMembresiaDTO editar(Long id, PrecioMembresiaDTO dto) {
        validarDTO(dto);
        PrecioMembresia entidad = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Precio no encontrado"));
        entidad.setTipo(dto.getTipo());
        entidad.setPrecio(dto.getPrecio());
        entidad.setDescripcion(dto.getDescripcion());
        return toDTO(repository.save(entidad));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private PrecioMembresiaDTO toDTO(PrecioMembresia entidad) {
        return PrecioMembresiaDTO.builder()
                .id(entidad.getId())
                .tipo(entidad.getTipo())
                .precio(entidad.getPrecio())
                .descripcion(entidad.getDescripcion())
                .build();
    }

    private PrecioMembresia toEntity(PrecioMembresiaDTO dto) {
        return PrecioMembresia.builder()
                .tipo(dto.getTipo())
                .precio(dto.getPrecio())
                .descripcion(dto.getDescripcion())
                .build();
    }

    private void validarDTO(PrecioMembresiaDTO dto) {
        if (dto.getTipo() == null || dto.getTipo().trim().isEmpty()) {
            throw new BadRequestException("El tipo es obligatorio");
        }
        if (dto.getPrecio() == null || dto.getPrecio() < 0) {
            throw new BadRequestException("El precio debe ser mayor o igual a 0");
        }
        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new BadRequestException("La descripción es obligatoria");
        }
    }
}
