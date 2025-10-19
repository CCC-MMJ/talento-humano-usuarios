package co.edu.unimagdalena.devops.auth.repository;


import co.edu.unimagdalena.devops.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User>  findByName(String name);
    Optional<User>  findByEmail(String email);
    Optional<User>  findByAddress(String address);
}
