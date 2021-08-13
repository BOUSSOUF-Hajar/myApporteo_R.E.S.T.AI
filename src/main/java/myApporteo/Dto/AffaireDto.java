package myApporteo.Dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import myApporteo.entities.Contrat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AffaireDto  implements Serializable {
		private Long id;
		private String type;
		private String NomPrenom;
		private String adresse;
		private String ville;
		private String codePostal;
		private String emailProp;
		private String statut;
		@JsonIgnore
		private Contrat contrat;
		private String telephoneProp ;
		private UserDto apporteur;
	   
	    private UserDto partenaire;
}
