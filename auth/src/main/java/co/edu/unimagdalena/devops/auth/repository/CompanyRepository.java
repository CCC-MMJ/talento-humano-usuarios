package co.edu.unimagdalena.devops.auth.repository;

import co.edu.unimagdalena.devops.auth.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByName(String name);
    Optional<Company> findBySector(String email);
}
