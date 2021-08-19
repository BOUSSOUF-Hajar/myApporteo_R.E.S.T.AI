package myApporteo.repositories;

import java.util.List;
import java.util.Optional;

import myApporteo.Dto.UserDto;
import myApporteo.entities.ERole;
import myApporteo.entities.Role;
import myApporteo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
   
	Optional<User> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByVille(String ville);


	List<User> findByRoles(Role role);
}