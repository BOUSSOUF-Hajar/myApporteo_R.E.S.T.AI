package myApporteo.entities;
import lombok.*;

import javax.persistence.*;

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
    @ManyToOne
    private User apporteur;
    @ManyToOne
    private User partenaire;
}
