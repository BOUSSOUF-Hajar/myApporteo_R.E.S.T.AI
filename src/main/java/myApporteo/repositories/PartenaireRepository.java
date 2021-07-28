package myApporteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import myApporteo.entities.*;

public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
}
