package com.gestioncontenido.service;

import com.gestioncontenido.dto.FaqDTO;

import java.util.List;

public interface FaqService {
    List<FaqDTO> obtenerTodas();
    FaqDTO crear(FaqDTO dto);
    FaqDTO editar(Long id, FaqDTO dto);
    void eliminar(Long id);
}
