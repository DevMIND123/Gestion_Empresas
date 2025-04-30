package com.gestioncontenido.service;

import com.gestioncontenido.dto.BonoDescuentoDTO;

import java.util.List;

public interface BonoDescuentoService {
    List<BonoDescuentoDTO> listar();
    BonoDescuentoDTO crear(BonoDescuentoDTO dto);
    BonoDescuentoDTO editar(Long id, BonoDescuentoDTO dto);
    void eliminar(Long id);
}
