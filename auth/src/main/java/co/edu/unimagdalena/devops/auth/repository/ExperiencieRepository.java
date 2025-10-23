package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Company;
import co.edu.unimagdalena.devops.auth.entity.Experiencie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExperiencieRepository extends JpaRepository<Experiencie, UUID> {
    Optional<Experiencie> findByProfileId(UUID profileId);
    Optional<Experiencie> findById(UUID experiencieId);
    Optional<Experiencie> findByCharge(String charge);
    Optional<Experiencie> findByDescription(String description);
}
