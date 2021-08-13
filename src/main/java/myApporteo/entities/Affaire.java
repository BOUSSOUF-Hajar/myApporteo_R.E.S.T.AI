package myApporteo.entities;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name ="affaire")

public class Affaire extends AbstractEntity{
	private String type;
	private String NomPrenom;
	private String adresse;
	private String ville;
	private String codePostal;
	private String emailProp;
	private String telephoneProp ;
	private String statut;
    @ManyToOne
    private User apporteur;
    @ManyToOne
    private User partenaire;
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
     private Contrat contrat;
}
