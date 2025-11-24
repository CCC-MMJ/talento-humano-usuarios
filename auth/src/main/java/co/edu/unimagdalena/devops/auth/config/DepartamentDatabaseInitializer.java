package co.edu.unimagdalena.devops.auth.config;

import co.edu.unimagdalena.devops.auth.service.DepartamentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartamentDatabaseInitializer {

    private final DepartamentService departamentSeedService;

    @PostConstruct
    public void init() {
        departamentSeedService.seed("Antioquia");
        departamentSeedService.seed("Atlántico");
        departamentSeedService.seed("Bogotá, D.C.");
        departamentSeedService.seed("Bolívar");
        departamentSeedService.seed("Boyacá");
        departamentSeedService.seed("Caldas");
        departamentSeedService.seed("Caquetá");
        departamentSeedService.seed("Cauca");
        departamentSeedService.seed("Cesar");
        departamentSeedService.seed("Córdoba");
        departamentSeedService.seed("Cundinamarca");
        departamentSeedService.seed("Chocó");
        departamentSeedService.seed("Huila");
        departamentSeedService.seed("La Guajira");
        departamentSeedService.seed("Magdalena");
        departamentSeedService.seed("Meta");
        departamentSeedService.seed("Nariño");
        departamentSeedService.seed("Norte de Santander");
        departamentSeedService.seed("Quindío");
        departamentSeedService.seed("Risaralda");
        departamentSeedService.seed("Santander");
        departamentSeedService.seed("Sucre");
        departamentSeedService.seed("Tolima");
        departamentSeedService.seed("Valle del Cauca");
        departamentSeedService.seed("Arauca");
        departamentSeedService.seed("Casanare");
        departamentSeedService.seed("Putumayo");
        departamentSeedService.seed("Archipiélago de San Andrés, Providencia y Santa Catalina");
        departamentSeedService.seed("Amazonas");
        departamentSeedService.seed("Guainía");
        departamentSeedService.seed("Guaviare");
        departamentSeedService.seed("Vaupés");
        departamentSeedService.seed("Vichada");

        System.out.println("Sincronización inicial de departamentos completada.");
    }
}

