package myApporteo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Partenaire")
public class Partenaire extends users{

    private String nomAgence;

    private String nomSociete;

    private String Siret;

    private Long numCarteT;

    private String CCI;

    private String ville;

    private String codePostal;

    @OneToMany(mappedBy = "partenaire")
    private List<affaire> affaires;
}
