package com.gestioncontenido;

import com.gestioncontenido.entity.Menu;
import com.gestioncontenido.entity.Ubicacion;
import com.gestioncontenido.repository.MenuRepository;
import com.gestioncontenido.repository.UbicacionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingletonInitializer {

    private final MenuRepository menuRepository;
    private final UbicacionRepository ubicacionRepository;

    @PostConstruct
    public void initSingletons() {
        if (menuRepository.count() == 0) {
            menuRepository.save(Menu.builder()
                .titulo("Menú Principal")
                .descripcion("Opciones principales del sistema")
                .build());
        }

        if (ubicacionRepository.count() == 0) {
            ubicacionRepository.save(Ubicacion.builder()
                .nombre("Sede Principal")
                .descripcion("Ubicación principal de operaciones")
                .cobertura("Bogotá")
                .build());
        }
    }
}
