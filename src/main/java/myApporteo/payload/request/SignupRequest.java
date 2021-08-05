package myApporteo.payload.request;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import myApporteo.entities.Affaire;
import myApporteo.entities.Affaire;
@Data

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String adresse;
    private String telephone;
    private  String type;
   

	

	
	

	private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    private List<Affaire> affairesApporteur;
    private List<Affaire> affairesPrtenaire;
    private String nomAgence;

    private String nomSociete;

    private String Siret;

   
	
	private Long numCarteT;

    private String CCI;

    private String ville;

    private String codePostal;
    
    public SignupRequest(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, String adresse, String telephone, String type,
			Set<String> role, @NotBlank @Size(min = 6, max = 40) String password, LocalDate dateDeNaissance,
			List<Affaire> affairesApporteur, List<Affaire> affairesPrtenaire, String nomAgence, String nomSociete,
			String siret, Long numCarteT, String CCI, String ville, String codePostal) {
		super();
		this.username = username;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
		this.type = type;
		this.role = role;
		this.password = password;
		this.dateDeNaissance =dateDeNaissance;
		this.affairesApporteur = affairesApporteur;
		this.affairesPrtenaire = affairesPrtenaire;
		this.nomAgence = nomAgence;
		this.nomSociete = nomSociete;
		this.Siret = siret;
		this.numCarteT = numCarteT;
		this.CCI = CCI;
		this.ville = ville;
		this.codePostal = codePostal;
	}

   
}
