package myApporteo.Dto;

import java.time.Instant;
import java.util.List;

import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import myApporteo.entities.Apporteur;
import myApporteo.entities.Partenaire;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AffaireDto {
		private Long id;
		private Apporteur apporteur;
	   
	    private Partenaire partenaire;
}
