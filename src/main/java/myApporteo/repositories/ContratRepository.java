package myApporteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myApporteo.entities.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, String> {

}
