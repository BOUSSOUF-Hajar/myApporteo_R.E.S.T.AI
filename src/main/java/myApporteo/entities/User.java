package myApporteo.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User extends AbstractEntity{
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "motdepasse")
    private String moteDePasse;

    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private String telephone;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
 
    private Set<Role> roles = new HashSet<>();
    private String nomAgence;

    private String nomSociete;

    private String Siret;

    private Long numCarteT;

    private String CCI;

    private String ville;

    private String codePostal;
    @JsonIgnore
    @OneToMany(mappedBy = "partenaire")
    private List<Affaire> affairesPartenaire;

    private  String type;
    @Column(name = "datedenaissance")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateDeNaissance;
    @JsonIgnore
    @OneToMany(mappedBy = "apporteur")
    private List<Affaire> affairesApporteur;
    


	public User(String username, String email, String moteDePasse, String adresse, String telephone,
			String nomAgence, String nomSociete, String siret, Long numCarteT, String cCI, String ville,
			String codePostal, List<Affaire> affairesPartenaire, String type, LocalDate dateDeNaissance,
			List<Affaire> affairesApporteur) {
		super();
		this.username = username;
		this.email = email;
		this.moteDePasse = moteDePasse;
		this.adresse = adresse;
		this.telephone = telephone;
		this.roles = roles;
		this.nomAgence = nomAgence;
		this.nomSociete = nomSociete;
		Siret = siret;
		this.numCarteT = numCarteT;
		this.CCI = cCI;
		this.ville = ville;
		this.codePostal = codePostal;
		this.affairesPartenaire = affairesPartenaire;
		this.type = type;
		this.dateDeNaissance = dateDeNaissance;
		this.affairesApporteur = affairesApporteur;
	}

	
  }
