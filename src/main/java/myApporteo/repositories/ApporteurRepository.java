package myApporteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import myApporteo.entities.*;

public interface ApporteurRepository extends JpaRepository<Apporteur, Long> {

}
