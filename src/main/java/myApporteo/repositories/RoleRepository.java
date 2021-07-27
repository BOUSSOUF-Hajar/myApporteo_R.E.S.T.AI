package myApporteo.repositories;

import java.util.Optional;

import myApporteo.entities.ERole;
import myApporteo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}