package com.gestioncontenido.service;

import com.gestioncontenido.dto.PrecioMembresiaDTO;

import java.util.List;

public interface PrecioMembresiaService {
    List<PrecioMembresiaDTO> listar();
    PrecioMembresiaDTO crear(PrecioMembresiaDTO dto);
    PrecioMembresiaDTO editar(Long id, PrecioMembresiaDTO dto);
    void eliminar(Long id);
}
