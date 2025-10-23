package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostulationRepository extends JpaRepository<Postulation, UUID> {
    Optional<Postulation> findByOfferId(UUID offerId);
}
