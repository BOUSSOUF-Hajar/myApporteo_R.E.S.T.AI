package myApporteo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myApporteo.entities.Affaire;
import myApporteo.entities.Role;
import myApporteo.entities.User;


@Repository
public interface AffaireRepository extends JpaRepository<Affaire, Long> {
	List<Affaire> findByApporteur(User user);
	List<Affaire> findByPartenaire(User user);
}
