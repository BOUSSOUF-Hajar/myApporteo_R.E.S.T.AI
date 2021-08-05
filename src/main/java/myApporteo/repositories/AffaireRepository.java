package myApporteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myApporteo.entities.Affaire;
import myApporteo.entities.Role;


@Repository
public interface AffaireRepository extends JpaRepository<Affaire, Long> {
}
