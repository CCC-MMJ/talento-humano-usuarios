package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DepartamentRepository extends JpaRepository<Departament, UUID> {
    Optional<Departament> findByNameIgnoreCase(String name);
    boolean existsByName(String name);
}

