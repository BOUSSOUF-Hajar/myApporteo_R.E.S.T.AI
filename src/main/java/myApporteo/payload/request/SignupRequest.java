package myApporteo.payload.request;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import lombok.*;
import myApporteo.entities.affaire;
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
    private Instant dateDeNaissance;
    private List<affaire> affairesApporteur;
    private List<affaire> affairesPrtenaire;
    private String nomAgence;

    private String nomSociete;

    private String Siret;

   
	
	private Long numCarteT;

    private String CCI;

    private String ville;

    private String codePostal;
    
    public SignupRequest(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, String adresse, String telephone, String type,
			Set<String> role, @NotBlank @Size(min = 6, max = 40) String password, Instant dateDeNaissance,
			List<affaire> affairesApporteur, List<affaire> affairesPrtenaire, String nomAgence, String nomSociete,
			String siret, Long numCarteT, String cCI, String ville, String codePostal) {
		super();
		this.username = username;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
		this.type = type;
		this.role = role;
		this.password = password;
		this.dateDeNaissance = dateDeNaissance;
		this.affairesApporteur = affairesApporteur;
		this.affairesPrtenaire = affairesPrtenaire;
		this.nomAgence = nomAgence;
		this.nomSociete = nomSociete;
		Siret = siret;
		this.numCarteT = numCarteT;
		CCI = cCI;
		this.ville = ville;
		this.codePostal = codePostal;
	}

   
}
