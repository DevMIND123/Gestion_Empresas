package com.gestioncontenido.service;

import com.gestioncontenido.dto.PromocionDTO;

import java.util.List;

public interface PromocionService {
    List<PromocionDTO> listarTodas();
    PromocionDTO crear(PromocionDTO dto);
    PromocionDTO editar(Long id, PromocionDTO dto);
}
