package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    Optional<Offer> findByTitleAndDescription(String title, String description);
    List<Offer> findByTitleContaining(String title);
    List<Offer> findByDescriptionContaining(String description);
    List<Offer> findByTitleContainingAndDescriptionContaining(String title, String description);
    Optional<Offer> findByTitle(String title);
}
