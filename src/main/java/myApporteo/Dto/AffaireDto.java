package myApporteo.Dto;

import java.time.Instant;
import java.util.List;

import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AffaireDto {
		private Long id;
		private String type;
		private String NomPrenom;
		private String adresse;
		private String ville;
		private String codePostal;
		private String emailProp;
		private String telephoneProp ;
		private UserDto apporteur;
	   
	    private UserDto partenaire;
}
