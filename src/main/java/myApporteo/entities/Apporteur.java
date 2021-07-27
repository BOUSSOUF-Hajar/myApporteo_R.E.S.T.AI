package myApporteo.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Apporteur")
public class Apporteur extends users{

    private  String type;
    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @OneToMany(mappedBy = "apporteur")
    private List<affaire> affaires;
}
