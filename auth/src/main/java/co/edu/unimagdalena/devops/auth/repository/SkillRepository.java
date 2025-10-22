package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    boolean existsByNombre(String nombre);
}