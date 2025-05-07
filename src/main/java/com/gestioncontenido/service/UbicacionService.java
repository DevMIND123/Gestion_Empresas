package com.gestioncontenido.service;

import com.gestioncontenido.dto.UbicacionDTO;

import java.util.List;

public interface UbicacionService {
    List<UbicacionDTO> listarUbicaciones();
    UbicacionDTO actualizarUbicacion(Long id, UbicacionDTO dto);
}
