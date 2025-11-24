package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudyRepository extends JpaRepository<Study, UUID> {
    @Override
    void deleteById(UUID id);
}
